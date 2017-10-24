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

    static WebDriver driver;
    static String browserToRun;

    public static WebDriver setBrowser(String browserToRun) {
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
            default: break;         // there should be smth better than that
        }
        return driver;
    }
}
