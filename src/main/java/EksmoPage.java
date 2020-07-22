import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EksmoPage extends AbstractPage {

    private static final By CATEGORY_BANNERS_LOCATOR = By.id("newsSmallBanners");
    private static final By PSYCHOLOGY_CATEGORY_LOCATOR = By.xpath("//div[@class='small-banners']/div[5]");

    public EksmoPage(WebDriver driver) {
        super(driver);
    }

    public WebElement findCategoryBanners() {
        waitForElementVisible(CATEGORY_BANNERS_LOCATOR);
        return getDriver().findElement(CATEGORY_BANNERS_LOCATOR);
    }

    public EksmoPage selectCategory() {
        waitForElementEnabled(PSYCHOLOGY_CATEGORY_LOCATOR);
        getDriver().findElement(PSYCHOLOGY_CATEGORY_LOCATOR).click();
        return this;
    }
}
