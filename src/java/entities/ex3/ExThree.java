package entities.ex3;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ExThree implements Exercise {
    @Override
    public void run() {
        EntityManager em = Persistence
                .createEntityManagerFactory("uniSystem")
                .createEntityManager();
    }


}
