package net.electrifai.library.runner;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.electrifai.library.utils.BaseClass;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        glue = {"net/electrifai/setpdefinations", "net/electrifai/library"},
        monochrome = true,dryRun = false,snippets = CucumberOptions.SnippetType.CAMELCASE,plugin = {"pretty",
        "html:target/cucumber.html",
        "json:target/cucumber.json",
},
        tags = "@smoke"
)
public class TestRunner extends BaseClass {
}
