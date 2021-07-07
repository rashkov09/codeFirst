package entities.ex4;

import entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {
    private LocalDateTime date;
    private String comments;
    private Patient patient;
    private Diagnose diagnose;

    public Visitation() {
    }

    public Visitation(LocalDateTime date, String comments) {
        setDate(date);
        setComments(comments);
    }

    @Column(name = "date", nullable = false)

    public LocalDateTime getDate() {
        return date;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "comments")

    public String getComments() {
        return comments;
    }

    private void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToOne

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }
}
