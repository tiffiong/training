package com.tiffanyiong.springbootrestful.controller;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.ClazzDTO;
import com.tiffanyiong.springbootrestful.entity.Enrollment;
import com.tiffanyiong.springbootrestful.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/class")
    public ResponseEntity<List<ClazzDTO>> getAllClasses(){
        return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
    }

    @PostMapping("/class")
    public ResponseEntity<ClazzDTO> createClazz(@RequestBody Clazz c) {
        return new ResponseEntity<>(classService.createClass(c), HttpStatus.OK);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<List<ClazzDTO>> getClassById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(classService.getClassById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "element not found", e);
        }
    }

    @DeleteMapping("/class/{id}")
    public void deleteClass(@PathVariable(value = "id") Long id) {
        try {
            classService.deleteClassById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "element not found", e);
        }
    }

    @PutMapping("/class/{id}")
    public ClazzDTO updateClass(@PathVariable(value = "id") Long id, @RequestBody Clazz clazz_detail) {
        return classService.updateClass(id, clazz_detail);
    }


    @GetMapping("/exception")
    public ResponseEntity<Clazz> testException() throws Exception{
        throw new Exception("exception");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException() {
        return new ResponseEntity("404", HttpStatus.NOT_FOUND);
    }

}
