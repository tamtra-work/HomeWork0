/*
1. Login to admin panel http://localhost/litecart/admin
2. Open Catalog (Left menu)
3. Click “Add New Product” (right top)
4. Fill all fields on General, Information and Prices tabs (all other tabs as well as Campaigns on Prices may not be filled)

        Note: You have to put “Product picture” file in project (resource) folder and use relative path instead of absolute:

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("image.png").getFile());
        fileField.sendKeys(file.getAbsolutePath());

5. Save the Product
6. Make sure that new product appeared in catalog (on admin page, client page verification is not necessary)
7. Send me the link to your scenario on GitHub
*/
package glselenium;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task5_AddNewItemTest extends TestSettings {

    Date date = new Date();
    long timeSuffix = date.getTime();

    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("");
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
    }


    @Test
    public void addNewItemToCatalog() {

        //TODO: create separate method for login to admin page
        driver.get(baseURL + "/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li/a[contains(@href,\"catalog\")]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();

        //Fill all fields on General tab
        driver.findElement(By.cssSelector("input[type=\"radio\"][name=\"status\"][value=\"1\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"text\"][name=\"name[en]\"]")).sendKeys("MySuperDuck-" + timeSuffix);
        driver.findElement(By.cssSelector("input[type=\"text\"][name=\"code\"]")).sendKeys("Code-MySuperDuck-" + timeSuffix);
        driver.findElement(By.cssSelector("input[type=\"checkbox\"][name=\"categories[]\"][data-name=\"Rubber Ducks\"]")).click(); // root category is selected by default, no need to select

        Select defaultCategoryDropdown = new Select(driver.findElement(By.cssSelector("select[name=\"default_category_id\"]")));
        defaultCategoryDropdown.selectByVisibleText("Rubber Ducks");

        driver.findElement(By.cssSelector("input[type=\"checkbox\"][name=\"product_groups[]\"][value=\"1-3\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"number\"][name=\"quantity\"]")).sendKeys("15");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("HolmesDuck.jpg").getFile());  // needs to be wrapped in try-catch
        driver.findElement(By.cssSelector("input[type=\"file\"][name=\"new_images[]\"]")).sendKeys(file.getAbsolutePath());  //TODO: for some reason image is not uploaded, probably because of ' and space in path


        driver.findElement(By.cssSelector("input[type=\"date\"][name=\"date_valid_from\"]")).sendKeys("2017-11-01");
        driver.findElement(By.cssSelector("input[type=\"date\"][name=\"date_valid_to\"]")).sendKeys("2018-11-01");

        //Fill all fields on Information Tab
        driver.findElement(By.cssSelector("a[href=\"#tab-information\"]")).click();

        Select manufacturerDropdown = new Select(driver.findElement(By.cssSelector("select[name=\"manufacturer_id\"]")));
        manufacturerDropdown.selectByVisibleText("ACME Corp.");

        driver.findElement(By.cssSelector("input[type=\"text\"][name=\"short_description[en]\"]")).sendKeys("Short description - " + timeSuffix);
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Description - " + timeSuffix);
        driver.findElement(By.cssSelector("input[type=\"text\"][name=\"head_title[en]\"]")).sendKeys("Head title - " + timeSuffix);
        driver.findElement(By.cssSelector("input[type=\"text\"][name=\"meta_description[en]\"]")).sendKeys("Meta description - " + timeSuffix);

        //Fill all fields on Prices tab
        driver.findElement(By.cssSelector("a[href=\"#tab-prices\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"number\"][name=\"purchase_price\"]")).sendKeys("100500");

        Select purchasePriceDropdown = new Select(driver.findElement(By.cssSelector("select[name=\"purchase_price_currency_code\"]")));
        purchasePriceDropdown.selectByValue("EUR");

        Select taxClassDropdown = new Select(driver.findElement(By.cssSelector("select[name=\"tax_class_id\"]")));
        taxClassDropdown.selectByVisibleText("Standard");

        //driver.findElement(By.cssSelector("input[name=\"prices[USD]\"]")).sendKeys("100");
        //driver.findElement(By.cssSelector("input[name=\"gross_prices[USD]\"]")).sendKeys("150");
        //driver.findElement(By.cssSelector("input[name=\"prices[EUR]\"]")).sendKeys("200");
        //driver.findElement(By.cssSelector("input[name=\"gross_prices[EUR]\"]")).sendKeys("250");


        // Save new duck
        driver.findElement(By.cssSelector("button[type=\"submit\"][name=\"save\"]")).click();

        //Make sure that new product appeared in catalog (on admin page, client page verification is not necessary)
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li/a[contains(@href,\"catalog\")]")).click();
        Assert.assertNotNull(driver.findElement(By.xpath("//a[contains(text(),'MySuperDuck-" + timeSuffix + "')]")));
    }


    @After
    public void cleanupTest() {

        // Delete duck that has been created
        // Let's assume that only one duck could have been added
        driver.get(baseURL + "/admin");
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li/a[contains(@href,\"catalog\")]")).click();

        List<WebElement> addedDuck = driver.findElements(By.xpath("//a[contains(text(),'MySuperDuck-" + timeSuffix + "')]"));
        if (addedDuck.size() > 0) {
            addedDuck.get(0).click();
            driver.findElement(By.cssSelector("button[type=\"submit\"][name=\"delete\"]")).click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        driver.quit();
        driver = null;
    }
}
