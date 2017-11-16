package glselenium.task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    String firstPopularDuckCSS = "//*[@id=\"box-most-popular\"]//li[1]";

    WebElement firstPopularDuckElement;

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void goToHome() {
        driver.get(baseURL);
    }

    public void openFirstPopularDuck() {
        firstPopularDuckElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstPopularDuckCSS)));
        firstPopularDuckElement.click();
    }
}
