package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> findAll();
    public void save(Role entity);
}
