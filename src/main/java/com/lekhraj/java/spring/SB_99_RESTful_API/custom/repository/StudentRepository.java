package com.lekhraj.java.spring.SB_99_RESTful_API.custom.repository;

import com.lekhraj.java.spring.SB_99_RESTful_API.entities.Student;
import com.lekhraj.java.spring.SB_99_RESTful_API.model.GenderEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class StudentRepository {
    @Autowired
    EntityManagerFactory session;

    public Student getStudent(String name){
        EntityManager em = session.createEntityManager();

        TypedQuery<Student> q = em.createQuery("select s from Student s where s.name=:sname", Student.class);
        q.setParameter("sname",name);

        TypedQuery<Student> nq = em.createNamedQuery("Student.findStudentByName", Student.class);
        nq.setParameter("sname",name);

        Student result =  nq.getSingleResult();
        em.close();
        return result;
    }

    public Student addStudent(String name){
        Student s = Student.builder()
                .age(32)
                .uuid(UUID.randomUUID())
                .gender(GenderEnum.FEMALE)
                .birthDate(LocalDate.of(1991, 5, 18))
                .name(name)
                .build();
        EntityManager em = session.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(s);
        transaction.commit();
        return s;
    }
}
