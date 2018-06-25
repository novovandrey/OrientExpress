package ru.novand.orientexpress.services.interfaces;

import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Service
public interface BookService {

    public boolean checkTrainExistByTrainCode(String trainCode);

    public Ticket saveTicket(String trainCode, String fromSt, String toSt, Instant depdate, Passenger passenger);

    public boolean checkVacantPlaces(int trainCode, Instant depdateFormat);

    public boolean isPassengerAlreadyRegistered(int trainCode, Instant depdateFormat, Passenger passenger);

    public Passenger createPasseneger(String firstName, String familyname, Instant birthday, String username) ;

    public boolean checkSchedule(String fromSt, String toSt, String departuredate);
    public String payTicketProcess(String FamilyName,String FirstName,String BirthDate,String traincode,String fromSt,
                                   String toSt,String depdate,String deptime,HttpServletRequest request);

}
