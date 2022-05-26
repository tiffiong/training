package com.tiffanyiong.springbootrestful.service;

import com.tiffanyiong.springbootrestful.entity.Student;
import com.tiffanyiong.springbootrestful.entity.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDTO createStudent(Student stu_detail);
    List<StudentDTO> getAllStudent();
    List<StudentDTO> getStudentById(Long id);
    void deleteStudentById (Long id);
    StudentDTO updateStudent(Long id, Student stu_detail);
}
