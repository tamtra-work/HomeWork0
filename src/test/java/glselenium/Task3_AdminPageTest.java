/*
Task 3: Create scenario which navigates through all sections on Admin page

1. Login to admin panel http://localhost/litecart/admin
2. Sequentially click on each item in side menu (left) including all submenu items
3. For each new page verify if header is present on the page (element h1)
4. Send me the link to your scenario on GitHub
 */

package glselenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task3_AdminPageTest extends TestSettings {

    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("");
    }

    //TODO: replace "while" with "for"
    //TODO: iterate by xpath indexes
    //TODO: before and after methods - do not copy paste to each test, make smarter
    @Test
    public void CheckHeadersPresenseOnAllSectionsOnAdminPage() {
        driver.get(baseURL + "/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        int numberOfTop = driver.findElements(By.cssSelector("li#app-")).size();
        int i = 1;
        while (i <= numberOfTop) {
            driver.findElement(By.cssSelector("li#app-:nth-child(" + i + ")")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
            int numberOfChild = driver.findElements(By.cssSelector("li[id^=doc-]")).size();
            int j = 1;
            while (j <= numberOfChild) {
                driver.findElement(By.cssSelector("li[id^=doc-]:nth-child(" + j + ")")).click();
                Assert.assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
                j++;
            }
            i++;
        }
    }

    @After
    public void cleanupTest() {
        driver.quit();
        driver = null;
    }
}


