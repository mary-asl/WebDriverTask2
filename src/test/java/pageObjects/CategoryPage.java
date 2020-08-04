package pageObjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Screenshoter;

import java.util.*;

public class CategoryPage extends AbstractPage {

    private static final By ITEM_FROM_LIST_LOCATOR = By.xpath("//div[@class='dtList-inner']");
    private static final By FILTER_BY_RATE_LOCATOR = By.cssSelector("#rate");
    private static final By ITEMS_RATE_LOCATOR = By.xpath("//span[@class='dtList-comments-count c-text-sm']");
    private static final By FILTER_BY_PRICE_BTN_LOCATOR = By.cssSelector("#price");
    private static final By ITEMS_PRICE_LOCATOR = By.xpath("//span[@class='price']/ins[@class='lower-price']");
    private static final By FILTER_BY_DISCOUNT_BTN_LOCATOR = By.xpath("//div/a[@id='sale']/span");
    private static final By ITEMS_DISCOUNTS_LOCATOR = By.xpath("//span[@class='price-sale active']");
    private static final By ITEMS_NAME_LOCATOR = By.xpath("//span[@class='goods-name c-text-sm']");
    private static final By SEARCH_INPUT_LOCATOR = By.id("tbSrch");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage selectItem() {
        waitForElementVisible(ITEM_FROM_LIST_LOCATOR);
        getDriver().findElement(ITEM_FROM_LIST_LOCATOR).click();
        return new ItemPage(getDriver());
    }

    public String getSearchingItemName() {
        waitForElementVisible(ITEMS_NAME_LOCATOR);
        return getDriver().findElement(ITEMS_NAME_LOCATOR).getText();
    }

    public String getInputValue() {
        waitForElementVisible(SEARCH_INPUT_LOCATOR);
        return getDriver().findElement(SEARCH_INPUT_LOCATOR).getAttribute("value");
    }

    public List<WebElement> getItemsRate() {
        waitForElementVisible(ITEMS_RATE_LOCATOR);
        return getDriver().findElements(ITEMS_RATE_LOCATOR);
    }

    public List<WebElement> getItemsPrice() {
        getDriver().navigate().refresh();
        waitForElementPresent(ITEMS_PRICE_LOCATOR);
        return getDriver().findElements(ITEMS_PRICE_LOCATOR);
    }

    public List<WebElement> getItemsDiscount() {
        getDriver().navigate().refresh();
        waitForElementPresent(ITEMS_DISCOUNTS_LOCATOR);
        return getDriver().findElements(ITEMS_DISCOUNTS_LOCATOR);
    }

    public CategoryPage filterByRate() {
        waitForElementVisible(FILTER_BY_RATE_LOCATOR);
        highlightElement(FILTER_BY_RATE_LOCATOR);
        getDriver().findElement(FILTER_BY_RATE_LOCATOR).click();
        Screenshoter.makeFullPageScreenshot(getDriver());
        unHighlightElement(FILTER_BY_RATE_LOCATOR);
        return this;
    }

    public CategoryPage filterByPrice() {
        waitForElementVisible(FILTER_BY_PRICE_BTN_LOCATOR);
        highlightElement(FILTER_BY_PRICE_BTN_LOCATOR);
        getDriver().findElement(FILTER_BY_PRICE_BTN_LOCATOR).click();
        Screenshoter.makeFullPageScreenshot(getDriver());
        unHighlightElement(FILTER_BY_PRICE_BTN_LOCATOR);
        return this;
    }

    public CategoryPage filterByDiscount() {
        waitForElementVisible(FILTER_BY_DISCOUNT_BTN_LOCATOR);
        highlightElement(FILTER_BY_DISCOUNT_BTN_LOCATOR);
        getDriver().findElement(FILTER_BY_DISCOUNT_BTN_LOCATOR).click();
        Screenshoter.makeFullPageScreenshot(getDriver());
        unHighlightElement(FILTER_BY_DISCOUNT_BTN_LOCATOR);
        return this;
    }

    public List<Integer> parseIntPrices() {
        List<Integer> intPrices = new ArrayList<Integer>();
        List<WebElement> prices = getItemsPrice();

        for (int i = 0; i < prices.size(); i++) {
            intPrices.add(Integer.parseInt(StringUtils
                    .substringBefore(prices.get(i).getText()
                            .replaceAll("\\s+", ""), "тг")));
        }
        return intPrices;
    }

    public List<Integer> parseIntRates() {
        List<Integer> intRates = new ArrayList<Integer>();

        for (int i = 0; i < getItemsRate().size(); i++) {
            intRates.add(Integer.parseInt(getItemsRate().get(i).getText()));
        }
        return intRates;
    }

    public List<Double> parseDoubleDiscount() {
        List<Double> doubleDiscount = new ArrayList<Double>();
        List<WebElement> discounts = getItemsDiscount();

        for (int i = 0; i < discounts.size(); i++) {
            doubleDiscount.add(Double.parseDouble(discounts.get(i).getText().substring(0, 3)));
        }
        return doubleDiscount;
    }

    public Boolean areItemsDisplayed() {
        return getDriver().findElement(ITEM_FROM_LIST_LOCATOR).isDisplayed();
    }

}
