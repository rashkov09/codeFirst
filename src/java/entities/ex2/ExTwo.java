package entities.ex2;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExTwo implements Exercise {
    @Override
    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("sales").createEntityManager();
         em.getTransaction().begin();
         //test product
     Product product = new Product();
     product.setName("Coca Cola");
     product.setPrice(BigDecimal.TEN);
     product.setQuantity(5);

     em.persist(product);
     // test customer
     Customer customer = new Customer();
     customer.setCreditCardNumber("2312313123");
     customer.setName("John Doe");
     customer.setEmail("doe@bg.sdd");
     em.persist(customer);

     //test store location
     StoreLocation location = new StoreLocation();
     location.setLocation_name("Kaufland");
     em.persist(location);
     //test sale

     Sale sale = new Sale();
     sale.setDate(LocalDateTime.now());

     sale.setProduct(product);
     sale.setCustomer(customer);
     sale.setStoreLocation(location);
     em.persist(sale);

     em.getTransaction().commit();
     em.close();

      em = Persistence.createEntityManagerFactory("sales").createEntityManager();


     em.getTransaction().begin();
        Product firstProduct=  em.find(Product.class,(long) 1);
         firstProduct.getSales().stream().forEach(s -> {
         System.out.printf("Product name: %s\nStore name: %s\n Bought by customer: %s\n Bought on: %s",
                 s.getProduct().getName(),s.getStoreLocation().getLocation_name(),s.getCustomer().getName(),s.getDate());
         });
        em.getTransaction().commit();


    }
}
