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
    private int expirationYear;


    public CreditCard() {
    }

    public CreditCard(String number, User owner, String cardType, Month expirationMonth, int expirationYear) {
        super( number, owner);
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

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

}
