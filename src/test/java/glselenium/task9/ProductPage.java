package glselenium.task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    String selectDuckSizeDropdownCSS = "select[name='options[Size]']";
    String addCartProductButtonCSS = "button[type='submit'][name='add_cart_product']";
    String defaultDuckSize = "Medium";

    int quantityCartBeforeAdding;

    WebElement addCartProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(addCartProductButtonCSS)));
    Select sizeDropdown;

    public void addDuckToCart() {
        //Add product to cart and wait until counter of the items in the cart changes
        quantityCartBeforeAdding = getQuantityCart();
        setDuckSize();
        addCartProductButton.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(quantityCartCSS), String.valueOf(quantityCartBeforeAdding + 1)));
    }

    public void setDuckSize() {
        if (driver.findElements(By.cssSelector(selectDuckSizeDropdownCSS)).size() != 0) {
            sizeDropdown = new Select(driver.findElement(By.cssSelector(selectDuckSizeDropdownCSS)));
            sizeDropdown.selectByValue(defaultDuckSize);
        }
    }
}
