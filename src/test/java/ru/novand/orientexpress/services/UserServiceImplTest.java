package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.services.impl.UserServiceImpl;
import ru.novand.orientexpress.services.interfaces.UserService;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    UserService userService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
        initMocks(this);
    }
    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void trstGetUserIDByNameSuccess() {

        User userResult = new User();
        User userMock = new User();
        userMock.setUsername("Ivan");
        userMock.setPassword("Pass");
        User userExpected = new User();
        userExpected = userMock;

        // act
        when(userDAO.findByUsername(anyString())).thenReturn(userMock);

        //userResult = userService.getUserIDByName(anyString());
        assertEquals(userExpected,userMock);
    }
}