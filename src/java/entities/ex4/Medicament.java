package entities.ex4;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {
    private String name;
    private Set<Diagnose> diagnose;


    public Medicament() {
    }

    public Medicament(String name) {
        setName(name);
    }

    @Column(name = "name", nullable = false)

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "medicaments")

    public Set<Diagnose> getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Set<Diagnose> diagnose) {
        this.diagnose = diagnose;
    }
}
