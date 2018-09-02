package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.entities.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
