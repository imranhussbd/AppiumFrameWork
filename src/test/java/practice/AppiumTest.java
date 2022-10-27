package practice;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class AppiumTest {
	
	@Test(enabled = false)
	public void simpleAppiumTestWithBrowser() throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability("platrformVersion", "12");
		//----------------------Alternative-----------------
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setVersion("11");
		capabilities.setBrowserName("Chrome");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		WebDriver driver = new RemoteWebDriver(url, capabilities);
		driver.get("https://www.amazon.com/");
	}
	
	@Test(enabled = false)
	public void appiumTestWithInstalledApp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setVersion("11");
		//App Activity, App package
		capabilities.setCapability("appPackage", "com.google.android.calculator");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		WebDriver driver = new RemoteWebDriver(url, capabilities);
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
	}
	
	@Test
	public void installingAppAndTest() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setVersion("11");
		capabilities.setCapability("app", new File( getClass().getClassLoader().getResource("apps/Demo.apk").getPath()).getAbsolutePath());
		
		WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		WebElement bikeLight = driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Bike Light']"));
		bikeLight.click();
		
		WebElement addToCart = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Add To Cart button']"));
		addToCart.click();
		
		WebElement cart = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='cart badge']"));
		cart.click();
		
		WebElement proceedToCheckout = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Proceed To Checkout button']"));
		proceedToCheckout.click();
		
		WebElement userName = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Username input field']"));
		userName.sendKeys("user005");
		
		WebElement pass = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password input field']"));
		pass.sendKeys("pass");
		
		WebElement login = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Login button']"));
		login.click();
		
		WebElement errorMsg = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='generic-error-message']/android.widget.TextView"));
		
		Assert.assertEquals(errorMsg.getText(),"Provided credentials do not match any user in this service.");
	}
	
}
