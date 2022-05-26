package com.tiffanyiong.springbootrestful.service;

import com.tiffanyiong.springbootrestful.entity.Student;
import com.tiffanyiong.springbootrestful.entity.StudentDTO;
import com.tiffanyiong.springbootrestful.repository.StudentRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Log logger = LogFactory.getLog(StudentServiceImpl.class);
    private final StudentRepo studentRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public StudentServiceImpl(StudentRepo student, RestTemplate restTemplate) {
        this.studentRepo = student;
        this.restTemplate = restTemplate;
    }


    @Override
    public StudentDTO createStudent(Student stu_detail) {
        logger.info("Invoked createStudent()");
        return new StudentDTO(studentRepo.save(stu_detail));
    }

    @Override
    public List<StudentDTO> getAllStudent() {
       return studentRepo.findAll().stream().map(s -> new StudentDTO(s)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentById(Long id) {
        return studentRepo.getStudentById(id).stream().map(s -> new StudentDTO(s)).collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(Long id) {
        logger.info("=======Invoke deleteStudentById======");
        if (studentRepo.findById(id) != null) {
            studentRepo.deleteById(id);
        } else {
            logger.error("Not Found");
        }
    }

    @Override
    public StudentDTO updateStudent(Long id, Student stu_detail) {
        logger.info("========update student========");
        if (studentRepo.findById(id).get() == null) {
            return null;
        }
        Student stu = studentRepo.findById(id).get();
        stu.setAge(stu_detail.getAge());
        stu.setFirst_name(stu_detail.getFirst_name());
        stu.setLast_name(stu_detail.getLast_name());
        studentRepo.save(stu);
        return new StudentDTO(stu);
    }
}
