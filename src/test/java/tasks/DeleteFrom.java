package tasks;

import endpoints.Endpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteFrom implements Task {

    Map<String, String> dataBody;
    Endpoints endpoint;
    Map<String, Object> header;

    public DeleteFrom(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        this.dataBody = dataBody;
        this.endpoint = endpoint;
        this.header = header;
    }

    public static DeleteFrom withData(Map<String, String> dataBody, Map<String, Object> header, Endpoints endpoint) {
        return instrumented(DeleteFrom.class, dataBody, header, endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(endpoint.path())
                        .with(request -> request.relaxedHTTPSValidation()
                                .headers(header)
                                .pathParam("id", dataBody.get("id"))
                                .log().all()
                        )
        );
        System.out.println("\n\n\nresponse_delete_requisition:\n" + lastResponse().asString());
    }
}