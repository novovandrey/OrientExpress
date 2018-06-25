package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import ru.novand.orientexpress.domain.DAO.Impl.GeoCityDAOImpl;
import ru.novand.orientexpress.domain.DAO.interfaces.GeoCityDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainTariffDAO;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainTariff;
import ru.novand.orientexpress.services.impl.BookServiceImpl;
import ru.novand.orientexpress.services.impl.MapViewServiceImpl;
import ru.novand.orientexpress.services.impl.ScheduleServiceImpl;
import ru.novand.orientexpress.services.interfaces.BookService;
import ru.novand.orientexpress.services.interfaces.MapViewService;
import ru.novand.orientexpress.services.interfaces.ScheduleService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@RunWith(MockitoJUnitRunner.class)
public class MapViewServiceImplTest {

    @Mock
    private ScheduleService scheduleService;
    @Mock
    private GeoCityDAO geoCityDAO;
    @InjectMocks
    MapViewService mapViewService = new MapViewServiceImpl();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetJsonMarkersSuccess() {

        String traincode = "111";
        String depdate = "2018-12-31";
        Train train1 = new Train();
        train1.setIdTrain(12);
        train1.setTrainCode("100");
        train1.setTrainSeats(10);
        BigDecimal bigDecimal = BigDecimal.valueOf(100);
        String arrivalstation = "Moscow";
        String departstation = "Saint Petersburg";
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListResult = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListOrdered = new ArrayList<>();

        List<String> citylist = new ArrayList<>();
        scheduleDTOList.add(new ScheduleDTO(traincode,arrivalstation,departstation,bigDecimal));
        // act
        when(scheduleService.getTrainTariff(traincode, depdate)).thenReturn(scheduleDTOList);
        when(scheduleService.getTariffOrdered(scheduleDTOListResult,departstation,arrivalstation)).thenReturn(scheduleDTOListResult);
        when(scheduleService.getCityList( scheduleDTOListOrdered)).thenReturn(citylist);

        List<MapPointDTO> mapPointDTOList = new ArrayList<>();
        MapPointDTO mapPointDTO = new MapPointDTO("Moscow","54","53");
        mapPointDTOList.add(mapPointDTO);

        List<MapPointDTO> mapPointDTOListExpected = new ArrayList<>();
        MapPointDTO mapPointDTOExpeceted = new MapPointDTO("Moscow","54","53");
        mapPointDTOListExpected.add(mapPointDTOExpeceted);

        when(geoCityDAO.getAllGeoCityByCities(citylist)).thenReturn(mapPointDTOList);
        mapPointDTOList = mapViewService.getJsonMarkers(traincode,depdate,departstation,arrivalstation);

        assertEquals(mapPointDTOListExpected,mapPointDTOList);
    }

    @Test
    public void testGetJsonMarkersFail() {

        String traincode = "111";
        String depdate = "2018-12-31";
        Train train1 = new Train();
        train1.setIdTrain(12);
        train1.setTrainCode("100");
        train1.setTrainSeats(10);
        BigDecimal bigDecimal = BigDecimal.valueOf(100);
        String arrivalstation = "Moscow";
        String departstation = "Saint Petersburg";
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListResult = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListOrdered = new ArrayList<>();

        List<String> citylist = new ArrayList<>();
        scheduleDTOList.add(new ScheduleDTO(traincode,arrivalstation,departstation,bigDecimal));
        // act
        when(scheduleService.getTrainTariff(traincode, depdate)).thenReturn(scheduleDTOList);
        when(scheduleService.getTariffOrdered(scheduleDTOListResult,departstation,arrivalstation)).thenReturn(scheduleDTOListResult);
        when(scheduleService.getCityList( scheduleDTOListOrdered)).thenReturn(citylist);

        List<MapPointDTO> mapPointDTOList = new ArrayList<>();
        MapPointDTO mapPointDTO = new MapPointDTO("Moscow","54","53");
        mapPointDTOList.add(mapPointDTO);

        List<MapPointDTO> mapPointDTOListExpected = new ArrayList<>();
        MapPointDTO mapPointDTOExpeceted = new MapPointDTO("Spb","54","53");
        mapPointDTOListExpected.add(mapPointDTOExpeceted);

        when(geoCityDAO.getAllGeoCityByCities(citylist)).thenReturn(mapPointDTOList);
        mapPointDTOList = mapViewService.getJsonMarkers(traincode,depdate,departstation,arrivalstation);

        assertNotEquals(mapPointDTOListExpected,mapPointDTOList);
    }
}