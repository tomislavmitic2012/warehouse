package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tom Mitic on 10/10/15.
 */
@Entity
public class Tag extends Model {

    private static List<Tag> tags = new LinkedList<>();

    public static Tag findById(Long id) {
        return find.byId(id);
    }

    public static Finder<Long, Tag> find = new Finder<>(Long.class, Tag.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @ManyToMany(mappedBy = "tags")
    public List<Product> products;

    public Tag() {
    }

    public Tag(Long id, String name, Collection<Product> products) {
        this.id = id;
        this.name = name;
        this.products = new LinkedList<>(products);
        for (Product product : products) {
            product.tags.add(this);
        }
    }
}
