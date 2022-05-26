package com.tiffanyiong.springbootrestful.repository;

import com.tiffanyiong.springbootrestful.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepo extends JpaRepository<Clazz, Long> {


    @Query(value = "select c from Clazz c")
    List<Clazz> getAllClasses();

    @Query(value = "select c from Clazz c where c.class_id = ?1")
    List<Clazz> getClassById(Long id);







}
