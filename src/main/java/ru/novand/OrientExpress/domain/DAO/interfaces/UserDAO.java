package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.User;
import java.util.List;

public interface UserDAO {

    public User save(User entity);

    public List<User> findAll();

    public User delete(User entity);
    public User update(User entity);
    public User findById(int id);
    public User findByName(String name);

}
