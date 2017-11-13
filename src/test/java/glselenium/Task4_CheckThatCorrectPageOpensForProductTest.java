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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task4_CheckThatCorrectPageOpensForProductTest extends TestSettings {

    //TODO: it would make much more sense to create separate classes for the following thousand of lines
    WebDriverWait wait;
    // Elements on main page
    WebElement productNameMainPage;
    WebElement priceRegularMainPage;
    WebElement priceDiscountMainPage;

    //Elements on product page
    WebElement productNameProductPage;
    WebElement priceRegularProductPage;
    WebElement priceDiscountProductPage;

    //Properties of elements on main page
    public String productNameMainPage_Name;
    public String priceRegularMainPage_Value;
    public String priceRegularMainPage_Color;
    public String priceRegularMainPage_Strike;
    public String priceDiscountMainPage_Value;
    public String priceDiscountMainPage_Color;
    public String priceDiscountMainPage_Bold;

    //Properties of elements on product page
    public String productNameProductPage_Name;
    public String priceRegularProductPage_Value;
    public String priceRegularProductPage_Color;
    public String priceRegularProductPage_Strike;
    public String priceDiscountProductPage_Value;
    public String priceDiscountProductPage_Color;
    public String priceDiscountProductPage_Bold;

    //CSS selectors
    String firstProductCampaignsCSSSelector = "div#box-campaigns li:first-child";
    String productNameCSSSelectorMainPage = " div.name";
    String productNameCSSSelectorProductPage = "h1.title";
    String priceRegularCSSSelector = " div.price-wrapper .regular-price";
    String priceDiscountCSSSelector = " div.price-wrapper .campaign-price";

    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("chrome");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkThatCorrectPageOpensForProduct() {

        driver.get(baseURL);

        //Getting properties of elements on main page
        productNameMainPage = driver.findElement(By.cssSelector(firstProductCampaignsCSSSelector + productNameCSSSelectorMainPage));
        priceRegularMainPage = driver.findElement(By.cssSelector(firstProductCampaignsCSSSelector + priceRegularCSSSelector));
        priceDiscountMainPage = driver.findElement(By.cssSelector(firstProductCampaignsCSSSelector + priceDiscountCSSSelector));

        productNameMainPage_Name = productNameMainPage.getText();
        priceRegularMainPage_Value = priceRegularMainPage.getText();
        priceDiscountMainPage_Value = priceDiscountMainPage.getText();

        priceRegularMainPage_Color = priceRegularMainPage.getCssValue("color");
        priceRegularMainPage_Strike = priceRegularMainPage.getCssValue("text-decoration");
        priceDiscountMainPage_Color = priceDiscountMainPage.getCssValue("color");
        priceDiscountMainPage_Bold = priceDiscountMainPage.getCssValue("font-weight");

        //Open Product Page (click on product)
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(firstProductCampaignsCSSSelector))).click();

        //Getting properties of elements on product page
        productNameProductPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productNameCSSSelectorProductPage)));
        priceRegularProductPage = driver.findElement(By.cssSelector(priceRegularCSSSelector));
        priceDiscountProductPage = driver.findElement(By.cssSelector(priceDiscountCSSSelector));

        productNameProductPage_Name = productNameProductPage.getText();
        priceRegularProductPage_Value = priceRegularProductPage.getText();
        priceDiscountProductPage_Value = priceDiscountProductPage.getText();

        priceRegularProductPage_Color = priceRegularProductPage.getCssValue("color");
        priceRegularProductPage_Strike = priceRegularProductPage.getCssValue("text-decoration");
        priceDiscountProductPage_Color = priceDiscountProductPage.getCssValue("color");
        priceDiscountProductPage_Bold = priceDiscountProductPage.getCssValue("font-weight");

        // Asserts
        Assert.assertEquals(productNameMainPage_Name, productNameProductPage_Name);
        Assert.assertEquals(priceRegularMainPage_Value, priceRegularProductPage_Value);
        Assert.assertEquals(priceDiscountMainPage_Value, priceDiscountProductPage_Value);

        if (driver instanceof FirefoxDriver | driver instanceof InternetExplorerDriver) {
            Assert.assertEquals(priceRegularMainPage_Strike, priceRegularProductPage_Strike);
            Assert.assertEquals(priceDiscountMainPage_Color, priceDiscountProductPage_Color);
            Assert.assertEquals("900", priceDiscountMainPage_Bold);
            Assert.assertEquals("700", priceDiscountProductPage_Bold);
        } else if (driver instanceof ChromeDriver) {
            Assert.assertEquals("rgba(119, 119, 119, 1)", priceRegularMainPage_Color);
            Assert.assertEquals("rgba(102, 102, 102, 1)", priceRegularProductPage_Color);
            Assert.assertEquals("line-through solid rgb(119, 119, 119)", priceRegularMainPage_Strike);
            Assert.assertEquals("line-through solid rgb(102, 102, 102)", priceRegularProductPage_Strike);
            Assert.assertEquals(priceDiscountMainPage_Color, priceDiscountProductPage_Color);
            Assert.assertEquals(priceDiscountMainPage_Bold, priceDiscountProductPage_Bold);
        } else {
            System.out.println("Your browser is not supported");
        }
    }


    @After
    public void cleanupTest() {
        driver.quit();
        driver = null;
    }
}
