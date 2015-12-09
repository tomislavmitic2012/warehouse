package models;

import com.avaje.ebean.Page;
import org.apache.commons.lang3.StringUtils;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.F;
import play.mvc.PathBindable;
import play.mvc.QueryStringBindable;
import utils.DateFormat;
import utils.Validation.EAN;

import javax.persistence.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Tomislav S. Mitic on 7/18/15.
 */
@Entity
public class Product extends Model implements PathBindable<Product>, QueryStringBindable<Product> {

    public static class EanValidator extends Constraints.Validator<String> {

        @Override
        public boolean isValid(String ean) {
            return ean != null &&
                    Pattern.compile("^[0-9]{13}$")
                            .matcher(ean).matches();
        }

        @Override
        public F.Tuple<String, Object[]> getErrorMessageKey() {
            return new F.Tuple<>(
                    "error.invalid.ean", new Object[]{});
        }
    }

    private static List<Product> products;

    public static Finder<Long, Product> find = new Finder<>(Long.class, Product.class);

    @Id
    public Long id;

    @Constraints.Required
    @EAN
    public String ean;

    @Constraints.Required
    public String name;

    public String description;

    public Date date = new Date();

    @DateFormat("yyyy-MM-dd")
    public Date peremptionDate = new Date();

    @ManyToMany
    public List<Tag> tags = new LinkedList<>();

    @Lob
    public byte[] picture;

    @OneToMany(mappedBy = "product")
    public List<StockItem> stockItems;

    public Product() {}

    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public static boolean remove(Product product) {
        return products.remove(product);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", ean, name);
    }

    public static Product findByEan(String ean) {
        return find.where().eq("ean", ean).findUnique();
    }

    public static List<Product> findAll() {
        return find.all();
    }

    public static Page<Product> find(int page) {
        return find.where()
                .orderBy("id asc")
                .findPagingList(10)
                .setFetchAhead(true)
                .getPage(page);
    }

    @Override
    public Product bind(String pathKey, String eanValue
    ) {
        return this.findByEan(eanValue);
    }

    @Override
    public F.Option<Product> bind(String key, Map<String, String[]> data) {
        return F.Option.Some(this.findByEan(data.get(key)[0]));
    }

    @Override
    public String unbind(String key) {
        return this.ean;
    }

    @Override
    public String javascriptUnbind() {
        return this.ean;
    }
}
