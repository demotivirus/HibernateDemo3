package main.oneToMani_uni.hibernate.demo;

import main.oneToMani_uni.hibernate.entity.Course;
import main.oneToMani_uni.hibernate.entity.Instructor;
import main.oneToMani_uni.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorCourses {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/oneToMany_uni/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //Create session
        Session session = sessionFactory.getCurrentSession();

        try{

            //Start transaction
            session.beginTransaction();

            //Get a course
            int theId = 10;
            Course course = session.get(Course.class, theId);

            //Delete course
            System.out.println("Deleting courses: " + course);
            session.delete(course);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}