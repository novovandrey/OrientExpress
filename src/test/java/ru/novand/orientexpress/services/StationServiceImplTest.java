package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.services.impl.BookServiceImpl;
import ru.novand.orientexpress.services.impl.StationServiceImpl;
import ru.novand.orientexpress.services.interfaces.BookService;
import ru.novand.orientexpress.services.interfaces.StationService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceImplTest {

    StationService stationService;

    @Mock
    private StationDAO stationDAO;

    @Before
    public void setUp() throws Exception {
        stationService = new StationServiceImpl(stationDAO);
        initMocks(this);
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddStationSuccess() {

        String stationName = "NewStation";
        Station stationResult = new Station();
        stationResult.setIdStation(1);
        stationResult.setStationname(stationName);

        Station stationMock = new Station();
        stationMock.setIdStation(1);
        stationMock.setStationname(stationName);

        Station stationExpected = new Station();

        // act
        when(stationDAO.save(stationResult)).thenReturn(stationResult);

        stationExpected = stationService.addStation(stationName);
        assertEquals(stationResult,stationMock);
    }

    @Test
    public void testAddStationFail() {

        String stationName = "NewStation";
        Station stationResult = new Station();
        stationResult.setIdStation(1);
        stationResult.setStationname(stationName);

        Station stationMock = new Station();
        stationMock.setIdStation(2);
        stationMock.setStationname(stationName);

        Station stationExpected = new Station();

        // act
        when(stationDAO.save(stationResult)).thenReturn(stationResult);

        stationExpected = stationService.addStation(stationName);
        assertNotEquals(stationResult,stationMock);
    }
}