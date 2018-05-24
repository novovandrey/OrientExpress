package ru.novand.orientexpress.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Service
public class PassengerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private UserDAO userDAO;

    private static final Logger logger = LoggerFactory.getLogger(PassengerService.class);

    public Passenger createPasseneger(String firstName, String familyname, LocalDate birthday,String username) throws CustomSQLException {
        logger.debug("createPasseneger method was called");
        Passenger passeneger = new Passenger();
        passeneger.setFirstname(firstName);
        passeneger.setFamilyname(familyname);
        passeneger.setBirthdate(birthday);

        User user = userDAO.findByName(username);

        passeneger.setUser(user);

        Passenger result = passengerDAO.save(passeneger);
        return result;
    }

    public Passenger getPassengerByUserName(String username) throws CustomSQLException {

        Passenger result = passengerDAO.findByUserName(username);
        return result;
    }

}
