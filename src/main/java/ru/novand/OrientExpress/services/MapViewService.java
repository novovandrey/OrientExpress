package ru.novand.OrientExpress.services;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.novand.OrientExpress.domain.dto.MapPointDTO;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

        Query queryObject= (Query) entityManager.createQuery ("select new ru.novand.OrientExpress.domain.dto.MapPointDTO(gc.city_en,gc.lat,gc.lng) from GeoCity gc where gc.city_en IN (:cities) and gc.country_en ='Russia' ");
        queryObject.setParameterList("cities", (cityList));

        maps.addAll(queryObject.list());

        maps.sort((c1, c2) -> Double.compare(c1.getSortfield(), c2.getSortfield()));
        
        return  maps;

        //String query = "select new ru.novand.OrientExpress.domain.dto.MapPointDTO(gc.city_en,gc.lat,gc.lng) from GeoCity gc where gc.city_en IN (:cities)";
//        List<MapPointDTO> mapPointDTOS = entityManager.createQuery (query)
//        .setParameter ("cities", city)
//                .getResultList();




        //return mapPointDTOS;
    }

}
