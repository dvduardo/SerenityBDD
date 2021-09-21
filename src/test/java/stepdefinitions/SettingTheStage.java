package stepdefinitions;

import actors.CastOfUsers;
import cucumber.api.java.Before;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;

public class SettingTheStage {

    EnvironmentVariables environmentVariables;

    @Before
    public void set_the_stage() {
        setTheStage(new CastOfUsers(environmentVariables));
    }
}
