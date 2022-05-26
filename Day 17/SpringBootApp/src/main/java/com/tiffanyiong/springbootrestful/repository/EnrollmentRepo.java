package com.tiffanyiong.springbootrestful.repository;


import com.tiffanyiong.springbootrestful.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {

    @Query(value = " select e from Enrollment e where e.enroll_id=?1")
    List<Enrollment> getEnrollmentBy(Long id);

}
