package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private static final By EKSMO_LOGO_LOCATOR = By.xpath("//a[@href='/brands/eksmo']");
    private static final By ITEM_FROM_LIST_LOCATOR = By.xpath("//div[@class='dtList-inner']");
    private static final By BUTTON_NEXT_BRAND_LOCATOR = By.xpath("//div[@class='brands-pane j-brands-slider-wrapper i-slider-brand-pane']//a[@class='lSNext']/button");
    private static final By PROMO_PAGE_LOCATOR = By.xpath(".//a[contains(@href,'/promotions/chistim-sklad')]");
    private static final By NEXT_BTN_LOCATOR = By.xpath("//div/a/button[@class='btn-next']");
    private static final By SEARCH_INPUT_LOCATOR = By.id("tbSrch");
    private static final By SEARCH_BTN_LOCATOR = By.id("btnSrch");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CategoryPage clickToDiscountPage() {
        if (!isElementPresent(ITEM_FROM_LIST_LOCATOR)) {
            waitForElementVisible(NEXT_BTN_LOCATOR);
            do {
                getDriver().findElement(NEXT_BTN_LOCATOR).click();
            }
            while (!getDriver().findElement(PROMO_PAGE_LOCATOR).isDisplayed());
            getDriver().findElement(PROMO_PAGE_LOCATOR).click();
        }
        return new CategoryPage(getDriver());
    }

    public HomePage cleanInputSearch() {
        getDriver().findElement(SEARCH_INPUT_LOCATOR).clear();
        return this;
    }

    public CategoryPage searchForItem(String item) {
        waitForElementVisible(SEARCH_INPUT_LOCATOR);
        getDriver().findElement(SEARCH_INPUT_LOCATOR).sendKeys(item);
        waitForElementVisible(SEARCH_BTN_LOCATOR);
        getDriver().findElement(SEARCH_BTN_LOCATOR).click();
        return new CategoryPage(getDriver());
    }

    public EksmoPage clickBrandLogo() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,4650)");
        WebElement buttonNext = getDriver().findElement(BUTTON_NEXT_BRAND_LOCATOR);
        waitForElementVisible(BUTTON_NEXT_BRAND_LOCATOR);
        String js = String.format("window.scroll(0, %s)", buttonNext.getLocation().getY());
        ((JavascriptExecutor) getDriver()).executeScript(js);
        waitForElementEnabled(BUTTON_NEXT_BRAND_LOCATOR);
        do {
            buttonNext.click();
        }
        while (!getDriver().findElement(EKSMO_LOGO_LOCATOR).isDisplayed());
        getDriver().findElement(EKSMO_LOGO_LOCATOR).click();
        return new EksmoPage(getDriver());
    }
}