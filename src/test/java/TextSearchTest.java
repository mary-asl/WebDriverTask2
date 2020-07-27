import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextSearchTest extends BaseForAllTests {
    @Test
    public void verifyPageTitle() {
        HomePage homePage = new HomePage(driver);
        String lookingForToy = "funko pop star wars";
        homePage.searchForItem(lookingForToy);
        Assert.assertEquals(driver.getTitle(), lookingForToy);
    }

    @Test(dependsOnMethods = "verifyPageTitle")
    public void isItemFound() {
        CategoryPage categoryPage = new CategoryPage(driver);
        boolean actual = true;
        String[] subStr = categoryPage.getInputValue().split(" ");
        for(int i = 0; i < subStr.length; i++) {
            if(!StringUtils.containsIgnoreCase(categoryPage.getSearchingItemName(), subStr[i])){
                actual = false;
                break;
            }
        }
        Assert.assertTrue(actual);
    }

    @Test(dependsOnMethods = "isItemFound")
    public void verifyFavorites() {
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectItem().selectSize().addToFavorites();
        Assert.assertTrue(new SignInPage(driver).signInByPhoneNum());
    }

}
