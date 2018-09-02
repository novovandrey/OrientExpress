package ru.novand.orientexpress.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.orientexpress.domain.DAO.interfaces.RoleDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.Role;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.services.interfaces.UserService;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setRoles(new HashSet<>(roleDAO.findAll()));
        user.setEnabled(true);
        userDAO.save(user);
        Role role = new Role();
        role.setName(user.getUsername());
        roleDAO.save(role);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
