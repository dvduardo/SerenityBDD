package model;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PayloadModel {

    JSONObject jsonObject;
    private Possibilidades payload;
    private String path_base = "./src/test/resources/payloads/";

    public void generator(Possibilidades payload) throws IllegalAccessException {
        Path path;

        switch (payload) {
            case PAYLOAD_SUCESSO:
                path = Paths.get(path_base + "payload_success.json");
                break;
            case PAYLOAD_MODIFICADO:
                path = Paths.get(path_base + "empty_payload.json");
                break;
            default:
                throw new IllegalAccessException("Unexpected value: " + this.payload);
        }

        StringBuilder contentBuilder = new StringBuilder();
        String content;
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                contentBuilder.append(line);
            }

            content = contentBuilder.toString();

            jsonObject = new JSONObject(content);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setValorPayload(String chave, Object valor) {
        jsonObject.put(chave, valor);
    }

    public void removeKey(String key) {
        jsonObject.remove(key);
    }

    public void removeKeyModify(String key1, String key2) {
        jsonObject.getJSONObject(key1).remove(key2);
    }

    public void createKey(String key, Object value) {
        jsonObject.put(key, value).toMap();

    }

    public void setValorCampoAninhado(String chave1, String chave2, Object valor) {
        jsonObject.getJSONObject(chave1).put(chave2, valor);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public enum Possibilidades {
        PAYLOAD_SUCESSO,
        PAYLOAD_MODIFICADO
    }
}