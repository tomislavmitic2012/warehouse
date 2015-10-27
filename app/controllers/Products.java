package controllers;

import interceptors.Catch;
import models.Product;
import models.Tag;
import org.apache.commons.lang3.StringUtils;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.details;
import views.html.products.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.data.Form.form;

/**
 * Created by Tomislav S. Mitic on 7/18/15.
 */
@Catch(send = false)
public class Products extends Controller {

    private static final Form<Product> productForm = form(Product.class);

    public static Result index() {
        return redirect(routes.Products.list(1));
    }

    public static Result list(Integer page) {
        List<Product> products = Product.findAll();
        List<String> accept = Arrays.asList(request().getHeader("Accept").split(","));
        if (accept.contains("text/plain")) {
            return ok(StringUtils.join(products, "\n"));
        }
        return ok(list.render(products));
    }

    public static Result newProduct() {
        return ok(details.render(productForm));
    }

    // The product should automatically be looked up using the product's EAN key
    public static Result details(Product product) {
        Form<Product> fillerForm = productForm.fill(product);
        return ok(details.render(fillerForm));
    }

    public static Result save() {
        Form<Product> boundForm = productForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        List<Tag> tags = new ArrayList<>();
        product.tags.stream().forEach(tag -> {
            if (tag.id != null) {
                tags.add(Tag.findById(tag.id));
            }
        });
        product.tags = tags;
        product.save();
        flash("success", String.format("Successfully added product %s", product));
        return redirect(routes.Products.list(1));
    }

    public static Result delete(String ean) {
        final Product product = Product.findByEan(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }
        Product.remove(product);
        return redirect(routes.Products.list(1));
    }
}
