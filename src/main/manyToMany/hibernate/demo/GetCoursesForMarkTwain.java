package main.manyToMany.hibernate.demo;

import main.manyToMany.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForMarkTwain {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/manyToMany/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //Create session
        Session session = sessionFactory.getCurrentSession();

        try{

            //Start transaction
            session.beginTransaction();

            //Get a student Mark Twain to DB
            int studentId = 1;
            Student student = session.get(Student.class, studentId);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Course: " + student.getCourses());

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}