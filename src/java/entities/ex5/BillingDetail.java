package entities.ex5;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail extends BaseEntity{
    private String number;
    private User owner;


    public BillingDetail() {
    }

    public BillingDetail(String number, User owner) {
        this.number = number;
        this.owner = owner;
    }


    @Column(name = "number")

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }




}
