package io.swagger.api.HoldersController;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "io.swagger.api.HoldersController.IT.steps",
        plugin = "pretty"

)
public class HoldersApiIT {

}
