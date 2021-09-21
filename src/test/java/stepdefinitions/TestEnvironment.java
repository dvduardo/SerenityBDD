package stepdefinitions;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;


public class TestEnvironment {

    private EnvironmentVariables environmentVariables;

    public TestEnvironment(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String getRestAPIBaseUrl() {
        String teste; //= EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.dev.url");
        teste = "https://jsonplaceholder.typicode.com";
        return teste;
    }
}
