package main.oneToMany_1.hibernate.demo;

import main.oneToMany_1.hibernate.entity.Instructor;
import main.oneToMany_1.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/oneToMany_1/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //Create session
        Session session = sessionFactory.getCurrentSession();

        try{

            //Create the objects
            Instructor instructor = new Instructor("Chack", "Norris", "dontDisturb@mrNorris.com");
            InstructorDetail instructorDetail =
                    new InstructorDetail("hollywood@ismyland.com", "break the wall");

            Instructor instructor2 = new Instructor("Bender", "Rodriguez", "cissMyMetal@shiny.ass");
            InstructorDetail instructorDetail2 =
                    new InstructorDetail("asino777", "Smoking cigars");

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
        }
    }
}
