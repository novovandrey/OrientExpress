package ru.novand.orientexpress.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.*;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.interfaces.BookService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private TrainRouteDAO trainRouteDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ScheduleServiceImpl scheduleService;
    @Autowired
    private final MessageSource messageSource;

    private DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public BookServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public boolean checkTrainExistByTrainCode(String trainCode) {
        logger.debug("checkTrainExistByTrainCode method was called");
        Train result = trainDAO.findByCode(trainCode);
        if (result!=null) return true;
        return false;
    }

    public Ticket saveTicket(String trainCode, String fromSt, String toSt, Instant depdate, Passenger passenger) throws CustomSQLException {
        logger.debug("saveTicket method was called");
        Ticket ticket = new Ticket(passenger, trainCode, depdate);
        ticketDAO.save(ticket);
        return ticket;
    }

    public boolean checkVacantPlaces(int trainCode, Instant depdateFormat) {
        logger.debug("checkVacantPlaces method was called");
        int tickets = ticketDAO.countTicketsOnTrain(String.valueOf(trainCode),depdateFormat);
        int ticketsAvalible = trainDAO.findByCode(String.valueOf(trainCode)).getTrainSeats();
        return (ticketsAvalible <= tickets);
    }

    public boolean isPassengerAlreadyRegistered(int trainCode, Instant depdateFormat, Passenger passenger) {
        logger.debug("isPassengerAlreadyRegistered method was called");
        List<Passenger> passengerList = ticketDAO.getAllPassengersByTrain(String.valueOf(trainCode),depdateFormat);
        return passengerList.contains(passenger);
    }

    public Passenger createPasseneger(String firstName, String familyname, Instant birthday, String username) throws CustomSQLException {
        logger.debug("createPasseneger method was called");
        Passenger passeneger = new Passenger();
        passeneger.setFirstname(firstName);
        passeneger.setFamilyname(familyname);
        passeneger.setBirthdate(birthday);

        User user = userDAO.findByUsername(username);

        passeneger.setUser(user);

        Passenger result = passengerDAO.save(passeneger);
        return result;
    }

    public boolean checkSchedule(String fromSt, String toSt, String departuredate){
        logger.debug("checkSchedule method was called");
        List<ScheduleDTO> routeList = scheduleService.getSchedule(fromSt,toSt,departuredate);
        if(routeList.isEmpty()) return false;
        return true;
    }
    public String payTicketProcess(String FamilyName,String FirstName,String BirthDate,String traincode,String fromSt,
                                   String toSt,String depdate,String deptime,HttpServletRequest request){
        logger.debug("payTicketProcess method was called");
        LocalDate birthDateFormat = LocalDate.parse(BirthDate, ddMMyyyyformatter);
        Instant BirthDateFormatInstant =  birthDateFormat.atStartOfDay(ZoneId.of("UTC")).toInstant();
        LocalDate depdateFormat = LocalDate.parse(depdate, ddMMyyyyformatter);
        Instant depdateFormatInstant =  depdateFormat.atStartOfDay(ZoneId.of("UTC")).toInstant();

        boolean checkTickets = true;
        boolean checkPassengers = true;
        boolean checkTime = true;
        boolean success = false;

        if (checkVacantPlaces(Integer.parseInt(traincode),depdateFormatInstant)) {
            //if train has no available seats
            checkTickets = false;
        }
        if (isPassengerAlreadyRegistered(Integer.parseInt(traincode),depdateFormatInstant, new Passenger(FirstName, FamilyName, BirthDateFormatInstant))) {
            checkPassengers = false;
        }

        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(10);
        String depdatetime = depdate + " " + deptime;
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime depdatetimeFormat = LocalDateTime.parse(depdatetime, formatterDateTime);
        if ( currentTime.isAfter(depdatetimeFormat)) {
            checkTime = false;
        }

        if (checkTickets && checkPassengers && checkTime) {
            String username = request.getUserPrincipal().getName();
            Passenger passenger = createPasseneger(FirstName, FamilyName, BirthDateFormatInstant, username);
            Ticket result = saveTicket(traincode, fromSt, toSt,  depdateFormatInstant, passenger);
            success = true;
        }

        logger.debug("payTicketProcess method was called result " + success);

        String msg = messageSource.getMessage("unexpected_error", null, LocaleContextHolder.getLocale());
        String classIdent;
        if (success) {
            classIdent = "alert-success";
            msg = messageSource.getMessage("success_processpay", null, LocaleContextHolder.getLocale());
        } else {
            classIdent = "alert-danger";
            if (!checkTickets)
                msg = messageSource.getMessage("no_seats", null, LocaleContextHolder.getLocale());

            if (!checkPassengers)
                msg = messageSource.getMessage("passengerAlreadyExists", null, LocaleContextHolder.getLocale());

            if (!checkTime)
                msg = messageSource.getMessage("less10min", null, LocaleContextHolder.getLocale());

        }

        return msg;
    }

}
