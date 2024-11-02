package testBase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URL;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	public static AndroidDriver driver;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set Desired capabilities for Android device
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "14.0");
		desiredCapabilities.setCapability("appium:deviceName", "myemulator");
		desiredCapabilities.setCapability("appium:appPackage", "com.example.deltatestapp");
		desiredCapabilities.setCapability("appium:appActivity", "com.example.deltatestapp.MainActivity");
		desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
		// Create an instance of the Appium driver
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), desiredCapabilities);
		
		// If keyboard open when launch
		if(driver.isKeyboardShown()) 
		{   
			driver.hideKeyboard(); 
		}
	}

	@AfterClass
	public void tearDown() {
		driver.terminateApp("com.example.deltatestapp");
		driver.quit();
	}

	public String randomString() {
        return RandomStringUtils.randomAlphabetic(4);
	}

	public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
	}

	public String alphaNumeric() {
		String genaratedString = RandomStringUtils.randomAlphabetic(3);
		String genaratedNumber = RandomStringUtils.randomNumeric(3);
		return (genaratedString + genaratedNumber);
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
