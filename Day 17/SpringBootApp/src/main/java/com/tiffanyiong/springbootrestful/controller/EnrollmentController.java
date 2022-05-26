package com.tiffanyiong.springbootrestful.controller;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.Enrollment;
import com.tiffanyiong.springbootrestful.entity.EnrollmentDTO;
import com.tiffanyiong.springbootrestful.entity.Student;
import com.tiffanyiong.springbootrestful.service.EnrollmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class EnrollmentController {
    private final EnrollmentServiceImpl enrollService;

    public EnrollmentController(EnrollmentServiceImpl enrollService) {
        this.enrollService = enrollService;
    }

    @GetMapping("/enrollment/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(enrollService.getEnrollmentById(id), HttpStatus.OK);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Null Pointer", e);
        }
    }

    @PostMapping("/enrollment")
    public ResponseEntity<Enrollment> createEnroll(){
        return new ResponseEntity<>(enrollService.createEnrollment(), HttpStatus.OK);
    }

   @PutMapping("/enrollment/{id}")
   public ResponseEntity<EnrollmentDTO> addClassToEnroll(@PathVariable(value = "id") Long id, @RequestBody Clazz clazz){
        return new ResponseEntity<>(enrollService.addClassToEnroll(id, clazz), HttpStatus.OK);
   }
    @PutMapping("/enrollment/{id}/student")
    public ResponseEntity<EnrollmentDTO> addStudent(@PathVariable(value = "id") Long id, @RequestBody Student s){
            return new ResponseEntity<>(enrollService.addStudentToEnroll(id, s), HttpStatus.OK);

    }

    @DeleteMapping("/enrollment/{id}")
    public void deleteEnroll(@PathVariable Long id){
        try {
            enrollService.deleteEnrollById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "element not found", e);
        }

    }


}
