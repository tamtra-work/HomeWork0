/*
1. Open main page
2. Open first product from the list
3. Add the product to the cart (the product may already be present in the cart, it’s ok)
4. Wait until counter of the items in the cart changes
5. Back to the main page and repeat steps 1-4 twice to have overall 3 items in the cart
6. Open the Cart (click Checkout at right-top)
7. Remove all products from the cart one-by-one. After each removal wait until table at the bottom of the page refreshed
8. Verify that 0 items in the cart after removal

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task6_CartOperationsAddAndRemoveProductTest extends TestSettings {

    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("");
        driver.manage().window().maximize();
    }


    //TODO: test fails if the same duck is added twice or more, fix it
    @Test
    public void test6_CartOperationsAddAndRemoveProduct() {

        WebDriverWait wait = new WebDriverWait(driver, 10);


        for (int i = 0; i < 2; i++) {

            driver.get(baseURL);
            //Open first product from the list
            WebElement firstPopularDuck = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"box-most-popular\"]//li[1]")));
            firstPopularDuck.click();


            //Select duck size if needed
            if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() != 0) {

                Select sizeDropdown = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                sizeDropdown.selectByValue("Medium");
            }

            //Add product to cart and wait until counter of the items in the cart changes
            WebElement counterCartItemsElement = driver.findElement(By.cssSelector("span.quantity"));
            String counterCartItemsString = counterCartItemsElement.getText();
            int counterCartItemsInt = Integer.parseInt(counterCartItemsString) + 1;

            WebElement addCartProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit'][name='add_cart_product']")));
            addCartProductButton.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), String.valueOf(counterCartItemsInt)));
        }

        //6. Open the Cart (click Checkout at right-top)
        driver.findElement(By.cssSelector("a.link[href$='checkout']")).click();

        //7. Remove all products from the cart one-by-one. After each removal wait until table at the bottom of the page refreshed

        int numberOfRows = driver.findElements(By.xpath("//*[@id=\"order_confirmation-wrapper\"]/table/tbody/tr")).size() - 5;

        for (int i = 0; i < numberOfRows; i++) {

            WebElement dataTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dataTable")));
            if (i < numberOfRows - 1) {
                driver.findElement(By.xpath("//*[@id=\"box-checkout-cart\"]/ul/li[1]/a")).click();
            }
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit'][name='remove_cart_item']")));
            removeButton.click();

            wait.until(ExpectedConditions.stalenessOf(dataTable));

        }
        //8. Verify that 0 items in the cart after removal

        driver.get(baseURL);
        WebElement quantityAfterRemoval = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.quantity")));
        Assert.assertEquals("0", quantityAfterRemoval.getText());


        System.out.print("");


    }


    @After
    public void cleanupTest() {

        driver.quit();
        driver = null;
    }

}
