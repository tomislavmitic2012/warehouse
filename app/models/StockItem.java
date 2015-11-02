package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Tomislav S. Mitic on 11/2/15.
 */
@Entity
public class StockItem {

    @Id
    public Long id;

    @ManyToOne
    public Warehouse warehouse;

    @ManyToOne
    public Product product;

    public Long quantity;

    @Override
    public String toString() {
        return String.format("%s %s", quantity, product);
    }
}
