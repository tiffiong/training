package com.example.restful_employee_practice.Enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String status;
    private List<EmployeeData> data;
    private String msg;
}
