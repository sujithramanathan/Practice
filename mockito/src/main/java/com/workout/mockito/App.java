package com.workout.mockito;

import com.workout.mockito.dao.impl.EmployeeDaoImpl;
import com.workout.mockito.service.EmployeeService;
import com.workout.mockito.service.impl.EmployeeServiceImpl;

public class App {

    public static void main(String[] args) {
        EmployeeService empService = new EmployeeServiceImpl(new EmployeeDaoImpl());
        System.out.println(empService.contains("Sujith"));
    }

}
