package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {

	private WebDriverWait wait;
	
	public HomePage(AndroidDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='welcomeNote']")
    private WebElement welcomeText;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\'signoutLink\']")
    private WebElement signoutBtn;


    // Actions
    public void waitForTextUpdate() {
		wait.until(ExpectedConditions.textToBePresentInElement(welcomeText, "Welcome"));
	}
    
    public String getWelcomeText() {
        return welcomeText.getText();
    }
    
    public void clickSignoutBtn() {
    	signoutBtn.click();
    }
}
