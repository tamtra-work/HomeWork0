package glselenium.task9;

import glselenium.TestSettings;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends TestSettings {

    String checkoutLinkCSS = "a.link[href$='checkout']";
    String quantityCartCSS = "span.quantity";
    WebElement quantityCartElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(quantityCartCSS)));

    public void goToCheckout() {
        driver.findElement(By.cssSelector(checkoutLinkCSS)).click();
    }

    public int getQuantityCart() {
        return Integer.parseInt(quantityCartElement.getText());
    }

    public void verifyCartEmpty() {
        Assert.assertEquals(0, getQuantityCart());
    }

}
