import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private static final By EKSMO_LOGO_LOCATOR = By.xpath("//a[@href='/brands/eksmo']");
    private static final By BUTTON_NEXT_BRAND_LOCATOR = By.xpath("//div[9]//a[2]/button[@class='btn-next']");
    private static final By DISCOUNT_BANNER_LOCATOR = By.id("banner_12529ee6-9afc-449a-b568-6fdfba574386");
    private static final By NEXT_BTN_LOCATOR = By.xpath("//div/a[2]/button");
    private static final By SEARCH_INPUT_LOCATOR = By.id("tbSrch");
    private static final By SEARCH_BTN_LOCATOR = By.id("btnSrch");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CategoryPage clickToBanner() {
        do {
            getDriver().findElement(NEXT_BTN_LOCATOR).click();
        }
        while (!getDriver().findElement(DISCOUNT_BANNER_LOCATOR).isDisplayed());
        getDriver().findElement(DISCOUNT_BANNER_LOCATOR).click(); // Click on the banner "50% discount"
        return new CategoryPage(getDriver());
    }

    public CategoryPage searchForItem(String item) {
        getDriver().findElement(SEARCH_INPUT_LOCATOR).sendKeys(item);
        getDriver().findElement(SEARCH_BTN_LOCATOR).click();
        return new CategoryPage(getDriver());
    }

    public EksmoPage clickBrandLogo() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,4700)");
        WebElement buttonNext = getDriver().findElement(BUTTON_NEXT_BRAND_LOCATOR);
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