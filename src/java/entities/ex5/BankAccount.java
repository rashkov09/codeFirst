package entities.ex5;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
@DiscriminatorValue("account")
public class BankAccount extends BillingDetail {
    private String bankName;
    private String swiftCode;
    private BillingDetail billingDetail;

    public BankAccount() {
    }

    public BankAccount(Long number, User owner, String bankName, String swiftCode) {
        super(number, owner);
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    @Column(name = "bank_name",nullable = false)

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "swift_code",nullable = false)

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    @ManyToOne

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }
}
