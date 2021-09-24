package actors;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import stepdefinitions.TestEnvironment;

public class CastOfUsers extends Cast {

    public final TestEnvironment testEnvironment;

    public CastOfUsers(EnvironmentVariables environmentVariables) {
        testEnvironment = new TestEnvironment(environmentVariables);
    }

    @Override
    public Actor actorNamed(String actorName, Ability... abilities) {
        Actor trader = super.actorNamed(actorName, abilities);
        trader.can(CallAnApi.at(testEnvironment.getRestAPIBaseUrl("dev")));

        return trader;
    }
}
