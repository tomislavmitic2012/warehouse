package interceptors;

import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import utils.ExceptionMailer;

/**
 * Created by Tomislav S. Mitic on 8/16/15.
 */
public class CatchAction extends Action<Catch> {

    private static final Logger.ALogger logger = Logger.of(CatchAction.class);

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        try {
            return delegate.call(context);
        } catch(Throwable e) {
            if (configuration.send()) {
                ExceptionMailer.send(e);
            } else {
                logger.error(String.format("%s%s", "Sending email containing exception: ", e));
            }
            throw new RuntimeException(e);
        }
    }
}
