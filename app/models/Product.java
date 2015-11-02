package models;

import org.apache.commons.lang3.StringUtils;
import play.data.validation.Constraints;
import play.libs.F;
import play.mvc.PathBindable;
import play.mvc.QueryStringBindable;
import utils.Validation.EAN;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Tomislav S. Mitic on 7/18/15.
 */
public class Product implements PathBindable<Product>, QueryStringBindable<Product> {

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

    static {
        products = new ArrayList<>();
        products.add(new Product("1111111111111", "Paperclips 1", "Paperclips description 1"));
        products.add(new Product("2222222222222", "Paperclips 2", "Paperclips description "));
        products.add(new Product("3333333333333", "Paperclips 3", "Paperclips description 3"));
        products.add(new Product("4444444444444", "Paperclips 4", "Paperclips description 4"));
        products.add(new Product("5555555555555", "Paperclips 5", "Paperclips description 5"));
    }

    @Constraints.Required
    @EAN
    public String ean;

    @Constraints.Required
    public String name;

    public String description;

    public List<Tag> tags = new LinkedList<>();

    public byte[] picture;

    public Product() {}

    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public static List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public static Product findByEan(String ean) {
        for (Product candidate : products) {
            if (candidate.ean.equals(ean)) {
                return candidate;
            }
        }
        return null;
    }

    public static List<Product> findByName(String term) {
        final List<Product> results = new ArrayList<>();
        for (Product candidate : products) {
            if (StringUtils.containsIgnoreCase(candidate.name, term)) {
                results.add(candidate);
            }
        }

        return results;
    }

    public static boolean remove(Product product) {
        return products.remove(product);
    }

    public void save() {
        products.remove(findByEan(this.ean));
        products.add(this);
    }

    public String toString() {
        return String.format("%s - %s", ean, name);
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
