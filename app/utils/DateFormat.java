package utils;

import play.data.Form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tom Mitic on 10/27/15.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Form.Display(name = "format.date", attributes = {"value"})
public @interface DateFormat {
    String value();
}
