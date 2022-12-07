package yungshun.chang.hibernatelms;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import yungshun.chang.hibernatelms.entity.*;

public class LMS {

    public static void main(String[] args) {

        // [1] Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
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
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the instructor detail object
            int id = 1;
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, id);

            // Print the instructor detail
            System.out.println("tmpInstructorDetail: " + tmpInstructorDetail);

            // Print  the associated instructor
            System.out.println("the associated instructor: " + tmpInstructorDetail.getInstructor());

            // [5] Commit transaction
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

        /* Delete `InstructorDetail` & `Instructor` (one-to-one bi-directional mapping with CascadeType.ALL)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the instructor detail object
            int id = 2;
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, id);

            // Print the instructor detail
            System.out.println("tmpInstructorDetail: " + tmpInstructorDetail);

            // Print the associated instructor
            System.out.println("the associated instructor: " + tmpInstructorDetail.getInstructor());

            // [5] Delete the instructor detail
            System.out.println("Deleting tmpInstructorDetail: " + tmpInstructorDetail);

            session.delete(tmpInstructorDetail);

            // [6] Commit transaction
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

        /* Delete `InstructorDetail` & update `Instructor` (one-to-one bi-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the instructor detail object
            int id = 3;
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, id);

            // Print the instructor detail
            System.out.println("tmpInstructorDetail: " + tmpInstructorDetail);

            // Print the associated instructor
            System.out.println("the associated instructor: " + tmpInstructorDetail.getInstructor());

            // Delete the instructor detail
            System.out.println("Deleting tmpInstructorDetail: " + tmpInstructorDetail);

            // [5] Remove the associated object reference & break bi-directional link
            tmpInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tmpInstructorDetail);

            // [6] Commit transaction
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

        /* Create `Instructor` (one-to-many bi-directional mapping) & `Course` (many-to-one bi-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Create instructor
            Instructor tmpInstructor = new Instructor("Shannon", "Chang", "shannonchang@gmail.com");

            InstructorDetail tmpInstructorDetail = new InstructorDetail("https://www.youtube.com", "Cardio");
            tmpInstructor.setInstructorDetail(tmpInstructorDetail);

            session.save(tmpInstructor);

            // [5] Create some courses
            Course tmpCourse1 = new Course("Cardio Boxing");
            Course tmpCourse2 = new Course("Tai Chi");

            // [6] Add courses to instructor
            tmpInstructor.add(tmpCourse1);
            tmpInstructor.add(tmpCourse2);

            // [7] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [8] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Create `Course` (many-to-one bi-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the instructor from db
            int id = 1;
            Instructor tmpInstructor = session.get(Instructor.class, id);

            // [5] Create some courses
            Course tmpCourse1 = new Course("Mountain Climbing");
            Course tmpCourse2 = new Course("Trekking");

            // [6] Add courses to instructor
            tmpInstructor.add(tmpCourse1);
            tmpInstructor.add(tmpCourse2);

            // [7] Save the courses
            session.save(tmpCourse1);
            session.save(tmpCourse2);

            // [8] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [9] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Get `Instructor` & `Course` (one-to-many bi-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the instructor from db
            int id = 1;
            Instructor tmpInstructor = session.get(Instructor.class, id);
            System.out.println("Instructor: " + tmpInstructor);

            // [5] Get courses for the instructor
            System.out.println("Courses: " + tmpInstructor.getCourses());

            // [6] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [7] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Delete `Course` (many-to-one bi-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get a course
            int id = 10;
            Course tmpCourse = session.get(Course.class, id);

            // [5] Delete course
            System.out.println("Deleting course: " + tmpCourse);

            session.delete(tmpCourse);

            // [6] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [7] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Create `Course` & `Review` (one-to-many uni-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Create a course
            Course tmpCourse = new Course("High-intensity Interval Training");

            // [5] Add some reviews
            tmpCourse.addReview(new Review("Great course. Loved it!"));
            tmpCourse.addReview(new Review("Cool course, job well done!"));
            tmpCourse.addReview(new Review("What a dumb course, you are an idiot!"));

            // [6] Save the course. And leverage the cascade all.
            System.out.println("Saving the course");

            session.save(tmpCourse);

            // [7] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [8] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Get `Course` & `Review` (one-to-many uni-directional mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the course
            int id = 1;
            Course tmpCourse = session.get(Course.class, id);

            // [5] Print the course
            System.out.println(tmpCourse);

            // [6] Print the course reviews
            System.out.println(tmpCourse.getReviews());

            // [7] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [8] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Delete `Course` & `Review` (one-to-many uni-directional mapping with CascadeType.ALL)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the course
            int id = 1;
            Course tmpCourse = session.get(Course.class, id);

            // [5] Print the course
            System.out.println("Deleting the course");
            System.out.println(tmpCourse);

            // [6] Print the course reviews
            System.out.println("Deleting the reviews");
            System.out.println(tmpCourse.getReviews());

            // [7] Delete the course
            session.delete(tmpCourse);

            // [8] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [9] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Create `Course` & `Student` (Many-to-many mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Create a course
            Course tmpCourse = new Course("Tabata");

            // [5] Save the course
            System.out.println("Saving the course");
            session.save(tmpCourse);
            System.out.println("Saved the course: " + tmpCourse);

            // [6] Create the students
            Student tmpStudent1 = new Student("John", "Doe", "johndoe@gmail.com");
            Student tmpStudent2 = new Student("Blue", "Mary", "bluemary@gmail.com");

            // [7] Add students to the course
            tmpCourse.addStudent(tmpStudent1);
            tmpCourse.addStudent(tmpStudent2);

            // [8] Save the students
            System.out.println("Saving students");
            session.save(tmpStudent1);
            session.save(tmpStudent2);
            System.out.println("Saved students: " + tmpCourse.getStudents());

            // [9] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [10] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Get `Course` from `Student` (many-to-many mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the student from database
            int id = 1;
            Student tmpStudent = session.get(Student.class, id);

            System.out.println("Loaded student: " + tmpStudent);
            System.out.println("Courses: " + tmpStudent.getCourses());

            // [5] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [6] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Delete `Student` (many-to-many mapping) & relationship in the `course_student` table
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the student from database
            int id = 2;
            Student tmpStudent = session.get(Student.class, id);

            System.out.println("Loaded student: " + tmpStudent);
            System.out.println("Courses: " + tmpStudent.getCourses());

            // [5] Delete student
            System.out.println("Deleting student: " + tmpStudent);
            session.delete(tmpStudent);

            // [6] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [7] Add clean up code
            session.close();

            factory.close();
        }
         */

        /* Create `Course` for `Student` (many-to-many mapping)
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the student from database
            int id = 1;
            Student tmpStudent = session.get(Student.class, id);

            System.out.println("Loaded student: " + tmpStudent);
            System.out.println("Courses: " + tmpStudent.getCourses());

            // [5] Create more courses
            Course tmpCourse1 = new Course("Myofascial Release");
            Course tmpCourse2 = new Course("High-intensity Interval Training");

            // [6] Add student to course
            tmpCourse1.addStudent(tmpStudent);
            tmpCourse2.addStudent(tmpStudent);

            // [7] Save the courses
            System.out.println("Saving the course");

            session.save(tmpCourse1);
            session.save(tmpCourse2);

            // [8] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [9] Add clean up code
            session.close();

            factory.close();
        }
         */

        // Delete `Course` (many-to-many mapping) & relationship in the `course_student` table
        try {
            // [3] Start a transaction
            session.beginTransaction();

            // [4] Get the course from database
            int id = 1;
            Course tmpCourse = session.get(Course.class, id);

            // [5] Delete the course
            System.out.println("Deleting course: " + tmpCourse);

            session.delete(tmpCourse);

            // [6] Commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            // [7] Add clean up code
            session.close();

            factory.close();
        }
    }
}
