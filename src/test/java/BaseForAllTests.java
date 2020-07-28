import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public abstract class BaseForAllTests {

    protected WebDriver driver;
    private static final String BASE_URL = "https://www.wildberries.kz";

    @BeforeClass
    public void initWebDriver() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/gecko/geckodriver.exe");
        driver = new FirefoxDriver();
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
