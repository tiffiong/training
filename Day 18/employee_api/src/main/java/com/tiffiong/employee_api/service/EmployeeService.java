package com.tiffiong.employee_api.service;

import com.tiffiong.employee_api.entity.EmployeeDTO;
import com.tiffiong.employee_api.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<EmployeeDTO> getEmployeeWhoIsOlder(Integer age);

    List<EmployeeDTO> getEmployeesByAge(Integer age);

}
