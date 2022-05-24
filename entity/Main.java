package com.tiffanyiong.demo.entity;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    private DataSource getDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/courses");
        dataSource.setUsername("root");
        dataSource.setPassword("Jj31272932");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver" );
//        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource);
        lemfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        lemfb.setJpaProperties( hibernateProperties);
        lemfb.setPackagesToScan("com.tiffanyiong.demo.entity");
        lemfb.setPersistenceUnitName( "demo-unit" );
        lemfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lemfb.afterPropertiesSet();
        return lemfb.getObject();
    }

    public static void main(String[] args) {
        Main mainDemo = new Main();
        DataSource dataSource = mainDemo.getDataSource();
        Properties properties =mainDemo.getProperties();
        EntityManagerFactory entityManagerFactory = mainDemo.entityManagerFactory(dataSource, properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

//        insertToClass(entityManager);
//        addToEnrollment(entityManager);
//
        getClassById(entityManager);
//        updateStudentAge(entityManager);
//
//
//        orphanRemove(entityManager);
      //  removeSingleRelation(entityManager);


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
