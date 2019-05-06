package main.manyToMany.hibernate.demo;

import main.manyToMany.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMarkTwain {

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

            //Create more courses for him
            Course course = new Course("How work 4 hours in week");
            Course course1 = new Course("Business for foreigners");

            //Add student to courses
            course.addStudents(student);
            course1.addStudents(student);

            //Save the courses
            System.out.println("Saving...");
            session.save(course);
            session.save(course1);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}