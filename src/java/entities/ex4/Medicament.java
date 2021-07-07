package entities.ex4;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {
    private String name;
    private Diagnose diagnose;


    public Medicament() {
    }

    public Medicament(String name) {
        setName(name);
    }

    @Column(name = "name")

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @ManyToOne

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }
}
