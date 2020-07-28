import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.EksmoPage;
import pageObjects.HomePage;
import pageObjects.ItemPage;

import java.util.List;


public class EksmoPageTest extends BaseForAllTests {

    private static final String EKSMO_PAGE_LINK = "https://www.wildberries.kz/brands/eksmo";

    @Test
    public void filterByDiscount() {
        boolean actual = false;
        EksmoPage eksmoPage = new HomePage(driver).clickBrandLogo().selectCategory();
        CategoryPage categoryPage = new CategoryPage(driver).filterByDiscount();
        List<Double> doubleDiscounts = categoryPage.parseDoubleDiscount();
        outerloop:
        for (int i = 0; i < doubleDiscounts.size(); i++) {
            for (int j = i + 1; j < doubleDiscounts.size(); j++) {
                if (doubleDiscounts.get(i) > doubleDiscounts.get(j)) {
                    actual = false;
                    break outerloop;
                } else
                    actual = true;
            }
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void filterByRate() {
        CategoryPage categoryPage = new CategoryPage(driver).filterByRate();
        boolean actual = false;
        List<Integer> integerRates = categoryPage.parseIntRates();
        outerloop:
        for (int i = 0; i < integerRates.size(); i++) {
            for (int j = i + 1; j < integerRates.size(); j++) {
                if (integerRates.get(i) < integerRates.get(j)) {
                    actual = false;
                    break outerloop;
                } else
                    actual = true;
            }
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void filterByPrice() {
        boolean actual = false;
        CategoryPage categoryPage = new CategoryPage(driver).filterByPrice();
        List<Integer> integerPrices = categoryPage.parseIntPrices();
        outerloop:
        for (int i = 0; i < integerPrices.size(); i++) {
            for (int j = i + 1; j < integerPrices.size(); j++) {
                if (integerPrices.get(i) > integerPrices.get(j)) {
                    actual = false;
                    break outerloop;
                } else actual = true;
            }
        }
        Assert.assertTrue(actual);
    }

    @Test(description = "Verify that categories are displayed on the page")
    public void verifyDisplayedCategory() {
        driver.navigate().to(EKSMO_PAGE_LINK);
        EksmoPage eksmoPage = new EksmoPage(driver);
        Assert.assertTrue(eksmoPage.findCategoryBanners().isDisplayed());
    }

    @Test
    public void isCategoryCorrect() {
        ItemPage itemPage = new CategoryPage(driver).selectItem();
        itemPage.readAllInformation();
        String expected = "Психология";
        Assert.assertEquals(itemPage.getCategory(), expected);
    }
}
