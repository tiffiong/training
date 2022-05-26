package com.tiffanyiong.springbootrestful.service;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.Enrollment;
import com.tiffanyiong.springbootrestful.entity.EnrollmentDTO;
import com.tiffanyiong.springbootrestful.entity.Student;
import com.tiffanyiong.springbootrestful.repository.EnrollmentRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

    private final Log logger = LogFactory.getLog(EnrollmentServiceImpl.class);
    private final EnrollmentRepo e_repo;
    private final RestTemplate restTemplate;


    public EnrollmentServiceImpl(EnrollmentRepo e_repo, RestTemplate restTemplate) {
        this.e_repo = e_repo;
        this.restTemplate = restTemplate;
    }


    @Override
    public Enrollment createEnrollment() {
        Enrollment e = new Enrollment();
        return e_repo.save(e);
    }

    @Override
    public EnrollmentDTO addClassToEnroll(Long id, Clazz c) {
        Enrollment e = null;
        if (e_repo.findById(id).get() != null) {
            e = e_repo.findById(id).get();
            c.addEnrollment(e);
            e.setClazz(c);
            e_repo.save(e);
        } else {
            logger.error("the element doesn't exist");
        }

        return new EnrollmentDTO(e);
    }

    public EnrollmentDTO addStudentToEnroll(Long id, Student student) {
        Enrollment e = null;
        if (e_repo.findById(id).get() != null) {
            e = e_repo.findById(id).get();
            student.addEnrollment(e);
            e.setStudent(student);
            e_repo.save(e);
        } else {
           logger.info("the element doesn't exist");
        }
        return new EnrollmentDTO(e);
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {
        logger.info("----Invoked getEnrollmentById---");
        if(e_repo.findById(id).get() != null) {
            Enrollment e = e_repo.findById(id).get();
            return new EnrollmentDTO(e);
        } else {
            logger.error("Not Found");
        }
        return null;
    }



    @Override
    public void deleteEnrollById(Long id) {
       if (e_repo.findById(id).get() == null) {
           logger.error("the element doesn't exist");
       } else {
           e_repo.deleteById(id);
       }
    }

}
