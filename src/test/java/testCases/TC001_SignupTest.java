package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninPage;
import pageObjects.SignupPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC001_SignupTest extends BaseClass {
	@Test(dataProvider = "SignupData", dataProviderClass = DataProviders.class)
	public void verifySignUpFuntionality(String username, String email, String password, String gender, String city,
			String result, String invalidField){
		SignupPage signup = new SignupPage(driver);
		SigninPage signin = new SigninPage(driver);
		HomePage home = new HomePage(driver);

		signin.navigateTosignupScreen();
		String actualTitle = signup.getSignupScreenTitle();
		Assert.assertEquals(actualTitle, "Sign up", "Title text match!");

		signup.setUsername(username);
		signup.setEmail(email);
		signup.setPassword(password);
		signup.chooseGender(gender);
		signup.selectCity(city);
		Assert.assertEquals(signup.getTheSelectedCity(), city, "Selected value match!");
		signup.checkTerms();
		boolean isSignUpBtnEnabled = signup.checkTheSignUpBtnStatus();
		
		/*
		 * Scenario valid it should be allowed to sign up
		 */
		if (result.equalsIgnoreCase("valid")) {
			if (isSignUpBtnEnabled) {
				Assert.assertTrue(true);
				signup.clickSignupBtn();
				home.waitForTextUpdate();
				String welcomeNote = home.getWelcomeText();
				Assert.assertEquals(welcomeNote, "Welcome " + username, "Welcome note value match!");
				home.clickSignoutBtn();
			} else {
                Assert.fail();
			}
		}

		/*
		 * Scenario invalid it should not submit sign up
		 */
		if (result.equalsIgnoreCase("invalid")) {
			if (!isSignUpBtnEnabled) {
				// Verify sign up button Accessibility is disabled
				Assert.assertTrue(signup.isBtnAccessibilityDisabled());
				if (invalidField.equalsIgnoreCase("email")) {
					Assert.assertEquals(signup.getEmailErroMsg(), "Email format should be like abc@mail.com");
				}
				if (invalidField.equalsIgnoreCase("password")) {
					Assert.assertEquals(signup.getPasswordErrorMsg(), "Password must be at least 6 characters long");
				}
				signup.navigateTosigninScreen();
			} else {
                Assert.fail();
			}
		}
	}
}
