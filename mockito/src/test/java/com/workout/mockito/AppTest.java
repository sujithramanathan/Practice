package com.workout.mockito;

import com.workout.mockito.dao.EmployeeDao;
import com.workout.mockito.service.EmployeeService;
import com.workout.mockito.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Mock
    private EmployeeDao employeeDao;

    @Test
    public void containsTest() {
        EmployeeService empService = new EmployeeServiceImpl(employeeDao);
        Mockito.when(employeeDao.contains(Mockito.anyString())).thenReturn("1,Sujith Ramanathan, New York, United States");
        assertEquals("1,Sujith Ramanathan, New York, United States", employeeDao.contains("hello world"));
    }

}
