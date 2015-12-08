package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Tomislav S. Mitic on 11/2/15.
 */
@Entity
public class StockItem extends Model {

    public static Finder<Long, StockItem> find = new Finder<Long, StockItem>(Long.class, StockItem.class);

    @Id
    public Long id;

    @ManyToOne
    public Warehouse warehouse;

    @ManyToOne
    public Product product;

    public Long quantity;

    @Override
    public String toString() {
        return String.format("StockItem %d - %dx product %s",
                id, quantity, product == null ? null : product.id);
    }
}
