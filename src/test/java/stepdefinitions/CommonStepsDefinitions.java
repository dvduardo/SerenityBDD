package stepdefinitions;


import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import endpoints.Endpoints;
import model.PayloadModel;
import net.serenitybdd.screenplay.actors.OnStage;
import org.json.JSONObject;
import tasks.DeleteFrom;
import tasks.GetResources;
import tasks.PatchTo;
import tasks.PostTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static endpoints.Endpoints.*;
import static matchers.ResponseMatchers.*;
import static model.PayloadModel.Possibilidades.PAYLOAD_SUCESSO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static properties.Properties.header_padrao;

public class CommonStepsDefinitions {

    HashMap<String, String> bodyData = new HashMap<>();
    PayloadModel payload = new PayloadModel();

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
    public void post_to(String type, Map<String, String> data){
        System.out.println("\n\nQuando eu criar uma requisicao " + type + "\n\n");
        switch (type) {
            case "post": {
                theActorInTheSpotlight().attemptsTo(
                        PostTo.withData(data, header_padrao, Post_to)
                );
                break;
            }
            case "get": {
                theActorInTheSpotlight().attemptsTo(
                        GetResources.withData(data, header_padrao, Get_resources)
                );
                break;
            }
            case "delete": {
                theActorInTheSpotlight().attemptsTo(
                        DeleteFrom.withData(data, header_padrao, Delete_from)
                );
                break;
            }
            case "patch": {
                bodyData.put("title", data.get("title"));
                theActorInTheSpotlight().attemptsTo(
                        PatchTo.withParametre(bodyData, data, header_padrao, Patch_to)
                );
                break;
            }

            default:
                throw new IllegalArgumentException("Opcao nao reconhecida para tipo de requisicao aceita: " + type);
        }


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

    @Quando("^eu recuperar o arquivo$")
    public void euRecuperarFile() throws IllegalAccessException {
        payload.generator(PAYLOAD_SUCESSO);
    }

    @E("^modificar o campo$")
    public void modificarOCampo(Map<String, Object> data) {
        for (String key : data.keySet()) {
            Object value = data.get(key);
            payload.setValorPayload(key, value);
        }
    }

    @E("^eu adicionar o campo$")
    public void adicionarOCampo(Map<String, Object> data) {
        for (String key : data.keySet()) {
            Object value = data.get(key);
            payload.createKey(key, value);
        }
    }

    @E("^remover o campo$")
    public void removerOCampo(List<String> data) {
        for (String datum : data) {
            payload.removeKey(datum);
        }
    }

    @E("^remover o campo cascateado$")
    public void removerOCampoInterno(Map<String, String> data) {
        for (String key : data.keySet()) {
            String key2 = data.get(key);
            payload.removeKeyModify(key, key2);
        }
    }

    @Quando("^eu recuperar o arquivo e enviar a requisicao$")
    public void euRecuperarFileEEnviarARequisicao() throws IllegalAccessException {
        payload.generator(PAYLOAD_SUCESSO);
        requisicao(payload.getJsonObject(), Post_to);
    }

    @Quando("^eu enviar a requisicao$")
    public void euEnviarARequisicao() {
        requisicao(payload.getJsonObject(), Post_to);
    }

    public void requisicao(JSONObject body, Endpoints endpoints) {
        theActorInTheSpotlight().attemptsTo(
                PostTo.withJsonData(body, header_padrao, endpoints)
        );
    }


    @E("^eu modificar o campo \"([^\"]*)\"\\.\"([^\"]*)\" para o valor \"([^\"]*)\"$")
    public void euModificarOCampoParaOValor(String arg0, String arg1, Object arg2) {
        payload.setValorCampoAninhado(arg0, arg1, arg2);
    }

    @E("^eu criar o campo \"([^\"]*)\"\\.\"([^\"]*)\" com o valor \"([^\"]*)\"$")
    public void eucriarOCampoParaOValor(String arg0, String arg1, Object arg2) {
        Map<String, Object> map = new HashMap<>();
        map.put(arg1, arg2);
        payload.createKey(arg0, new JSONObject(map));
    }


    @E("^eu modificar o campo \"([^\"]*)\" para o valor \"([^\"]*)\"$")
    public void euModificarOCampoParaOValor(String arg0, Object arg1) {
        payload.setValorPayload(arg0, arg1);
    }

}

