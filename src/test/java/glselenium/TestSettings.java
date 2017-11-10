package glselenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class TestSettings {

    WebDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities(); //TODO: Desired capabilities - check to which browsers they are applicable - ?

    String baseURL = "http://172.22.90.208/litecart";

    public WebDriver getBrowser(String browserToRun) {
        switch (browserToRun){
            case "ie":
                capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver(capabilities);
                break;
            case "firefox":
                capabilities.setCapability("marionette", false);
                capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver(capabilities);
                break;
            case "chrome":
                capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver(capabilities);
                break;
            default:
                getBrowser("firefox");
        }
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        return driver;
    }

}
