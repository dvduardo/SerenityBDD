package tasks;

import endpoints.Endpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.json.JSONObject;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostTo implements Task {

    Map<String, String> dataBody;
    JSONObject jsonBody;
    Endpoints endpoint;
    Map<String, Object> header;
    private String type;

    public PostTo(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        this.dataBody = dataBody;
        this.endpoint = endpoint;
        this.header = header;
        type = "map";
    }

    public PostTo(JSONObject dataBody, Map<String, Object> header, Endpoints endpoint) {
        this.jsonBody = dataBody;
        this.endpoint = endpoint;
        this.header = header;
        type = "json";
    }

    public static PostTo withData(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        return instrumented(PostTo.class, dataBody, header, endpoint);
    }

    public static PostTo withJsonData(JSONObject dataBody, Map<String, Object> header, Endpoints endpoint) {
        return instrumented(PostTo.class, dataBody, header, endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (type.equalsIgnoreCase("map"))
            actor.attemptsTo(
                    Post.to(endpoint.path())
                            .with(request -> request.relaxedHTTPSValidation()
                                    .headers(header)
                                    .body(dataBody)
                                    .log().all()
                            )
            );
        if (type.equalsIgnoreCase("json"))
            actor.attemptsTo(
                    Post.to(endpoint.path())
                            .with(request -> request.relaxedHTTPSValidation()
                                    .headers(header)
                                    .body(jsonBody.toMap())
                                    .log().all()
                            )
            );
        System.out.println("\n\n\nresponse_post_requisition:\n" + lastResponse().asString());
    }
}