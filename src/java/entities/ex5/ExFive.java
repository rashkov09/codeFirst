package entities.ex5;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.Month;
import java.time.Year;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ExFive implements Exercise {
    @Override
    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("billsPaymentSystem").createEntityManager();

        User user = new User("Daniel","Petrov","333aaa@abv.bg","todor1234");
        BankAccount bankAccount =  new BankAccount("2131212412421",user,"TokudaBank","TOK1235");
        CreditCard creditCard = new CreditCard("4444-333-2322-333",user,"Master Card", Month.APRIL,1992);
        Set<BillingDetail> billingDetails = new LinkedHashSet<>();
        billingDetails.add(bankAccount);
        billingDetails.add(creditCard);
        user.setBillingDetails(billingDetails);

        User user1 = new User("Todor","Petrov","dsda@abv.bg","todor1234");
        BankAccount bankAccount1 =  new BankAccount("321312A232S",user1,"Raifaizen Bank","R1231231");
        CreditCard creditCard1 = new CreditCard("5141212-2131241-1232131",user1,"Master Card", Month.JULY,2021);
        billingDetails = new LinkedHashSet<>();
        billingDetails.add(bankAccount1);
        billingDetails.add(creditCard1);
        user1.setBillingDetails(billingDetails);
        em.getTransaction().begin();
        em.persist(user);
        em.persist(bankAccount);
        em.persist(creditCard);
        em.persist(user1);
        em.persist(bankAccount1);
        em.persist(creditCard1);

        em.getTransaction().commit();

        List<User> users = em.createQuery("SELECT u FROM User AS u",User.class).getResultList();

        users.forEach(u->{
            System.out.printf("%s %s\n",u.getFirstName(),u.getLastName() );
            if (u.getBillingDetails() != null){
                u.getBillingDetails().forEach(bd->{
                    if (bd.getClass().getSimpleName().equals("CreditCard")){
                        CreditCard card = (CreditCard) bd;
                        System.out.printf("%s - %s/%s\n",card.getCardType(),card.getExpirationMonth(),card.getExpirationYear());
                    } else if (bd.getClass().getSimpleName().equals("BankAccount")){
                        BankAccount account = (BankAccount) bd;
                        System.out.printf("%s - %s - %s\n",account.getBankName(),account.getNumber(),account.getSwiftCode());
                    }
                });
            }
        });
    }
}
