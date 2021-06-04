package io.swagger.api.accountApi;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "io.swagger.api.accountApi.IT.steps",
        plugin = "pretty"

)


public class AccountApiT {
}

