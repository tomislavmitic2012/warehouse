import org.junit.Test;
import play.mvc.Http;
import play.twirl.api.Html;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Http.Context context = mock(Http.Context.class);
        Http.Flash flash = mock(Http.Flash.class);
        doReturn(flash).when(context).flash();
        Http.Context.current.set(context);
        Http.Context.current.set(context);

        Html html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }


}
