package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        insertToClass(entityManager);
 //       addToEnrollment(entityManager);
        getClassById(entityManager);
  //      updateStudent(entityManager);
    //      readStudentAge(entityManager);
 //         removeRelation(entityManager);


    }


    private static void insertToClass(EntityManager entityManager){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Clazz clazz = new Clazz();
        clazz.setClass_name("Math");
        entityManager.persist(clazz);

        entityManager.persist(clazz);
        transaction.commit();
    }

    private static void addToEnrollment(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Clazz clazz = new Clazz();
        clazz.setClass_name("Math");

        Student student = new Student();
        em.persist(student);

        student.setAge(27);
        student.setLast_name("Dickson");
        student.setFirst_name("Matthew");

        Enrollment enrollment = new Enrollment();
        enrollment.setClazz(clazz);
        enrollment.setStudent(student);
        clazz.addEnrollment(enrollment);
        student.addEnrollment(enrollment);

        em.persist(clazz);
        transaction.commit();
    }

    private static void updateStudentAge(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Enrollment enroll = em.find(Enrollment.class, 1);
        //125,Hart,Stoll,23  ---> 17
        enroll.getStudent().setAge(17);
        transaction.commit();
    }

    private static void getClassById(EntityManager em) {
//        Clazz c = em.find(Clazz.class, 202);
//        System.out.println(c);

        Query query = em.createQuery("select c from Clazz c left join fetch c.enrollmentSet e where c.class_id =?1");
        query.setParameter(1, 224);
        Clazz c = (Clazz) query.getSingleResult();
        System.out.println(c);
    }

    private static void readStudentAge(EntityManager em) {
        Query query = em.createQuery("select s.first_name, s.age from Student s where s.age > ?1");
        query.setParameter(1, 20);
        List<Student> result = query.getResultList();
        System.out.println(result);

    }


    private static  void removeRelation(EntityManager em){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query =  em.createNativeQuery("select c from Clazz c join fetch c.enrollmentSet cenroll where c.class_id = ?1");
        query.setParameter(1, 222);
        Clazz c = (Clazz) query.getSingleResult();
        Student s = em.find(Student.class, 125);
        List<Enrollment> enroll = new ArrayList<>();
        for (Enrollment e : c.getEnrollmentSet()){
            if(e.getStudent().getStudentId().equals(s.getStudentId())) {
                enroll.add(e);
                System.out.println(e.getEnroll_id());
                em.remove(e);
            }
        }
        c.getEnrollmentSet().removeAll(enroll);
        s.getEnrollmentSet().removeAll(enroll);
        transaction.commit();

    }

}
