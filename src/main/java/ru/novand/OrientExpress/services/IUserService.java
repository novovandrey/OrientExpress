package ru.novand.OrientExpress.services;

import ru.novand.OrientExpress.domain.dto.UserDTO;
import ru.novand.OrientExpress.exception.EmailExistsException;
import ru.novand.OrientExpress.domain.entities.User;

public interface IUserService {
    User registerNewUserAccount(UserDTO accountDto)
            throws EmailExistsException;
}
