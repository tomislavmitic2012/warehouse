package models;

import play.Logger;
import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by tomislav on 7/18/15.
 */
public class Product {

    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product("1111111111111", "Paperclips 1", "Paperclips description 1"));
        products.add(new Product("2222222222222", "Paperclips 2", "Paperclips description 2"));
        products.add(new Product("3333333333333", "Paperclips 3", "Paperclips description 3"));
        products.add(new Product("4444444444444", "Paperclips 4", "Paperclips description 4"));
        products.add(new Product("5555555555555", "Paperclips 5", "Paperclips description 5"));
    }

    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    public String description;

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
        Optional<Product> candidate = products.stream().filter(p -> ean.equals(p.ean)).findFirst();
        try {
            return candidate.get();
        } catch (NoSuchElementException e) {
            Logger.info(String.format("No such product exists with ean: %s. Exception: %s", ean, e));
        }
        return null;
    }

    public static List<Product> findByName(String term) {
        final List<Product> results = new ArrayList<>();
        products.stream().filter(p -> term.toLowerCase().equals(p.name.toLowerCase())).forEach(p -> results.add(p));
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
}
