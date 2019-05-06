package main.oneToMany_uni.hibernate.demo;

import main.oneToMany_uni.hibernate.entity.Course;
import main.oneToMany_uni.hibernate.entity.Instructor;
import main.oneToMany_uni.hibernate.entity.InstructorDetail;
import main.oneToMany_uni.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviews {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/oneToMany_uni/hibernate.cfg.xml")
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

            //Delete the cource
            System.out.println("Deleting the course");
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