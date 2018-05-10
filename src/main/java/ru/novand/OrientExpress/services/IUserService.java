package ru.novand.OrientExpress.services;

import ru.novand.OrientExpress.domain.dto.UserDto;
import ru.novand.OrientExpress.exception.EmailExistsException;
import ru.novand.OrientExpress.domain.entities.User;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}
