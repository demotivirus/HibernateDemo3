package main.oneToMany_2.hibernate.demo;

import main.oneToMany_2.hibernate.entity.Course;
import main.oneToMany_2.hibernate.entity.Instructor;
import main.oneToMany_2.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazy {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/oneToMany_2/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //Create session
        Session session = sessionFactory.getCurrentSession();

        try{

            //Start transaction
            session.beginTransaction();

            //Get the instructor from DB
            int theId = 2;
            Instructor instructor = session.get(Instructor.class, theId);

            //Get courses for the instructor
            System.out.println("Eager Instructor: " + instructor);
            System.out.println("Eager Courses: " + instructor.getCourses());

            

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Eager Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}