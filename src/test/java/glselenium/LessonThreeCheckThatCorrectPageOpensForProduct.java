/*
Task 4: Check that correct page is opened for Product

1. Open main page (http://localhost/litecart)
2. Select first product in Campaigns section
3. Open Product Page (click on product)
4. Verify that:
    Product Name is equal on Main page and Item Page
    Prices (discount and regular) are equal on both pages
    Regular price is gray and strike () on both pages
    Campaigns price is red and bold on both pages

    Note: for color values you should just make sure that cssValue for color is the same as it shown in browser for the element.
    Note color may not be equal on main and product pages)

5. Run tests in all key browsers (chrome, ff, ie)
6. Send me the link to your scenario on GitHub
 */

package glselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LessonThreeCheckThatCorrectPageOpensForProduct extends TestSettings {

    @Before
    public void setupTest() {
        //browserToRun = "firefox";
        TestSettings.setBrowser(browserToRun);
    }

    // NOT FINISHED AND BARELY STARTED
    @Test
    public void checkThatCorrectPageOpensForProduct() {
        driver.get(baseURL);
        driver.findElement(By.xpath("//*[@id='box-campaigns']/div/ul/li[1]")).click();
        System.out.println();


    }


    @After
    public void cleanupTest() {
        driver.quit();
        driver = null;
    }
}
