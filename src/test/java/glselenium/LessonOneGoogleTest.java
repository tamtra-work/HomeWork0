package glselenium;

import org.junit.Before;
import org.junit.Test;
import static glselenium.TestSettings.browserToRun;
import static glselenium.TestSettings.driver;


public class LessonOneGoogleTest {

    //or @BeforeClass
    @Before
    public void setupTest() {
        browserToRun = "ie";
        TestSettings.setBrowser(browserToRun);
    }

    @Test
    public void openGoogle(){
        driver.get("http://www.google.com");
        driver.quit();
    }


/*      Older version


    @Test
    public void openGoogleInChrome() {

        ChromeDriverManager.getInstance().setup();
        WebDriver driverChr = new ChromeDriver();
        driverChr.get("http://www.google.com");
        driverChr.quit();
    }


    @Test
    public void openGoogleInFirefox() {

        DesiredCapabilities capa = DesiredCapabilities.firefox();
        capa.setCapability("marionette", false);

        FirefoxDriverManager.getInstance().setup();
        WebDriver driverFF = new FirefoxDriver(capa);
        driverFF.get("http://www.google.com");
        driverFF.quit();
    }

    @Test
    public void openGoogleInIE() {

        InternetExplorerDriverManager.getInstance().setup();
        WebDriver driverIE = new InternetExplorerDriver();
        driverIE.get("http://www.google.com");
        driverIE.quit();
    }

    */
}