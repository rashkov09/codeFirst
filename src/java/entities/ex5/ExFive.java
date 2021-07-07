package entities.ex5;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ExFive implements Exercise {
    @Override
    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("billsPaymentSystem").createEntityManager();
    }
}
