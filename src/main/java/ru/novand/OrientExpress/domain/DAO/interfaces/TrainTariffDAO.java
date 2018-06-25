package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.TrainTariff;

import java.util.List;

public interface TrainTariffDAO extends GenDAO<TrainTariff> {

    public List<TrainTariff> getAllTrainTariffByTrainCode(String traincode);

}
