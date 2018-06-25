package ru.novand.orientexpress.services;

import ru.novand.orientexpress.domain.dto.UserDTO;
import ru.novand.orientexpress.exception.EmailExistsException;
import ru.novand.orientexpress.domain.entities.User;

public interface IUserService {
    User registerNewUserAccount(UserDTO accountDto)
            throws EmailExistsException;
}
