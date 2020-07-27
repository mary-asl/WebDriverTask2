import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;

import java.util.List;


public class PromoActionsTest extends BaseForAllTests {

    private static final int ANNOUNCED_DISCOUNT = -50;

    @Test
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
        Assert.assertTrue(actual);
    }

    @Test(description = "Verify that items are displayed")
    public void verifyDisplayedItems() {
        CategoryPage categoryPage = new CategoryPage(driver);
        Assert.assertTrue(categoryPage.areItemsDisplayed());
    }

    @Test
    public void verifyFavorites() {
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectItem().selectSize().addToFavorites();
        Assert.assertTrue(new SignInPage(driver).signInByPhoneNum());
    }
}