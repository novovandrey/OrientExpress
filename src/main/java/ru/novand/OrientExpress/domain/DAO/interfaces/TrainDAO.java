package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.Train;

public interface TrainDAO extends GenDAO<Train> {
    public Train findByCode (String code);
}