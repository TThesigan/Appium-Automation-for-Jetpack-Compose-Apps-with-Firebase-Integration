package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SigninPage extends BasePage {

    private Actions action;
    private WebDriverWait wait;

    public SigninPage(AndroidDriver driver) {
        super(driver);
        this.action = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='signinTitle']")
    private WebElement SigninTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='emailEditText']/android.view.View")
    private WebElement emailText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='emailErrorMsg']")
    private WebElement emailErrorMsg;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='passwordEditText']/android.view.View")
    private WebElement passwordText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='signinErrorMsg']")
    private WebElement signinErrorMsg;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='button_enabled' and @enabled='true']")
    private WebElement signinBtnEnabledProperty;

    @AndroidFindBy(accessibility = "button_disabled")
    private WebElement signinBtndisabledProperty;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='signinBtn']/android.widget.Button")
    private WebElement signinBtn;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='signupLink']/android.widget.Button")
    private WebElement signupPageLink;

    public String getSigninScreenTitle() {
        return SigninTitle.getText();
    }

    public void setEmail(String email) {
        emailText.click();
        action.sendKeys(email).perform();
    }

    public String getEmailErroMsg() {
        return emailErrorMsg.getText();
    }

    public void setPassword(String password) {
        passwordText.click();
        action.sendKeys(password).perform();
        driver.hideKeyboard();
    }

    public String getSigninErroMsg() {
        return signinErrorMsg.getText();
    }

    public boolean checkTheSigninBtnStatus() {
        boolean isButtonEnabled = false;
        try {
            if (signinBtnEnabledProperty.isEnabled()) {
                isButtonEnabled = true;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Sign in button disabled & sign in details not valid");
        }
        return isButtonEnabled;
    }

    public boolean isBtnAccessibilityDisabled() {
        return signinBtndisabledProperty.isDisplayed();
    }

    public void clickSigninBtn() {
        signinBtn.click();
    }

    public void navigateTosignupScreen() {
        driver.hideKeyboard();
        signupPageLink.click();
    }

    public String getSigninFailureToastMsg(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast")))
                .getText();
    }

    public void clearSigninForm(){
        clearTextField(emailText);
        clearTextField(passwordText);
    }

    public void clearTextField(WebElement textField) {
        action.moveToElement(textField)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
    }
}
