package pageObjects;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class AbstractPage {
    public static final int WAIT_FOR_ELEMENT_SECONDS = 20;
    private WebDriver driver;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/src";
    public Logger logger = LogManager.getLogger();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void waitForElementPresent(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void redrawElement(By locator) {
        new  WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.
                refreshed(ExpectedConditions.stalenessOf(getDriver().findElement(locator))));
    }

    public void highlightElement(By locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", driver.findElement(locator));
    }

    public void unHighlightElement(By locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = '" + "none" + "'",  driver.findElement(locator));
    }

    public void makeScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenName + ".png");
            FileUtils.copyFile(screenshot, copy);
        } catch (IOException e) {
            logger.error("Failed to make screenshot");
        }
    }

    public void makeFullPageScreenshot() {
        try {
            String screenName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenName + ".png"));
        } catch (IOException e) {
            logger.error("Failed to make screenshot");
        }
    }

}
