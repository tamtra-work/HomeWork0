/*
1. Login to admin panel http://localhost/litecart/admin
2. Open Countries (http://localhost/litecart/admin/?app=countries&doc=countries )
3. Open on Edit any country or click “add new country”
4. On Edit/New page some elements has arrow icon -
5. Click on icon and wait until new window opened
6. Close new window
7. Repeat for all links on a page

 */

package glselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Task7_LinksOpenedInNewWindowTest extends TestSettings {

    //Copy-paste from slides
    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("");
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @Test
    public void linksOpenedInNewWindow() {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        //TODO: create separate method for login to admin page
        driver.get(baseURL + "/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


        WebElement countryLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href$='countries']")));
        countryLink.click();

        driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[@class='row']/td[5]/a")).click(); // click on first found country

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i.fa.fa-external-link"));

        for (WebElement link : externalLinks) {
            //Copy-paste from slides
            String originalW = driver.getWindowHandle();
            Set<String> existWs = driver.getWindowHandles();
            link.click();
            String newW = wait.until(anyWindowOtherThan(existWs));
            driver.switchTo().window(newW);
            //5. wait until page loads - implemented in TestSettings class upon driver instance creation
            driver.close();
            driver.switchTo().window(originalW);
        }
    }

    @After
    public void cleanupTest() {
        driver.quit();
        driver = null;
    }
}
