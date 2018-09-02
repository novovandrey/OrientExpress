package ru.novand.orientexpress.services.impl;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.Impl.GeoCityDAOImpl;
import ru.novand.orientexpress.domain.DAO.interfaces.GeoCityDAO;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.GeoCity;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.interfaces.MapViewService;
import ru.novand.orientexpress.services.interfaces.ScheduleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@Service
public class MapViewServiceImpl implements MapViewService {

    @PersistenceContext
    private EntityManager entityManager;

    private GeoCityDAO geoCityDAO;

    private ScheduleService scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Autowired
    public MapViewServiceImpl(GeoCityDAO geoCityDAO, ScheduleService scheduleService) {
        this.geoCityDAO = geoCityDAO;
        this.scheduleService = scheduleService;
    }

    public MapViewServiceImpl() {

    }

    public List<MapPointDTO> getJsonMarkers(String traincode, String departuredate, String fromSt, String toSt ) throws CustomSQLException {
        logger.debug("MapViewService GetJsonMarkers method was called");

        List<ScheduleDTO> tariff = scheduleService.getTrainTariff(traincode, departuredate);

        List<ScheduleDTO> tariffOrdered = scheduleService.getTariffOrdered(tariff,fromSt,toSt);

        List<String> citylist = scheduleService.getCityList(tariffOrdered);

        List<MapPointDTO> maps = geoCityDAO.getAllGeoCityByCities(citylist);

        return  maps;

    }

}
