package tasks;

import endpoints.Endpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostTo implements Task {

    Map<String, String> dataBody;
    Endpoints endpoint;
    Map<String, Object> header;

    public PostTo(Map<String, String> dataBody,Map<String, Object> header, Endpoints endpoint) {
        this.dataBody = dataBody;
        this.endpoint = endpoint;
        this.header = header;
    }

    public static PostTo withData(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        return instrumented(PostTo.class, dataBody, header, endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint.path())
                        .with(request -> request.relaxedHTTPSValidation()
                                .headers(header)
                                .body(dataBody)
                                .log().all()
                        )
        );

    }
}