package ru.novand.orientexpress.services.interfaces;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.impl.PassengerServiceImpl;
import ru.novand.orientexpress.services.impl.ScheduleServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface MapViewService {

    public List<MapPointDTO> getJsonMarkers(String traincode, String departuredate, String fromSt, String toSt );
}
