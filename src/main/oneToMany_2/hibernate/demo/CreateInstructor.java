package main.oneToMany_2.hibernate.demo;

import main.oneToMany_2.hibernate.entity.Course;
import main.oneToMany_2.hibernate.entity.Instructor;
import main.oneToMany_2.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {

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

            Instructor instructor = new Instructor("Bond", "James Bond", "007@agent.com");
            InstructorDetail instructorDetail =
                    new InstructorDetail("777", "Lock, Stock and Two Smoking Barrels");

            //Associate obj
            instructor.setInstructorDetail(instructorDetail);

            //Start transaction
            session.beginTransaction();

            //Save the instructor
            //All it's save because CascadeType.ALL
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
