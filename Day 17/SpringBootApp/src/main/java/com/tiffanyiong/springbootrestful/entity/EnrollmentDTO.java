package com.tiffanyiong.springbootrestful.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private Long class_id;
    private Long enroll_id;
    private Long student_id;

    public EnrollmentDTO(Enrollment e) {
        if (e.getClass() == null){
            this.class_id = null;
        } else {
            this.class_id = e.getClazz().getClass_id();
        }

        this.enroll_id = e.getEnroll_id();
        if (e.getStudent() != null) {
            this.student_id = e.getStudent().getStudent_id();
        } else {
            this.student_id = null;
        }

    }
}
