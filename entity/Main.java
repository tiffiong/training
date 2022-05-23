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
//        addToEnrollment(entityManager);
//
//        getClassById(entityManager);
//        updateStudentAge(entityManager);
//
//
//        orphanRemove(entityManager);
        removeSingleRelation(entityManager);


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
        clazz.setClass_name("English");

        Student student = new Student();
        //new student
//        em.persist(student);

        student.setAge(27);
        student.setLast_name("Dickson");
        student.setFirst_name("Matthew");
        //existed student
        student.setStudentId(128);
        em.merge(student);

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



    private static  void removeSingleRelation(EntityManager em){
        //orphanRemoval = false;
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("select c from Clazz c left join fetch c.enrollmentSet e where c.class_id =?1");
        query.setParameter(1, 226);

        Clazz c = (Clazz) query.getSingleResult();
        for(Enrollment e: c.getEnrollmentSet()) {
            em.remove(e);
        }
        c.setEnrollmentSet(new ArrayList<>());
        transaction.commit();

    }

    private static void orphanRemove(EntityManager em){
        //orphanRemoval = true;
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Clazz c = em.find(Clazz.class, 223);
        c.getEnrollmentSet().remove(0);
        transaction.commit();


    }

}
