/*
Task 8: Add logging capabilities to your test

1. Copy code from “Task 3 : Create scenario which navigates through all sections on Admin page” into new class (e.g. Task 8)
2. Add EventFiringWebDriver
3. Add logging before and after find element
4. Add screenshot capture on exceptions
5. Send me the link to your scenario on GitHub
6. Optional: Execute scenario via RemoteWebDriver on local selenium server
 */

package glselenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class Task8_AddLoggingCapabilitiesToTask3Test extends TestSettings {

    WebDriver driver;
    EventFiringWebDriver eventDriver;
    EventsHandler handler;
    WebDriverWait wait;

    @Before
    public void setupTest() {
        driver = getBrowser("");
        eventDriver = new EventFiringWebDriver(driver);
        handler = new EventsHandler();
        eventDriver.register(handler);
        wait = new WebDriverWait(driver, 5);
    }

    //TODO: replace "while" with "for"
    //TODO: iterate by xpath indexes
    //TODO: before and after methods - do not copy paste to each test, make smarter
    @Test
    public void addLoggingToTask3() {

        eventDriver.get(baseURL + "/admin");
        eventDriver.findElement(By.name("username")).sendKeys("admin");
        eventDriver.findElement(By.name("password")).sendKeys("admin");
        wait.until(elementToBeClickable(By.name("login")));
        eventDriver.findElement(By.name("login")).click();

        //TODO: wait till page loads
        wait.until(elementToBeClickable(By.cssSelector("li#app-")));
        int numberOfTop = eventDriver.findElements(By.cssSelector("li#app-")).size();
        int i = 1;
        while (i <= numberOfTop) {
            eventDriver.findElement(By.cssSelector("li#app-:nth-child(" + i + ")")).click();
            Assert.assertTrue(eventDriver.findElement(By.cssSelector("h1")).isDisplayed());
            int numberOfChild = driver.findElements(By.cssSelector("li[id^=doc-]")).size();
            int j = 1;
            while (j <= numberOfChild) {
                eventDriver.findElement(By.cssSelector("li[id^=doc-]:nth-child(" + j + ")")).click();
                Assert.assertTrue(eventDriver.findElement(By.cssSelector("h1")).isDisplayed());
                j++;
            }
            i++;
        }
    }

    @After
    public void cleanupTest() {
        eventDriver.quit();
        eventDriver = null;
    }
}
