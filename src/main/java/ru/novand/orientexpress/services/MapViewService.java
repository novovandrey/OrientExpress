package ru.novand.orientexpress.services;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@Service
public class MapViewService {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(PassengerService.class);

    public List<MapPointDTO> getJsonMarkers( List<String> cityList ) throws CustomSQLException {
        logger.debug("GetJsonMarkers method was called");

        List<MapPointDTO> maps = new ArrayList<>();

        System.out.println("ScheduleService getTrainTariff is called");

        Query queryObject= (Query) entityManager.createQuery ("select new ru.novand.orientexpress.domain.dto.MapPointDTO(gc.city_en,gc.lat,gc.lng) from GeoCity gc where gc.city_en IN (:cities) and gc.country_en ='Russia' ");
        queryObject.setParameterList("cities", (cityList));

        maps.addAll(queryObject.list());

        maps.sort(Comparator.comparingDouble(MapPointDTO::getSortfield));
        
        return  maps;

        //String query = "select new ru.novand.orientexpress.domain.dto.MapPointDTO(gc.city_en,gc.lat,gc.lng) from GeoCity gc where gc.city_en IN (:cities)";
//        List<MapPointDTO> mapPointDTOS = entityManager.createQuery (query)
//        .setParameter ("cities", city)
//                .getResultList();




        //return mapPointDTOS;
    }

}
