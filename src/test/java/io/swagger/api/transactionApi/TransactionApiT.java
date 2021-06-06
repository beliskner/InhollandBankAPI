package io.swagger.api.transactionApi;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "io.swagger.api.transactionApi.IT.steps",
        plugin = "pretty"

)



public class TransactionApiT {
}
