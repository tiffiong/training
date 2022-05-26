package com.tiffiong.employee_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String employee_name;
    public EmployeeDTO (Employee employee) {
        this.id = employee.getId();
        this.employee_name = employee.getEmployee_name();
    }
}
