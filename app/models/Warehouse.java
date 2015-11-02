package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomislav S. Mitic on 11/2/15.
 */
@Entity
public class Warehouse {

    @Id
    public Long id;

    public String name;

    @OneToMany(mappedBy = "warehouse")
    public List<StockItem> stock = new ArrayList<>();

    @OneToOne
    public Address address;

    @Override
    public String toString() {
        return name;
    }
}
