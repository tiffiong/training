package com.tiffiong.employee_api.service;

import com.tiffiong.employee_api.entity.Employee;
import com.tiffiong.employee_api.entity.EmployeeDTO;
import com.tiffiong.employee_api.repository.EmployeeRepo;
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
    private final EmployeeRepo employeeRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo, RestTemplate restTemplate) {
        this.employeeRepo = employeeRepo;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<EmployeeDTO> getEmployeeWhoIsOlder(Integer age) {
        List<EmployeeDTO> list = employeeRepo.getEmployeeWhoIsOlder(age).stream().map(e -> new EmployeeDTO(e)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<EmployeeDTO> getEmployeesByAge(Integer age) {
        List<EmployeeDTO> list = employeeRepo.groupByAge(age).stream().map(e -> new EmployeeDTO(e)).collect(Collectors.toList());
        return list;
    }
}
