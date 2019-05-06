package main.oneToMani_uni.hibernate.demo;

import main.oneToMani_uni.hibernate.entity.Course;
import main.oneToMani_uni.hibernate.entity.Instructor;
import main.oneToMani_uni.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourse {

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

            //Get the instructor from DB
            int theId = 2;
            Instructor instructor = session.get(Instructor.class, theId);

            //Create courses
            Course course = new Course("Green screen fights");
            Course course1 = new Course("Bullets & Guns");
            Course course2 = new Course("Ho to become the star");

            //Add courses to instructor
            instructor.add(course);
            instructor.add(course1);
            instructor.add(course2);

            //Save courses
            session.save(course);
            session.save(course1);
            session.save(course2);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}