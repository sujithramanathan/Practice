package com.workout.mockito.service.impl;

import com.workout.mockito.dao.EmployeeDao;
import com.workout.mockito.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void printAllData() {
        employeeDao.printAllData();
    }

    public String contains(String text) {
        return employeeDao.contains(text);
    }
}
