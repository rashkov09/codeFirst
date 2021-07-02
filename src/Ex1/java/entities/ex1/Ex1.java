package entities.ex1;

import entities.ex3.Person;
import entities.ex3.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ex1 {
    public static void main(String[] args) {
        EntityManagerFactory ee = Persistence.createEntityManagerFactory("gringotts");
        EntityManager e = ee.createEntityManager();
        Person  test = new Student("Yordan","rashkov","391283123",2.3,1);

        e.persist(test);
    }
}
