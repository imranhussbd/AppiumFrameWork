package base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import pages.CalculatorObject;
import static utils.IConstant.*;
import java.net.MalformedURLException;
import java.net.URL;
import utils.ReadProperties;

public class BaseClass {

	WebDriver driver;
	ReadProperties device_caps;
	ReadProperties app_caps;
	ReadProperties browserstack;
	
	protected CalculatorObject calculatorObject;
	
	@BeforeMethod
	public void setUp() {
		device_caps = new ReadProperties(DEVICE_CAPS);
		app_caps = new ReadProperties(APP_CAPS);
		browserstack = new ReadProperties(BROWSERSTACK);
		try {
			URL url = new URL(app_caps.getKey(URL));
			driver = new RemoteWebDriver(url, setCapabilitiesForInstalledApp());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		initClasses();
	}
	
	private DesiredCapabilities setCapabilitiesForInstalledApp() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(DEVICE_NAME, device_caps.getKey(DEVICE_NAME));
		dc.setPlatform(Platform.ANDROID);
		dc.setVersion(PLATFORM_VERSION);
		dc.setCapability(APP_PACKAGE, app_caps.getKey(APP_PACKAGE));
		dc.setCapability(APP_ACTIVITY, app_caps.getKey(APP_ACTIVITY));
		return dc;
	}
	
	private DesiredCapabilities setCapabilitiesForNotInstalledApp() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(DEVICE_NAME, device_caps.getKey(DEVICE_NAME));
		dc.setPlatform(Platform.ANDROID);
		dc.setCapability(PLATFORM_VERSION, device_caps.getKey(PLATFORM_VERSION));
		dc.setCapability(APP, app_caps.getKey(APP));
		return dc;
	}
	
	private DesiredCapabilities setCapabilitiesForMobileBrowser() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(DEVICE_NAME, device_caps.getKey(DEVICE_NAME));
		dc.setPlatform(Platform.ANDROID);
		dc.setCapability(PLATFORM_VERSION, device_caps.getKey(PLATFORM_VERSION));
		dc.setBrowserName(app_caps.getKey(BROWSER));
		return dc;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	private void initClasses() {
		calculatorObject = new CalculatorObject(driver);
	}
}
