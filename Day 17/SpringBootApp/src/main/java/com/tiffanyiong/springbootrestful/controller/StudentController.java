package com.tiffanyiong.springbootrestful.controller;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.ClazzDTO;
import com.tiffanyiong.springbootrestful.entity.Student;
import com.tiffanyiong.springbootrestful.entity.StudentDTO;
import com.tiffanyiong.springbootrestful.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class StudentController {
    private final StudentServiceImpl stu_service;
    @Autowired
    public StudentController(StudentServiceImpl str_service) {
        this.stu_service = str_service;
    }


    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return new ResponseEntity<>(stu_service.getAllStudent(), HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody Student s) {
        return new ResponseEntity<>(stu_service.createStudent(s), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<StudentDTO>> getStudentById(@PathVariable Long id){
       try {
           return new ResponseEntity<>(stu_service.getStudentById(id), HttpStatus.OK);
       } catch (NoSuchElementException e) {
           throw new ResponseStatusException(
                   HttpStatus.valueOf(500), "element not found", e);
       }
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        try {
            stu_service.deleteStudentById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(500), "element not found", e);
        }
    }

    @PutMapping("/student/{id}")
    public StudentDTO updateStudent(@PathVariable(value = "id") Long id, @RequestBody Student s_detail) {
        try {
            return stu_service.updateStudent(id, s_detail);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(500), "element not found", e);
        }
    }





}
