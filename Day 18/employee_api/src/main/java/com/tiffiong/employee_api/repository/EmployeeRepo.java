package com.tiffiong.employee_api.repository;

import com.tiffiong.employee_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(value ="select e from Employee e where e.age > ?1")
    List<Employee> getEmployeeWhoIsOlder(Integer age);

    @Query(value ="select e from Employee e where e.age = ?1")
    List<Employee> groupByAge(Integer age);
}
