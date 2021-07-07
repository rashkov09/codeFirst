package entities.ex3;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")

public class Teacher extends User {
    private String email;
    private Double salary;
    private Set<Course> courses;

    public Teacher() {
    }


    @Column(name = "email", nullable = false, unique = true)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salary")

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @OneToMany(mappedBy = "teacher")

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
