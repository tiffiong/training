package com.tiffanyiong.demo.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "enrollment")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Enrollment {
    public Enrollment(Clazz clazz, Student student) {
        this.clazz = clazz;
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Clazz clazz;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private Student student;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enroll_id;


    @Override
    public String toString() {
        return "Enrollment{" +
                "clazz=" + clazz +
                ", student=" + student +
                ", enroll_id='" + enroll_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(clazz, that.clazz) && Objects.equals(student, that.student) && Objects.equals(enroll_id, that.enroll_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, student, enroll_id);
    }
}
