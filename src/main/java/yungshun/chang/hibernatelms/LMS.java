package yungshun.chang.hibernatelms;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import yungshun.chang.hibernatelms.entity.Instructor;
import yungshun.chang.hibernatelms.entity.InstructorDetail;

public class LMS {

    public static void main(String[] args) {

        // [1] Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // [2] Create session
        Session session = factory.getCurrentSession();

        /* Create `Instructor` & `InstructorDetail` (one-to-one uni-directional mapping)
        try {
            // [3] Create the objects
            Instructor tmpInstructor = new Instructor("Joe", "Johnson", "joejohnson@gmail.com");

            InstructorDetail tmpInstructorDetail = new InstructorDetail("https://www.youtube.com", "Hiking");

            // [4] Associate the objects
            tmpInstructor.setInstructorDetail(tmpInstructorDetail);

            // [5] Start a transaction
            session.beginTransaction();

            // [6] Save the instructor
            // This will also save the details object because of CascadeType.ALL
            System.out.println("Saving instructor: " + tmpInstructor);
            session.save(tmpInstructor);

            // [7] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            factory.close();
        }
         */

        /* Delete `Instructor` & `InstructorDetail` (one-to-one uni-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get instructor by primary key / id
            int id = 1;
            Instructor tmpInstructor = session.get(Instructor.class, id);
            System.out.println("Found instructor: " + tmpInstructor);

            // [5] Delete the instructors
            // Also delete associated `InstructorDetail` object because of CascadeType.ALL
            if (tmpInstructor != null) {
                System.out.println("Deleting: " + tmpInstructor);
                session.delete(tmpInstructor);
            }

            // [6] Commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");
        } finally {
            factory.close();
        }
         */

        /* Get `InstructorDetail` & `Instructor` (one-to-one bi-directional mapping)
        try {
            // Start a transaction
            session.beginTransaction();

            // Get the instructor detail object
            int id = 1;
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, id);

            // Print the instructor detail
            System.out.println("tmpInstructorDetail: " + tmpInstructorDetail);

            // Print  the associated instructor
            System.out.println("the associated instructor: " + tmpInstructorDetail.getInstructor());

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            // Handle connection leak issue
            session.close();
            factory.close();
        }
         */

        // Delete `InstructorDetail` & `Instructor` (one-to-one bi-directional mapping)
        try {
            // Start a transaction
            session.beginTransaction();

            // Get the instructor detail object
            int id = 2;
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, id);

            // Print the instructor detail
            System.out.println("tmpInstructorDetail: " + tmpInstructorDetail);

            // Print  the associated instructor
            System.out.println("the associated instructor: " + tmpInstructorDetail.getInstructor());

            // Delete the instructor detail
            System.out.println("Deleting tmpInstructorDetail: " + tmpInstructorDetail);

            session.delete(tmpInstructorDetail);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            // Handle connection leak issue
            session.close();

            factory.close();
        }
    }
}
