package entities.ex3;

import javax.persistence.Column;
import java.math.BigDecimal;

public class Teacher extends Person {
    private static final String type = "teacher";
    private String email;
    private BigDecimal salaryPerHour;

    public Teacher(String firstName, String lastName, String phoneNumber, String email, BigDecimal salaryPerHour) {
        super(firstName, lastName, phoneNumber, type);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }

    @Column(name= "email" )

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salary_per_hour")

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
