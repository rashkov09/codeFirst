package entities.ex4;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {
    private String name;
    private String comments;
    private Set<Medicament> medicaments;
    private Set<Visitation> visitations;

    public Diagnose() {
    }

    public Diagnose(String name, String comments) {
        setName(name);
        setComments(comments);
    }

    @Column(name ="name", nullable = false)

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    private void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToMany

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }


    @OneToMany(mappedBy = "diagnose")

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }
}
