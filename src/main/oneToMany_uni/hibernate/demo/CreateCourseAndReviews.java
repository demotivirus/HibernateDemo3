package main.oneToMany_uni.hibernate.demo;

import main.oneToMany_uni.hibernate.entity.Course;
import main.oneToMany_uni.hibernate.entity.Instructor;
import main.oneToMany_uni.hibernate.entity.InstructorDetail;
import main.oneToMany_uni.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviews {

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

            //Create the course
            Course course = new Course("Monkey it's a funky");

            //Add reviews
            course.addReview(new Review("Not bad"));
            course.addReview(new Review("Great"));
            course.addReview(new Review("Wonderful"));

            //Save the course & all cascade table it's save
            System.out.println("Saving the course");
            System.out.println(course);
            System.out.println(course.getReviews());

            session.save(course);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}