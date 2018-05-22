package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.Train;

import java.util.List;

public interface TrainDAO {
    public Train findByCode (String code);
    public Train save(Train entity);

    public List<Train> findAll();

    public Train delete(Train entity);
    public Train update(Train entity);
    public Train findById(int id);
}