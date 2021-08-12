package cucumberOptions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions( stepNotifications = true,
	features = {"src\\test\\java\\features\\Customers.feature","src\\test\\java\\features\\Login.feature"}, 
	glue = "stepDefinations",
	tags = "@sanityLogin",
	dryRun = false, // check every test has stepDefination
	monochrome = true, // Console logs readable
	plugin =  {"pretty",  "html:target/cucumber.html",
			//"json:target/cucumber-reports/Cucumber.json",		 "junit:target/cucumber-reports/Cucumber.xml",
	}
)

public class testRunner  extends AbstractTestNGCucumberTests{

}
