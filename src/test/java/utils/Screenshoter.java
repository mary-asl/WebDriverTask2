package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Screenshoter {

    public static final int WAIT_FOR_ELEMENT_SECONDS = 20;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/src";
    public static Logger logger = LogManager.getLogger();

    public static void makeScreenshot(WebDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenName + ".png");
            FileUtils.copyFile(screenshot, copy);
        } catch (IOException e) {
            logger.error("Failed to make screenshot");
        }
    }

    public static void makeFullPageScreenshot(WebDriver driver) {
        try {
            String screenName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenName + ".png"));
        } catch (IOException e) {
            logger.error("Failed to make screenshot");
        }
    }
}
