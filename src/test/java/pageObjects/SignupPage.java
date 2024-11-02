package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SignupPage extends BasePage {

	private Actions action;
	
	public SignupPage(AndroidDriver driver) {
		super(driver);
		this.action= new Actions(driver);
	}

	// Locators
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='signupTitle']")
    private WebElement signupTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='userNameEditText']/android.view.View")
    private WebElement usernameTxt;
    
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='emailEditText']/android.view.View")
    private WebElement emailTxt;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='emailErrorMsg']")
    private WebElement emailErrorMsg;
    
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='passwordEditText']/android.view.View")
    private WebElement passwordTxt;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='passwordErrorMsg']")
    private WebElement passwordErrorMsg;
    
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='radioM']")
    private WebElement maleRadioBtn;
    
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='radioF']")
    private WebElement famaleRadioBtn;
    
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='DropDownText']")
    private WebElement cityDropdownTxt;
    
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Dropdown arrow']")
    private WebElement cityDropdownArrow;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]")
    private WebElement cityJaffna;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]")
    private WebElement cityKandy;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[3]")
    private WebElement cityColombo;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[4]")
    private WebElement cityGalle;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[5]")
    private WebElement cityTrinco;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[6]")
    private WebElement cityNuwaraeliya;
    
    @AndroidFindBy(xpath = "//android.widget.CheckBox[@resource-id='TermsCheckBox']")
    private WebElement termsCheckBox;
    
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='signupBtn']/android.widget.Button")
    private WebElement signupBtn;
    
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='button_enabled' and @enabled='true']")
    private WebElement signupBtnEnabledProperty;
    
    @AndroidFindBy(accessibility = "button_disabled")
    private WebElement signupBtndisabledProperty;
    
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='signInLink']")
    private WebElement signinPageLink;


    // Actions
    public String getSignupScreenTitle() {
        return signupTitle.getText();
    }
    
    public void setUsername(String userName) {
    	usernameTxt.click();
    	action.sendKeys(userName).perform();
	}
    
    public void setEmail(String email) {
    	emailTxt.click();
    	action.sendKeys(email).perform();
	}
    
    public String getEmailErroMsg() {
    	return emailErrorMsg.getText();
    }
    
    public void setPassword(String password) {
    	passwordTxt.click();
    	action.sendKeys(password).perform();
    	driver.hideKeyboard(); 
	}
    
    public String getPasswordErrorMsg() {
    	return passwordErrorMsg.getText();
    }
    
    public void chooseGender(String gender) {
    	if (gender=="M"){
    		maleRadioBtn.click();	
    	}
    	else {
    		famaleRadioBtn.click();
    	}
    }
    
    public void selectCity(String cityName) {
    	cityDropdownArrow.click();
        switch (cityName) {
            case "Jaffna":
            	cityJaffna.click();
                break;
            case "Kandy":
            	cityKandy.click();
                break;
            case "Colombo":
            	cityColombo.click();
                break;
            case "Galle":
            	cityGalle.click();
                break;
            case "Trinco":
            	cityTrinco.click();
                break;
            case "Nuwara Eliya":
            	cityNuwaraeliya.click();
                break;
            default:
                break;
        }
    }
    
    public String getTheSelectedCity() {
    	return cityDropdownTxt.getAttribute("text");
    }
    
    public void checkTerms() {
    	termsCheckBox.click();
    }
    
    public boolean checkTheSignUpBtnStatus() {
    	boolean isButtonEnabled = false;
    	 try {
             if (signupBtnEnabledProperty.isEnabled()) {
            	 isButtonEnabled = true;
             }
         } catch (NoSuchElementException e) {
             System.out.println("Sign up button disabled & sign up details not valid");
         }
    	 return isButtonEnabled;
    }
    
    public boolean isBtnAccessibilityDisabled() {
    	return signupBtndisabledProperty.isDisplayed();
    }
    
    public void clickSignupBtn() {
    	signupBtn.click();
    }
    
    public void navigateTosigninScreen() {
    	signinPageLink.click();
    }
}
