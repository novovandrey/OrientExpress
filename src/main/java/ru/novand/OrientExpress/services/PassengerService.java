package ru.novand.OrientExpress.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Service
public class PassengerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerDAO passengerDAO;

    private static final Logger logger = LoggerFactory.getLogger(PassengerService.class);

    public Passenger createPasseneger(String firstName, String familyname, Date birthday) throws CustomSQLException {
        logger.debug("createPasseneger method was called");
        Passenger passeneger = new Passenger();
        passeneger.setFirstname(firstName);
        passeneger.setFamilyname(familyname);
        passeneger.setBirthdate(birthday);
        Passenger result = passengerDAO.save(passeneger);
        return result;
    }

}
