import org.testng.Assert;
import org.testng.annotations.Test;


public class FirstScript extends BaseForAllTests {

    @Test(description = "Verify that items are displayed")
    public void verifyDisplayedItems() {
        CategoryPage categoryPage = new HomePage(driver).clickToBanner();
        Assert.assertTrue(categoryPage.areItemsDisplayed());
    }

    @Test(dependsOnMethods = "verifyDisplayedItems") // Verify that all displayed items have discount since 50%
    public void verifyDiscount() {
        boolean actual = true;
        CategoryPage categoryPage = new CategoryPage(driver);
        for (int i = 0; i < categoryPage.getItemsDiscount().size(); i++) {
            if (categoryPage.parseDoubleDiscount().get(i) > -50) {
                actual = false;
                break;
            }
        }
        Assert.assertTrue(actual);
    }

    @Test(dependsOnMethods = "verifyDisplayedItems")
    public void verifyFavorites() {
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectItem().selectSize().addToFavorites();
        Assert.assertTrue(new SignInPage(driver).signInByPhoneNum());
    }
}