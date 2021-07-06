package entities.ex1;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public  class ExOne implements Exercise {

    @Override
    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("gringotts").createEntityManager();
    }
}
