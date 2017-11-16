/*
Task 9: Create multi-layered test for scenario “Cart operations Add and Remove products” (Task6)

1. Create separate package in your test repository and hold all new files and folders there
2. Implement Task6 using multi-layered architecture
3. Send me the link to your scenario on GitHub

 */
package glselenium.task9;

import glselenium.TestSettings;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Task9_MultiLayeredTest extends TestSettings {

    WebDriver driver;
    WebDriverWait wait;

    HomePage homePage;
    ProductPage productPage;
    CheckoutPage checkoutPage;

    @Before
    public void setupTest() {
        driver = getBrowser("chrome");
        wait = new WebDriverWait(driver, 10);

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void test6_CartOperationsAddAndRemoveProduct() {

        for (int i = 0; i < 3; i++) {
            homePage.goToHome();
            homePage.openFirstPopularDuck();
            productPage.addDuckToCart();
        }

        checkoutPage.goToCheckout();
        checkoutPage.deleteAllItemsCart();
        homePage.goToHome();
        
        Assert.assertEquals(0, homePage.getQuantityCart());
    }

    @After
    public void cleanupTest() {
        driver.quit();
        driver = null;
    }
}
