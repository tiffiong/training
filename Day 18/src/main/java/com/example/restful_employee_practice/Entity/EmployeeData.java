package com.example.restful_employee_practice.Enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {
    private Long id;
    private String employee_name;
    private Long employee_salary;
    private Integer employee_age;


}
