import org.testng.Assert;
import org.testng.annotations.Test;


public class SecondScript extends BaseForAllTests {

    @Test(description = "Verify that category banners are displayed on the page")
    public void verifyDisplayedCategory() {
        EksmoPage eksmoPage = new HomePage(driver).clickBrandLogo();
        Assert.assertTrue(eksmoPage.findCategoryBanners().isDisplayed());
    }

    @Test(dependsOnMethods = "verifyDisplayedCategory")
    public void isCategoryCorrect() {
        ItemPage itemPage = new CategoryPage(driver).selectItem();
        itemPage.readAllInformation();
        String expected = "Психология";
        Assert.assertEquals(itemPage.getCategory(), expected);
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "verifyDisplayedCategory")
    public void filterByRate() {
        EksmoPage eksmoPage = new EksmoPage(driver).selectCategory();
        CategoryPage categoryPage = new CategoryPage(driver).filterByRate();
        boolean actual = true;
        for (int i = 0; i < categoryPage.getItemsRate().size(); i++) {
            for (int j = i + 1; j < categoryPage.getItemsRate().size(); j++) {
                if (categoryPage.parseIntRates().get(i) < categoryPage.parseIntRates().get(j)) {
                    actual = false;
                    break;
                }
            }
        }
        Assert.assertTrue(actual);
    }

    @Test(dependsOnMethods = "verifyDisplayedCategory", priority = 1)
    public void filterByPrice() {
        boolean actual = true;
        CategoryPage categoryPage = new CategoryPage(driver).filterByPrice();

        for (int i = 0; i < categoryPage.getItemsPrice().size(); i++) {
            for (int j = i + 1; j < categoryPage.getItemsPrice().size(); j++) {
                if (categoryPage.parseIntPrices().get(i) > categoryPage.parseIntPrices().get(j)) {
                    actual = false;
                    break;
                }
            }
        }
        Assert.assertTrue(actual);
    }

    @Test(dependsOnMethods = "filterByPrice")
    public void filterByDiscount() {
        boolean actual = true;
        CategoryPage categoryPage = new CategoryPage(driver).filterByDiscount();

        for (int i = 0; i < categoryPage.getItemsDiscount().size(); i++) {
            for (int j = i + 1; j < categoryPage.getItemsDiscount().size(); j++) {
                if (categoryPage.parseDoubleDiscount().get(i) > categoryPage.parseDoubleDiscount().get(j)) {
                    actual = false;
                    break;
                }
            }
        }
        Assert.assertTrue(actual);
    }
}
