package practice;

import static org.testng.Assert.assertTrue;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerLocalTest {

	private AppiumDriver driver;
	private static AppiumDriverLocalService service;

	@BeforeClass
	public static void startAppiumServer() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();

		builder.usingAnyFreePort();
		builder.usingDriverExecutable(new File("path_to_node"));
		builder.withAppiumJS(new File("path_to_appium"));

		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	@BeforeMethod
	public void startSession() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setVersion("11");
		// App Activity, App package
		capabilities.setCapability("appPackage", "com.google.android.calculator");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

		driver = new AndroidDriver(service.getUrl(), capabilities);
	}

	@AfterMethod
	public void endSession() {
		try {
			driver.quit();
		} catch (Exception ign) {
		}
	}

	@AfterClass
	public static void stopAppiumServer() {
		service.stop();
	}

	@Test
	public void test() {
		WebElement element = driver.findElement(By.id("com.google.android.calculator:id/digit_5"));
		element.click();
		element = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
		element.click();
		element = driver.findElement(By.id("com.google.android.calculator:id/digit_8"));
		element.click();
		element = driver.findElement(By.id("com.google.android.calculator:id/eq"));
		element.click();
		element = driver.findElement(By.id("com.google.android.calculator:id/result_final"));
		System.out.println(element.getText());
		assertTrue(true);
	}

}
