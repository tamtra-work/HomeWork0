package glselenium.task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    String firstPopularDuckCSS = "//*[@id=\"box-most-popular\"]//li[1]";
    WebElement firstPopularDuckElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstPopularDuckCSS)));

    public void goToHome(WebDriver driver) {
        driver.get(baseURL);
    }

    public void openFirstPopularDuck() {
        firstPopularDuckElement.click();
    }
}
