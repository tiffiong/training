package com.tiffanyiong.springbootrestful.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long student_id;
    private String first_name;
    private String last_name;
    private int age;

    public StudentDTO(Student s){
        this.student_id = s.getStudent_id();
        this.first_name = s.getFirst_name();
        this.last_name = s.getLast_name();
        this.age = s.getAge();
    }
}
