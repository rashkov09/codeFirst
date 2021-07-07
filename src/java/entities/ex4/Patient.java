package entities.ex4;

import entities.BaseEntity;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Check(constraints = "has_medical_insurance IN ('yes','no')")
@Table(name = "patients")

public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDateTime dateOfBirth;
    private Blob picture;
    private String hasMedicalInsurance;
    private Set<Visitation> visitations;



    public Patient() {
    }

    public Patient(String firstName, String lastName, String address, String email, LocalDateTime dateOfBirth, Blob picture, String hasMedicalInsurance) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setPicture(picture);
        setHasMedicalInsurance(hasMedicalInsurance);
    }

    @Column(name = "first_name", nullable = false)

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address", nullable = false)

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "email", unique = true, nullable = false)

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth", nullable = false)

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "picture", nullable = true)

    public Blob getPicture() {
        return picture;
    }

    private void setPicture(Blob picture) {
        this.picture = picture;
    }

    @Column(name = "has_medical_insurance", nullable = false)


    public String getHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    private void setHasMedicalInsurance(String hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
    @OneToMany(mappedBy = "patient")

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }
}
