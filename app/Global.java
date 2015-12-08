import com.avaje.ebean.Ebean;
import models.Tag;
import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;
import play.libs.Yaml;
import utils.AnnotationDateFormatter;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Tom Mitic on 10/26/15.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Formatters.register(Date.class, new Formatters.SimpleFormatter<Date>() {

            private static final String PATTERN = "dd-MM-YYYY";

            @Override
            public Date parse(String s, Locale locale) throws ParseException {
                if (s == null || s.trim().isEmpty()) {
                    return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, locale);
                sdf.setLenient(false);
                return sdf.parse(s);
            }

            @Override
            public String print(Date date, Locale locale) {
                if (date == null) {
                    return "";
                }
                return new SimpleDateFormat(PATTERN, locale).format(date);
            }
        });

        Formatters.register(Date.class, new AnnotationDateFormatter());

        InitialData.insert(app);
    }

    static class InitialData {

        public static void insert(Application app) {
            if (Ebean.find(Tag.class).findRowCount() == 0) {
                Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                Ebean.save(all.get("tags"));
            }
        }
    }
}
