package cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(features = "src/test/java/features/",glue = {"stepdefinitions"})
@RunWith(Cucumber.class)
public class TestRunner {
}
