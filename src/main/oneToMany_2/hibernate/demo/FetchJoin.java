package main.oneToMany_2.hibernate.demo;

import main.oneToMany_2.hibernate.entity.Course;
import main.oneToMany_2.hibernate.entity.Instructor;
import main.oneToMany_2.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoin {

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

            //Start transaction
            session.beginTransaction();

            //Get QUERY the instructor from DB
            int theId = 2;
            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                            + "join fetch i.courses " + "where i.id=:instructorId",
                            Instructor.class);

            //Set param in query
            query.setParameter("instructorId", theId);

            //Execute query & get Instructor
            Instructor instructor = query.getSingleResult();

            //Get courses for the instructor
            System.out.println("Query Instructor: " + instructor);
            System.out.println("Query Courses: " + instructor.getCourses());

            //Commit transaction
            session.getTransaction().commit();

            session.close();
            System.out.println("\nNow session is closed\n");
            System.out.println("Query Courses: " + instructor.getCourses());

            System.out.println("Query Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}