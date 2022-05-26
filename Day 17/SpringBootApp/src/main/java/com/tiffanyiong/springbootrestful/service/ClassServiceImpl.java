package com.tiffanyiong.springbootrestful.service;


import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.ClazzDTO;
import com.tiffanyiong.springbootrestful.entity.Enrollment;
import com.tiffanyiong.springbootrestful.repository.ClassRepo;
import com.tiffanyiong.springbootrestful.repository.EnrollmentRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService{
    private final Log logger = LogFactory.getLog(ClassServiceImpl.class);

    private final ClassRepo classRepo;
    private final RestTemplate restTemplate;

    private EnrollmentRepo enrollmentRepo;

    @Autowired
    public ClassServiceImpl(ClassRepo classRepo, RestTemplate restTemplate) {
        this.classRepo = classRepo;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<ClazzDTO> getAllClasses() {
        logger.info("-----classService: getAllClasses()------");
        return classRepo.getAllClasses().stream().map(c -> new ClazzDTO(c)).collect(Collectors.toList());
    }

    @Override
    public List<ClazzDTO> getClassById(Long id) {
        logger.info("-----classService: getClassById()------");
        List<ClazzDTO> list = classRepo.getClassById(id).stream().map(c -> new ClazzDTO(c)).collect(Collectors.toList());
        return list;
    }

    @Override
    public ClazzDTO createClass(Clazz c){
        logger.info("Invoked create Class");
        logger.info("Object: " + c);
        return new ClazzDTO(classRepo.save(c));
    }

    @Override
    public void deleteClassById(Long id) {
        logger.info("Invoked delete Class");
        if (classRepo.findById(id).get() != null) {
            classRepo.deleteById(id);
        } else {
            logger.error("element not found");
        }

    }

    @Override
    public ClazzDTO updateClass(Long id, Clazz c) {
        if (classRepo.findById(id).get() != null) {
            Clazz clazz = classRepo.findById(id).get();
            clazz.setClass_name(c.getClass_name());
            classRepo.save(clazz);
            return new ClazzDTO(clazz);
        } else {
            logger.error("Element Not Found");
        }

        return null;
    }


}
