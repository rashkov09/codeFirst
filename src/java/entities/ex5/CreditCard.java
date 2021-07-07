package entities.ex5;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "credit_cards")
@DiscriminatorValue("card")
public class CreditCard extends BillingDetail {
    private String cardType;
    private Month expirationMonth;
    private Year expirationYear;
    private BillingDetail billingDetail;

    public CreditCard() {
    }

    public CreditCard(Long number, User owner, String cardType, Month expirationMonth, Year expirationYear) {
        super(number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    @Column(name = "card_type",nullable = false)

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name = "expiration_month",nullable = false)

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name = "expiration_year",nullable = false)

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }

    @ManyToOne

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }
}
