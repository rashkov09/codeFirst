package exercises;

import iface.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ExerciseImpl implements Exercise {
    private String persistenceUnit;
    private EntityManager manager;

    public ExerciseImpl(String persistenceUnit) {
        setManager(persistenceUnit);

    }

    public String getPersistenceUnit() {
        return persistenceUnit;
    }

    public void setPersistenceUnit(String persistenceUnit) {
        this.persistenceUnit = persistenceUnit;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(String unit) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(unit);
        this.manager = factory.createEntityManager();
    }
}
