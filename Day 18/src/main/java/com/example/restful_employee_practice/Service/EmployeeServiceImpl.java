package com.example.restful_employee_practice.Service;

import com.example.restful_employee_practice.Config.RestTemplateConfig;
import com.example.restful_employee_practice.Enitiy.Employee;
import com.example.restful_employee_practice.Enitiy.EmployeeData;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Log logger = LogFactory.getLog(EmployeeServiceImpl.class);
    private final RestTemplateConfig restTemplate;


    private final String url = "http://dummy.restapiexample.com/api/v1/employees";

    @Autowired
    public EmployeeServiceImpl(RestTemplateConfig restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<EmployeeData> getEmployeeOlderThan(Integer age) {
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(url, Employee.class);
        System.out.println(employee);
        return employee.getData().stream().filter(e -> {
            return e.getEmployee_age() > age;
        }).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeData> getEmployeByAge(Integer age) {
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(url, Employee.class);
        System.out.println(employee);
        return employee.getData().stream().filter(e -> {
            return e.getEmployee_age() == age;
        }).collect(Collectors.toList());

    }
}
