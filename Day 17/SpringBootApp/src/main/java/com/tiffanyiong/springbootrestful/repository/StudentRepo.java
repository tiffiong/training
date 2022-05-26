package com.tiffanyiong.springbootrestful.repository;

import com.tiffanyiong.springbootrestful.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query(value ="select s from Student s where s.student_id = ?1")
    List<Student> getStudentById(Long id);
}
