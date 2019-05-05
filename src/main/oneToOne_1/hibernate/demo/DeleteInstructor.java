package main.oneToOne_1.hibernate.demo;

import main.oneToOne_1.hibernate.entity.Instructor;
import main.oneToOne_1.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructor {

    public static void main(String[] args) {

        //Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("main/oneToOne_1/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //Create session
        Session session = sessionFactory.getCurrentSession();

        try{

            //Start transaction
            session.beginTransaction();

            //Get instructor by id
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor: " + instructor);

            //Delete the instructors
            if(instructor != null){
                System.out.println("Deleting: " + instructor);

                //Delete all associate obj
                session.delete(instructor);
            }

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
        }
    }
}
