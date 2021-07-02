package entities.ex3;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Student extends Person{
    private static final String type = "student";
    private Double averageGrade;
    private Integer attendance;

    public Student() {
    }

    public Student(String firstName, String lastName, String phoneNumber, Double averageGrade, Integer attendance) {
        super(firstName, lastName, phoneNumber,type);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    @Column(name = "avg_grade")

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
}
