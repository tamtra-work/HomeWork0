/*
Task 9: Create multi-layered test for scenario “Cart operations Add and Remove products” (Task6)

1. Create separate package in your test repository and hold all new files and folders there
2. Implement Task6 using multi-layered architecture
3. Send me the link to your scenario on GitHub

 */
package glselenium.task9;

import glselenium.TestSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class Task9_MultiLayeredTest extends TestSettings {

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    CheckoutPage checkoutPage = new CheckoutPage();


    @Before
    public void setupTest() {
        WebDriver driver = getBrowser("chrome");
        driver.manage().window().maximize();
    }


    //TODO: test fails if the same duck is added twice or more, fix it
    @Test
    public void test6_CartOperationsAddAndRemoveProduct() {

        for (int i = 0; i < 3; i++) {
            homePage.goToHome(driver);
            homePage.openFirstPopularDuck();
            productPage.addDuckToCart();
        }

        checkoutPage.goToCheckout();
        checkoutPage.deleteAllItemsCart();
        checkoutPage.verifyCartEmpty();

    }

    @After
    public void cleanupTest() {

        driver.quit();
        driver = null;
    }


}
