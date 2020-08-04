import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseForAllTests {

    protected WebDriver driver;
    private static final String BASE_URL = "https://www.wildberries.kz";
    public Logger logger = LogManager.getLogger();

    @BeforeClass
    public void initWebDriver() {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        try{
            driver.get(BASE_URL);
        }catch(WebDriverException e){
            System.out.println("WebDriverException occured");
        }
        driver.manage().window().maximize();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
