package com.example.restful_employee_practice.Controller;

import com.example.restful_employee_practice.Enitiy.Employee;
import com.example.restful_employee_practice.Enitiy.EmployeeData;
import com.example.restful_employee_practice.Service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employee", params = "older_age")
    public ResponseEntity<List<EmployeeData>> getEmployeeOlderThan(@RequestParam Integer older_age){
        return new ResponseEntity<>(employeeService.getEmployeeOlderThan(older_age), HttpStatus.OK);
    }

    @GetMapping(value = "/employee", params = "employee_age")
    public ResponseEntity<List<EmployeeData>> getEmployeeByAge(@RequestParam Integer employee_age) {
        return new ResponseEntity<>(employeeService.getEmployeByAge(employee_age), HttpStatus.OK);
    }


}
