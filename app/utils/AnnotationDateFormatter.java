package utils;

import org.apache.commons.lang3.StringUtils;
import play.data.format.Formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Tomislav Mitic on 10/27/15.
 */
public class AnnotationDateFormatter extends Formatters.AnnotationFormatter<DateFormat, Date>{


    @Override
    public Date parse(DateFormat dateFormat, String s, Locale locale) throws ParseException {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.value(), locale);
        sdf.setLenient(false);
        return sdf.parse(s);
    }

    @Override
    public String print(DateFormat dateFormat, Date date, Locale locale) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat.value(), locale).format(date);
    }
}
