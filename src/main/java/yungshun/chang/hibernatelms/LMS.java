package yungshun.chang.hibernatelms;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LMS {

    public static void main(String[] args) {

        // [1] Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // [2] Create session
        Session session = factory.getCurrentSession();

        try {

        } finally {
            factory.close();
        }
    }
}
