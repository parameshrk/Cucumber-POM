package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features"
        , glue = "stepdefinition"
        , plugin = {"pretty","html:target/reports/report.html"
        ,"json:target/reports/report.json","junit:target/reports/report.xml"}

       // , tags ="@login"
       // , tags = "@hotelsSearch"

        , tags = "@DistList"
        // ,dryRun = true
)

public class TestRunner {
}