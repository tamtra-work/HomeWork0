/*
Task 1: Create Infrastructure

1. Create account on GitHub
2. Install all necessary soft and create empty gradle project
3. Create simple test which just open browser, navigates to www.google.com and then close the browser
4. Try to run different browsers
5. Upload your project on GitHub and send me the link
 */

package glselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class Task1_GoogleTest extends TestSettings {

    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("chr");
    }

    @Test
    public void openGoogle(){
        driver.get("http://www.google.com");
    }


    @After

    public void cleanupTest() {
        driver.quit();
        driver = null;
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