package stepdefinitions;


import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import endpoints.Endpoints;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import tasks.DeleteFrom;
import tasks.GetResources;
import tasks.PatchTo;
import tasks.PostTo;

import java.util.HashMap;
import java.util.Map;

import static matchers.ResponseMatchers.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static properties.Properties.header_padrao;

public class CommonStepsDefinitions {

    HashMap<String,String> bodyData = new HashMap<>();

    @Dado("^que \"([^\"]*)\" esta em andamento$")
    public void NewActorCreate(String value) {
        OnStage.theActor(value);
    }

    @E("^retorna \"([^\"]*)\"$")
    public void retorna(String arg0) {
        if (arg0.equalsIgnoreCase("sucesso")) {
            theActorInTheSpotlight().should(
                    seeThatResponse(is200)
            );
        }
        if (arg0.equalsIgnoreCase("created")) {
            theActorInTheSpotlight().should(
                    seeThatResponse(is201)
            );
        }
        if (arg0.equalsIgnoreCase("not_found")) {
            theActorInTheSpotlight().should(
                    seeThatResponse(is404)
            );
        }
    }

    @Quando("^eu criar uma requisicao \"([^\"]*)\" com sucesso$")
    public void post_to(String type, Map<String, String> data) {
        System.out.println("\n\nQuando eu criar uma requisicao "+type+"\n\n");
        if (type.equalsIgnoreCase("post")) {
            theActorInTheSpotlight().attemptsTo(
                    PostTo.withData(data, header_padrao, Endpoints.Post_to)
            );
        }
        if (type.equalsIgnoreCase("get")) {
            theActorInTheSpotlight().attemptsTo(
                    GetResources.withData(data, header_padrao, Endpoints.Get_resources)
            );
        }
        if (type.equalsIgnoreCase("delete")) {
            theActorInTheSpotlight().attemptsTo(
                    DeleteFrom.withData(data, header_padrao, Endpoints.Delete_from)
            );
        }
        if (type.equalsIgnoreCase("patch")) {
            bodyData.put("title",data.get("title"));
            theActorInTheSpotlight().attemptsTo(
                    PatchTo.withParametre(bodyData,data, header_padrao, Endpoints.Patch_to)
            );
        }

        System.out.println("\n\n\nresponse_body:" + SerenityRest.lastResponse().asString());
    }

    @Entao("^validar os campos$")
    public void validarOsCampos(Map<String, Object> data) {
        for (Object key : data.keySet()) {
            Object value = data.get(key);
            theActorInTheSpotlight().should(
                    seeThatResponse(validatableResponse -> validatableResponse.body(key.toString(), containsString(value.toString())))
            );
        }
    }

    @Entao("^validar o campo \"([^\"]*)\" contem o valor inteiro \"([^\"]*)\"$")
    public void validarOsCampos(String campo, int valor) {
        theActorInTheSpotlight().should(
                seeThatResponse(validatableResponse -> validatableResponse.body(campo, is(valor)))
        );
    }
}

