package ru.novand.orientexpress.domain.DAO.Impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.GeoCityDAO;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.domain.entities.GeoCity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository("geoCityDAO")
public class GeoCityDAOImpl implements GeoCityDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<MapPointDTO> getAllGeoCityByCities( List<String> citylist) {

        List<MapPointDTO> maps = new ArrayList<>();
        System.out.println("MapViewService getJsonMarkers is called");
        Query queryObject= (Query) manager.createQuery ("select new ru.novand.orientexpress.domain.dto.MapPointDTO(gc.city_en,gc.lat,gc.lng) from GeoCity gc where gc.city_en IN (:cities) and gc.country_en ='Russia' ");
        queryObject.setParameterList("cities", (citylist));

        maps.addAll(queryObject.list());

        maps.sort(Comparator.comparingDouble(MapPointDTO::getSortfield));

        return  maps;

    }

}
