package stepdefinitions;

import net.thucydides.core.util.EnvironmentVariables;


public class TestEnvironment {

    private EnvironmentVariables environmentVariables;

    public TestEnvironment(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String getRestAPIBaseUrl(String environment) {
        switch (environment) {
            case "dev":
                return environmentVariables.getProperty("environments.dev");
            case "homol":
                return environmentVariables.getProperty("environments.homol");
            case "prod":
                return environmentVariables.getProperty("environments.prod");

            default:
                return environmentVariables.getProperty("environments.dev");
        }
    }
}
