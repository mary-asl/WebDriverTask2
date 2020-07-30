package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends AbstractPage {

    private static final By SIGN_IN_FORM_LOCATOR = By.cssSelector(".signIn");

    public Boolean signInByPhoneNum(){
        waitForElementPresent(SIGN_IN_FORM_LOCATOR);
        return getDriver().findElement(SIGN_IN_FORM_LOCATOR).isDisplayed();
    }

    public SignInPage(WebDriver driver) {
        super(driver);
    }
}
