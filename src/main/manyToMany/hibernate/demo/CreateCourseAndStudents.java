package main.manyToMany.hibernate.demo;

import main.manyToMany.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudents {

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

            //Create the course
            Course course = new Course("How live in a Mexico");

            //Save the course
            System.out.println("Saving the course...");
            session.save(course);
            System.out.println("Course saved: " + course);

            //Create students
            Student student = new Student("Mark", "Twain", "markTwain@gmail.com");
            Student student1 = new Student("Albert", "Enshtein", "albertEnshtein@google.com");

            //Add students to the course
            course.addStudents(student);
            course.addStudents(student1);

            //Save the students
            System.out.println("Saving the students...");
            session.save(student);
            session.save(student1);
            System.out.println("Students saved: " + course.getStudents());

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}