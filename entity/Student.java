package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Table(name = "student")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String first_name;

    private String last_name;

    private Integer age;


    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Enrollment> enrollmentSet = new ArrayList<>();

    public List<Enrollment> getEnrollmentSet() {
        return enrollmentSet;
    }
    public void setEnrollmentSet(List<Enrollment> en){
        this.enrollmentSet = en;
    }

    public void addEnrollment(Enrollment en){
        this.enrollmentSet.add(en);
    }




    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(first_name, student.first_name) && Objects.equals(last_name, student.last_name) && Objects.equals(age, student.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, first_name, last_name, age);
    }
}
