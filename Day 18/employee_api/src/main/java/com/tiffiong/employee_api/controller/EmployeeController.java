package com.tiffiong.employee_api.controller;

import com.tiffiong.employee_api.entity.Employee;
import com.tiffiong.employee_api.entity.EmployeeDTO;
import com.tiffiong.employee_api.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping(value ="/api")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee", params = "age")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeOlderThan(@RequestParam Integer age) {
        return new ResponseEntity<>(employeeService.getEmployeeWhoIsOlder(age), HttpStatus.OK);
    }
    @RequestMapping(value = "/employee", params = "employee_age")
    public ResponseEntity<List<EmployeeDTO>> groupByAge(@RequestParam Integer employee_age) {
        return new ResponseEntity<>(employeeService.getEmployeesByAge(employee_age), HttpStatus.OK);
    }



}
