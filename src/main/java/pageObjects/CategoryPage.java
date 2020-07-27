package pageObjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CategoryPage extends AbstractPage {

    private static final By ITEM_FROM_LIST_LOCATOR = By.xpath("//div[@class='dtList-inner']");
    private static final By FILTER_BY_RATE_LOCATOR = By.cssSelector("#rate");
    private static final By ITEMS_RATE_LOCATOR = By.xpath("//span[@class='dtList-comments-count c-text-sm']");
    private static final By FILTER_BY_PRICE_LOCATOR = By.cssSelector("#price");
    private static final By ITEMS_PRICE_LOCATOR = By.xpath("//ins[@class='lower-price']");
    private static final By FILTER_BY_DISCOUNT_LOCATOR = By.xpath("//div/a[@id='sale']/span");
    private static final By ITEMS_DISCOUNTS_LOCATOR = By.cssSelector("span.price-sale.active");
    private static final By ITEMS_NAME_LOCATOR = By.xpath("//span[@class='goods-name c-text-sm']");
    private static final By SEARCH_INPUT_LOCATOR = By.id("tbSrch");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage selectItem() {
        getDriver().findElement(ITEM_FROM_LIST_LOCATOR).click();
        return new ItemPage(getDriver());
    }

    public String getSearchingItemName(){
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,200)");
        waitForElementVisible(ITEMS_NAME_LOCATOR);
        return getDriver().findElement(ITEMS_NAME_LOCATOR).getText();
    }

    public String getInputValue(){
        waitForElementVisible(SEARCH_INPUT_LOCATOR);
        return getDriver().findElement(SEARCH_INPUT_LOCATOR).getAttribute("value");
    }

    public List<WebElement> getItemsRate() {
        waitForElementVisible(ITEMS_RATE_LOCATOR);
        return getDriver().findElements(ITEMS_RATE_LOCATOR);
    }

    public List<WebElement> getItemsPrice() {
        waitForElementVisible(ITEMS_PRICE_LOCATOR);
        return getDriver().findElements(ITEMS_PRICE_LOCATOR);
    }

    public List<WebElement> getItemsDiscount() {
        waitForElementVisible(ITEMS_DISCOUNTS_LOCATOR);
        return getDriver().findElements(ITEMS_DISCOUNTS_LOCATOR);
    }

    public CategoryPage filterByRate() {
        waitForElementVisible(FILTER_BY_RATE_LOCATOR);
        getDriver().findElement(FILTER_BY_RATE_LOCATOR).click();
        return this;
    }

    public CategoryPage filterByPrice() {
        waitForElementVisible(FILTER_BY_PRICE_LOCATOR);
        getDriver().findElement(FILTER_BY_PRICE_LOCATOR).click();
        return this;
    }

    public CategoryPage filterByDiscount() {
        waitForElementVisible(FILTER_BY_DISCOUNT_LOCATOR);
        getDriver().findElement(FILTER_BY_DISCOUNT_LOCATOR).click();
        return this;
    }

    public List<Integer> parseIntPrices() {
        List<Integer> intPrices = new ArrayList<Integer>();

        for (int i = 0; i < getItemsPrice().size(); i++) {
            intPrices.add(Integer.parseInt(StringUtils
                    .substringBefore(getItemsPrice().get(i).getText()
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

        for (int i = 0; i < getItemsDiscount().size(); i++) {
            doubleDiscount.add(Double.parseDouble(getItemsDiscount().get(i).getText().substring(0, 3)));
        }
        return doubleDiscount;
    }

    public Boolean areItemsDisplayed(){
        return getDriver().findElement(ITEM_FROM_LIST_LOCATOR).isDisplayed();
    }

}
