package utils.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tom Mitic on 10/31/15.
 */
@Constraint(validatedBy = EANValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EAN {

    String message() default "Error invalid ean";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
