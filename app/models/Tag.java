package models;

import play.data.validation.Constraints;

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
public class Tag {

    private static List<Tag> tags = new LinkedList<>();

    static {
        tags.add(new Tag(1L, "lightweight",
                Product.findByName("paperclips 1")));
        tags.add(new Tag(2L, "metal",
                Product.findByName("paperclips")));
        tags.add(new Tag(3L, "plastic",
                Product.findByName("paperclips")));
    }

    public static Tag findById(Long id) {
        for (Tag tag : tags) {
            if (tag.id == id) {
                return tag;
            }
        }
        return null;
    }

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
