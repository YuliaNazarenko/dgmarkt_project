package tests;

import context.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.StartPage;
import utils.ConfigurationReader;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    TestContext context;

    @BeforeEach
    public void setUp(){
        context = new TestContext();
        context.driver = DriverFactory.get();
        long duration = Long.parseLong(ConfigurationReader.get("timeout"));
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(duration));
        context.action = new Actions(context.driver);
        context.driver.get(ConfigurationReader.get("url")+"startPageLogin");
        StartPage startPage = new StartPage(context);
        startPage.startPageLogin();
    }
    @AfterEach
    public void tearDown(){
        if(context.driver!=null){
            context.driver.quit();
        }
    }
}
