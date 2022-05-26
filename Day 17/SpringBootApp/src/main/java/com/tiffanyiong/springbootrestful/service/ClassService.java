package com.tiffanyiong.springbootrestful.service;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import com.tiffanyiong.springbootrestful.entity.ClazzDTO;
import com.tiffanyiong.springbootrestful.entity.Enrollment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
//    Clazz getClassById(Integer id);
    List<ClazzDTO> getAllClasses();
    List<ClazzDTO> getClassById(Long id);

    ClazzDTO createClass (Clazz c);

    void deleteClassById (Long id);

    ClazzDTO updateClass(Long id, Clazz c);



}
