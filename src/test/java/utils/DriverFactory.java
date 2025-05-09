package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver get() {
        WebDriver driver;
        String browser = ConfigurationReader.get("browser");

        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();

                options.addArguments("--no-sandbox");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
                options.addArguments("--lang=en-en");
                options.addArguments("--disable-features=PasswordManager");
                options.addArguments("--disable-change-password-bubble");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-password-generation");
                options.addArguments("--disable-blink-features=AutomationControlled,PasswordManager");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);

                if (ConfigurationReader.get("headless").toLowerCase().contains("true")) {
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--lang=en-en");
                    options.addArguments("--disable-features=PasswordManager");
                    options.addArguments("--disable-change-password-bubble");
                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--disable-password-generation");
                    options.addArguments("--disable-blink-features=AutomationControlled,PasswordManager");
                }
                driver = new ChromeDriver(options);
                if (ConfigurationReader.get("maximize").toLowerCase().contains("true")) {
                    driver.manage().window().maximize();
                }
                return driver;
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
        }
        throw new WebDriverException("Parameter is not ...");
    }
}
