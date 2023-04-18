package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
@Repository //db ye erisilen class, component'in gelismisi
public class StudentRepositoryImpl implements StudentRepository{

   @Autowired
   private SessionFactory sessionFactory;


    @Override
    public void save(Student student) {
        Session session=sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(student); //db de böyle bir kayit varsa update, yoksa kaydetme islemi yapar


        tx.commit();
        session.close();
    }

    @Override
    public List<Student> findAll() {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

        List<Student> studentList=session.createQuery("FROM Student",Student.class).getResultList();

        transaction.commit();
        session.close();

        return studentList;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

       Student student=session.get(Student.class,id);
       Optional<Student> opt =Optional.ofNullable(student); //student yoksa ici bos bir optional objesi döndürür. NullPointerException almayiz.

        transaction.commit();
        session.close();

        return opt;
    }

    @Override
    public void delete(Long id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

        Student student= session.load(Student.class,id); //silecegimiz objeyi proxy olarak getirmesi yeterli
        session.delete(student);

        transaction.commit();
        session.close();


    }
}
