package entities.ex1;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public  class ExOne implements Exercise {

    @Override
    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("gringotts").createEntityManager();

        //test wizard
        WizardDeposits wd = new WizardDeposits();
        wd.setAge(23);
        wd.setDepositAmount(3333.333);
        wd.setDepositCharge(23.33);
        wd.setDepositExpirationDate(LocalDateTime.of(2021,6,28,12,1));
        wd.setDepositGroup("Fire");
        wd.setDepositInterest(2.33);
        wd.setDepositStartDate(LocalDateTime.now());
        wd.setFirstName("Tim");
        wd.setDepositExpired(false);
        wd.setLastName("Timson");
        wd.setMagicWandCreator("Creator");
        wd.setMagicWandSize(45);
        wd.setNotes("Very powerful");

        em.getTransaction().begin();
        em.persist(wd);
        em.getTransaction().commit();
    }
}
