package com.tiffiong.employee_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private Long id;
    private String employee_name;
    private Integer age;
    private Long employee_salary;
    private String profile_image;

}
