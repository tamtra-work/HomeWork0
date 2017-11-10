package glselenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class EventsHandler extends AbstractWebDriverEventListener {

    Date date = new Date();
    long currentTime = date.getTime();

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

        System.out.println("Searching for element " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

        System.out.println("Found element " + by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Path exceptionScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
        Path copyToPath = Paths.get("exceptionScreenshot" + currentTime + ".jpg");
        try {
            Files.copy(exceptionScreenshot, copyToPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
