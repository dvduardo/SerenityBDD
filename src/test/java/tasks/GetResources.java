package tasks;

import endpoints.Endpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetResources implements Task {

    Map<String, String> dataBody;
    Endpoints endpoint;
    Map<String, Object> header;

    public GetResources(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        this.dataBody = dataBody;
        this.endpoint = endpoint;
        this.header = header;
    }

    public static GetResources withData(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        return instrumented(GetResources.class, dataBody, header, endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint.path())
                        .with(request -> request.relaxedHTTPSValidation()
                                .headers(header)
                                .pathParam("id", dataBody.get("id"))
                                .log().all()
                        )
        );

    }
}