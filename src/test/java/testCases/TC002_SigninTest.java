package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_SigninTest extends BaseClass {
	@Test(dataProvider = "SignupData", dataProviderClass = DataProviders.class)
	public void verifySignInFuntionality(String username, String email, String password, String gender, String city,
			String result, String invalidField) {
		SigninPage signin = new SigninPage(driver);
		HomePage home = new HomePage(driver);

		String actualTitle = signin.getSigninScreenTitle();
		Assert.assertEquals(actualTitle, "Sign in", "Title text match!");

		if (result.equalsIgnoreCase("valid")) {
			signin.setEmail(email);
			signin.setPassword(password);
			Assert.assertTrue(signin.checkTheSigninBtnStatus());
			signin.clickSigninBtn();
			home.waitForTextUpdate();
			String welcomeNote = home.getWelcomeText();
			Assert.assertEquals(welcomeNote, "Welcome " + username, "Welcome note value match!");
			home.clickSignoutBtn();
		}

		else if (result.equalsIgnoreCase("invalid")) {
			if (invalidField.equalsIgnoreCase("email")) {
				signin.setEmail(email);
				signin.setPassword(password);
				Assert.assertEquals(signin.getEmailErroMsg(), "Email format should be like abc@mail.com");
				// Verify sign up button Accessibility is disabled
				Assert.assertTrue(signin.isBtnAccessibilityDisabled());
			} else if (invalidField.equalsIgnoreCase("password")) {
				signin.clearSigninForm();
				signin.setEmail(email);
				signin.setPassword(email);
				Assert.assertTrue(signin.checkTheSigninBtnStatus());
				signin.clickSigninBtn();
				Assert.assertEquals(signin.getSigninFailureToastMsg(), "Invalid email or password");
			}
		}
	}
}
