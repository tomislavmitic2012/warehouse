package utils;

import play.Logger;

/**
 * Created by Tomislav S. Mitic on 8/16/15.
 */
public class ExceptionMailer {

    private static final Logger.ALogger logger = Logger.of(ExceptionMailer.class);

    public static void send(Throwable e) {
        logger.error(String.format("%s%s", "Sending email containing exception: ", e));
    }
}
