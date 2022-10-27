package practice;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStack {

	  public static String userName = "mdnasiribnehussa_BvaIyP";
	  public static String accessKey = "dLrG7HyYBoEzS9P9FWJx";

	  public static String app_url = "bs://7ab65d0017839b13d183197511011024966c7bb7";
	  
	  public static void main(String args[]) throws MalformedURLException, InterruptedException {
	    DesiredCapabilities caps = new DesiredCapabilities();

	    caps.setCapability("device", "Samsung Galaxy S8 Plus");
	    caps.setCapability("os_version", "7.0");
	    caps.setCapability("project", "My First Project");
	    caps.setCapability("build", "My First Build");
	    caps.setCapability("name", "Bstack-[Java] Sample Test");
	    caps.setCapability("app", app_url);

	    WebDriver driver = new RemoteWebDriver(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]")).sendKeys("secret_sauce");
		String titleString = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")).getText();
		System.out.println(titleString);

	    // The driver.quit statement is required, otherwise the test continues to execute, leading to a timeout.
	    driver.quit();
	  }
}
