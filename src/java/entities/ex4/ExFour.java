package entities.ex4;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ExFour implements Exercise {
    @Override
    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("hospital").createEntityManager();

        Engine engine = new Engine(em);

        engine.run();

    }
}
