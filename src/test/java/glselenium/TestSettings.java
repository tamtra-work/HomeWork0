package glselenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSettings {

    WebDriver driver;
    //String browserToRun;
    String baseURL = "http://172.22.90.208/litecart";

    public WebDriver getBrowser(String browserToRun) {
        switch (browserToRun){
            case "ie":
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver();
                break;
            case "firefox":
                DesiredCapabilities capa = DesiredCapabilities.firefox();
                capa.setCapability("marionette", false);
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver(capa);
                break;
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
            default:
                getBrowser("firefox");
        }
        return driver;
    }

}
