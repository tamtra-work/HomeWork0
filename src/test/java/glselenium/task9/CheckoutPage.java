package glselenium.task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends BasePage {

    String rowsXPath = "//*[@id=\"order_confirmation-wrapper\"]/table/tbody/tr";
    String dataTableCSS = ".dataTable";
    String removeCartItemButtonCSS = "button[type='submit'][name='remove_cart_item']";
    String duckPreviewXPath = "//*[@id=\"box-checkout-cart\"]/ul/li[1]/a";

    WebElement dataTableElement; // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(dataTableCSS)));
    WebElement removeButton;

    List rowsElement = driver.findElements(By.xpath(rowsXPath));
    int numberOfItemRows = rowsElement.size() - 5;


    public void deleteAllItemsCart() {

        for (int i = 0; i < numberOfItemRows; i++) {

            dataTableElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(dataTableCSS)));
            if (i < numberOfItemRows - 1) {
                driver.findElement(By.xpath(duckPreviewXPath)).click();
            }
            removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(removeCartItemButtonCSS)));
            removeButton.click();

            wait.until(ExpectedConditions.stalenessOf(dataTableElement));

        }

    }


}


