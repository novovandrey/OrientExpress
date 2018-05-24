package ru.novand.orientexpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDAO userDAO;

//
//    @Transactional
//    @Override
//    public User registerNewUserAccount(UserDTO accountDto)
//            throws EmailExistsException {
//
//        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
//                    "There is an account with that email address:  + accountDto.getEmail());
//        }
//        User user = new User();
//        user.setFirstName(accountDto.getFirstName());
//        user.setLastName(accountDto.getLastName());
//        user.setPassword(accountDto.getPassword());
//        user.setEmail(accountDto.getEmail());
//        user.setRoles(Arrays.asList("ROLE_USER"));
//        return repository.save(user);
//    }
//    private boolean emailExist(String email) {
//        User user = repository.findByEmail(email);
//        if (user != null) {
//            return true;
//        }
//        return false;
//    }
public User getUserIDByName(String username) throws CustomSQLException {
    User user = new User();
    User result = userDAO.findByName(username);
    return result;
}
}