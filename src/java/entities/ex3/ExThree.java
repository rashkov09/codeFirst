package entities.ex3;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ExThree implements Exercise {
    @Override
    public void run() {
        EntityManager em = Persistence
                .createEntityManagerFactory("uniSystem")
                .createEntityManager();

        //test student

        Student student = new Student();
        student.setFirstName("Todor");
        student.setLastName("Todorov");
        student.setPhoneNumber("0888333222");
        student.setAttendance(3);
        student.setAvgGrade(5.4);

        //test teacher
        Teacher teacher = new Teacher();
        teacher.setFirstName("Emil");
        teacher.setLastName("Hristov");
        teacher.setEmail("ehristov@gmail.com");
        teacher.setPhoneNumber("099932222");
        teacher.setSalary(3300.33);

        //test course
        Course course = new Course();
        course.setName("OOP");
        course.setDescription("Object Oriented Programming");
        course.setTeacher(teacher);
        course.setCredits(50);
        course.setStartDate(LocalDateTime.of(2021,4,28,22,33));
        course.setEndDate(LocalDateTime.of(2021,5,28,22,33));
        Set<Student> students = new HashSet<>();
        students.add(student);
        course.setStudents(students);

        em.getTransaction().begin();

        em.persist(student);
        em.persist(teacher);
        em.persist(course);

        em.getTransaction().commit();


    }


}
