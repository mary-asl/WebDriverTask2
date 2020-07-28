import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;

import java.util.concurrent.TimeUnit;

public class TextSearchTest extends BaseForAllTests {

    private static final String LOOKING_FOR_ITEM = "funko pop star wars";

    @Test
    public void isItemFound() {
        CategoryPage categoryPage = new HomePage(driver).searchForItem(LOOKING_FOR_ITEM);
        boolean actual = false;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String[] subStr = categoryPage.getInputValue().split(" ");
        for(int i = 0; i < subStr.length; i++) {
            if(!StringUtils.containsIgnoreCase(categoryPage.getSearchingItemName(), subStr[i])){
                actual = false;
                break;
            }
            else
                actual = true;
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void verifyPageTitle() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new HomePage(driver).cleanInputSearch().searchForItem(LOOKING_FOR_ITEM);
        Assert.assertEquals(driver.getTitle(), LOOKING_FOR_ITEM);
    }

}
