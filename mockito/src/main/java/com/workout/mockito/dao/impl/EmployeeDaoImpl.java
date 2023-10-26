package com.workout.mockito.dao.impl;

import com.workout.mockito.dao.EmployeeDao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDaoImpl implements EmployeeDao {

    private BufferedReader br;

    private final String filePath = System.getProperty("user.dir").concat("/test-data/data.txt");

    public EmployeeDaoImpl() {
        init();
    }

    private void init() {
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printAllData() {
        try {
            String line;
            while (null != (line = br.readLine())) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String contains(String text) {
        try {
            String line;
            while (null != (line = br.readLine())) {
                if(line.contains(text))
                    return line;
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return "Not Found";
    }
}
