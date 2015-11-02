package utils.Validation;

import play.data.validation.Constraints;
import play.libs.F;

import javax.validation.ConstraintValidator;
import java.util.regex.Pattern;

/**
 * Created by Tom Mitic on 10/31/15.
 */
public class EANValidator extends Constraints.Validator<String> implements ConstraintValidator<EAN, String> {

    @Override
    public void initialize(EAN constraintAnnotation) {
    }

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
