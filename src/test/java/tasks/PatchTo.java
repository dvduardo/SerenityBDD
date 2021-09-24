package tasks;

import endpoints.Endpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PatchTo implements Task {

    HashMap<String, String> dataBody;
    Endpoints endpoint;
    Map<String, Object> header;
    Map<String, String> parametre;

    public PatchTo(HashMap<String, String> dataBody, Map<String, String> parametre, Map<String, Object> header, Endpoints endpoint) {
        this.dataBody = dataBody;
        this.endpoint = endpoint;
        this.header = header;
        this.parametre = parametre;
    }

    public static PatchTo withParametre(HashMap<String, String> dataBody, Map<String, String> parametre, Map<String, Object> header, Endpoints endpoint) {
        return instrumented(PatchTo.class, dataBody, parametre, header, endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Patch.to(endpoint.path())
                        .with(request -> request.relaxedHTTPSValidation()
                                .headers(header)
                                .pathParam("id", parametre.get("id"))
                                .body(dataBody)
                                .log().all()
                        )
        );
        System.out.println("\n\n\nresponse_patch_requisition:\n" + lastResponse().asString());
    }
}