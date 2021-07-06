package entities.ex2;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation extends BaseEntity {
    private String location_name;
    private Set<Sale> sales;

    public StoreLocation() {
    }

    @Column(name = "location_name")

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    @OneToMany(mappedBy = "storeLocation", cascade = CascadeType.PERSIST)

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
