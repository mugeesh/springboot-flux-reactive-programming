package com.example.webflux.service;

import com.example.webflux.dao.EmployeeDao;
import com.example.webflux.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public List<Employee> fetchAllEmployee() {
        long startTime = System.currentTimeMillis();
        List<Employee> employee = dao.fetchAllEmployee();
        long endTime = System.currentTimeMillis();
        log.debug("Total Execution Time = " + (endTime - startTime));
        return employee;
    }
    public Flux<Employee> fetchAllEmployeeWithFlux() {
        long startTime = System.currentTimeMillis();
        Flux<Employee> employee = dao.fetchAllEmployeeWithFlux();
        long endTime = System.currentTimeMillis();
        log.debug("Total Execution Time = " + (endTime - startTime));
        return employee;
    }
}
