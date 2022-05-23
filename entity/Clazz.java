package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {


    private String class_name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer class_id;


    @OneToMany(mappedBy = "clazz", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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
        return "Clazz{" +
                "class_name='" + class_name + '\'' +
                ", class_id=" + class_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return Objects.equals(class_name, clazz.class_name) && Objects.equals(class_id, clazz.class_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(class_name, class_id);
    }
}
