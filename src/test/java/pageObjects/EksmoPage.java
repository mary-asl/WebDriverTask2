package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EksmoPage extends AbstractPage {

    private static final By CATEGORIES_LOCATOR = By.id("newsSmallBanners");
    private static final By PSYCHOLOGY_CATEGORY_LOCATOR = By.xpath("//div[@class='small-banners']/div[@class='number-5']");

    public EksmoPage(WebDriver driver) {
        super(driver);
    }

    public WebElement findCategoryBanners() {
        waitForElementVisible(CATEGORIES_LOCATOR);
        return getDriver().findElement(CATEGORIES_LOCATOR);
    }

    public EksmoPage selectCategory() {
        waitForElementEnabled(PSYCHOLOGY_CATEGORY_LOCATOR);
        getDriver().findElement(PSYCHOLOGY_CATEGORY_LOCATOR).click();
        return this;
    }
}
