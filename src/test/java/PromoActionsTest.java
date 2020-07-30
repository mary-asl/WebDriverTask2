import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;

import java.util.List;


public class PromoActionsTest extends BaseForAllTests {

    private static final int ANNOUNCED_DISCOUNT = -50;

    @Test(description = "verify that all of products on the page have correct discount")
    public void verifyDiscount() {
        boolean actual = true;
        CategoryPage categoryPage = new HomePage(driver).clickToDiscountPage();
        List<Double> itemsDiscount = categoryPage.parseDoubleDiscount();
        for (int i = 0; i < itemsDiscount.size(); i++) {
            if (itemsDiscount.get(i) > ANNOUNCED_DISCOUNT) {
                actual = false;
                break;
            }
        }
        Assert.assertTrue(actual, "discount on items does not match the specified");
    }

    @Test(description = "verify that items are displayed")
    public void verifyDisplayedItems() {
        CategoryPage categoryPage = new HomePage(driver).clickToDiscountPage();
        Assert.assertTrue(categoryPage.areItemsDisplayed(), "there are no items on the page");
    }

    @Test(description = "verify that only authorized users can add item to favorites")
    public void verifyFavorites() {
        CategoryPage categoryPage = new HomePage(driver).clickToDiscountPage();
        categoryPage.selectItem().selectSize().addToFavorites();
        Assert.assertTrue(new SignInPage(driver).signInByPhoneNum(), "sign in page is not displayed");
    }
}