package glselenium.task9;

import glselenium.TestSettings;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends TestSettings {

    WebDriverWait wait;

    String checkoutLinkCSS = "a.link[href$='checkout']";
    String quantityCartCSS = "span.quantity";

    WebElement quantityCartElement;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void goToCheckout() {
        driver.findElement(By.cssSelector(checkoutLinkCSS)).click();
    }

    public int getQuantityCart() {
        quantityCartElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(quantityCartCSS)));
        return Integer.parseInt(quantityCartElement.getText());
    }
}
