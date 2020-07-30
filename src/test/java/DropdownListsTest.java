import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;

public class DropdownListsTest extends BaseForAllTests{
    @Test(description = "verify that current location was changed after changed the country")
    public void verifyCurrentLocation(){
        String expectedLocation = "Минск";
        HomePage homePage = new HomePage(driver).hoverToChangeLocaleBtn();
        homePage.clickToCountry("Belarus");
        Assert.assertEquals(homePage.getCurrentLocale().getText(), expectedLocation, "current location wasn't change");
    }
}
