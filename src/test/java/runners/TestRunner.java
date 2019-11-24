package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import gobear.automation.core.utils.WebDriverFactory;

@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions",
                "hooks"})
public class TestRunner extends AbstractTestNGCucumberTests {
    public TestRunner() {
        Runtime.getRuntime().addShutdownHook(new Thread(WebDriverFactory::closeDriver));
    }
}
