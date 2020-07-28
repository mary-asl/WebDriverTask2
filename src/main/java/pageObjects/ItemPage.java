package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ItemPage extends AbstractPage {

    private static final By READ_ALL_INFORM_BTN = By.xpath("//span//div[@class='for-link']/div[@class='c-link-in3-v1']");
    private static final By ACTUAL_ITEM_CATEGORY = By.xpath("//div[@class='params']//div//span[b='Жанры/тематика']/following::span[1]");
    private static final By ITEMS_SIZE_LOCATOR = By.xpath("//label[@data-size-name]");
    private static final By FAVORITES_BTN_LOCATOR = By.xpath("//div[@class='order']/button");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage selectSize() {
        if (getDriver().findElement(ITEMS_SIZE_LOCATOR).isDisplayed())
            getDriver().findElement(ITEMS_SIZE_LOCATOR).click();
        return this;
    }

    public SignInPage addToFavorites() {
        getDriver().findElement(FAVORITES_BTN_LOCATOR).click();
        return new SignInPage(getDriver());
    }

    public ItemPage readAllInformation() {
        int elementPosition = getDriver().findElement(READ_ALL_INFORM_BTN).getLocation().getY();
        String js = String.format("window.scroll(0, %s)", elementPosition);
        waitForElementVisible(READ_ALL_INFORM_BTN);
        ((JavascriptExecutor) getDriver()).executeScript(js);
        getDriver().findElement(READ_ALL_INFORM_BTN).click();
        return this;
    }

    public String getCategory() {
        String actualCategory = getDriver().findElement(ACTUAL_ITEM_CATEGORY).getText();
        return actualCategory;
    }
}
