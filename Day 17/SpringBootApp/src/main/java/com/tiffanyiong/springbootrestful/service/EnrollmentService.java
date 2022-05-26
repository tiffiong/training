package com.tiffanyiong.springbootrestful.service;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.Enrollment;
import com.tiffanyiong.springbootrestful.entity.EnrollmentDTO;
import com.tiffanyiong.springbootrestful.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentService {
    Enrollment createEnrollment();

    EnrollmentDTO addClassToEnroll(Long id, Clazz c);
    EnrollmentDTO getEnrollmentById(Long id);

    void deleteEnrollById(Long id);
    EnrollmentDTO addStudentToEnroll(Long id, Student student);
}
