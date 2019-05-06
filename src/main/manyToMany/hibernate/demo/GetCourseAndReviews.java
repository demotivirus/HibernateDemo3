package main.manyToMany.hibernate.demo;

import main.manyToMany.hibernate.entity.Course;
import main.manyToMany.hibernate.entity.Instructor;
import main.manyToMany.hibernate.entity.InstructorDetail;
import main.manyToMany.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviews {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/manyToMany/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //Create session
        Session session = sessionFactory.getCurrentSession();

        try{

            //Start transaction
            session.beginTransaction();

            //Get the course
            int tempId = 10;
            Course course = session.get(Course.class, tempId);

            //Print the course
            System.out.println("\nCourse is: " + course);

            //Print the course reviews
            System.out.println("\nReviews is: " + course.getReviews());

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}