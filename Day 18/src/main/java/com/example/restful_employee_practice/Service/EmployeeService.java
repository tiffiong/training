package com.example.restful_employee_practice.Service;

import com.example.restful_employee_practice.Enitiy.Employee;
import com.example.restful_employee_practice.Enitiy.EmployeeData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
//    age > 61
//    [xxx, xxx, xxx ]

    List<EmployeeData> getEmployeeOlderThan(Integer age);
    /*  age == 23
    [xx1, xx2]
    * */

    List<EmployeeData> getEmployeByAge(Integer age);

}
