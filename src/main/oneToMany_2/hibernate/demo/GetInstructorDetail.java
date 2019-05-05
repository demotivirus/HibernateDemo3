package main.oneToMany_2.hibernate.demo;

import main.oneToMany_2.hibernate.entity.Instructor;
import main.oneToMany_2.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetail {

    public static void main(String[] args) {

    //Create session factory
    SessionFactory sessionFactory = new Configuration()
            .configure("main/oneToMany_2/hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    //Create session
    Session session = sessionFactory.getCurrentSession();

    try{

        //Start transaction
        session.beginTransaction();

        //Get the instructor detail
        int theId = 2;
        InstructorDetail instructorDetail =
                session.get(InstructorDetail.class, theId);

        //Print the instructor detail
        System.out.println("Instructor detail: " + instructorDetail);

        //Print the associated instructor
        System.out.println("The associated instructor " + instructorDetail.getInstructor());

        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Done!");

    } catch (Exception ex){
        ex.printStackTrace();
    } finally {
        session.close();
        sessionFactory.close();
    }
}
}