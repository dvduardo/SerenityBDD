package matchers;

import io.restassured.response.ValidatableResponse;

import java.util.function.Consumer;

public class ResponseMatchers {
    public static Consumer<ValidatableResponse> is200 = validatableResponse -> validatableResponse.statusCode(200);
    public static Consumer<ValidatableResponse> is201 = validatableResponse -> validatableResponse.statusCode(201);
    public static Consumer<ValidatableResponse> is404 = validatableResponse -> validatableResponse.statusCode(404);
}
