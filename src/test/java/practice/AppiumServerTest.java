package practice;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerTest {
	

	@Test
	public void startServer() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, 4723);
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		builder.withAppiumJS(new File("C:\\Users\\mdnas\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\appium.js"));
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
		
		service.start();
		System.out.println(service.getUrl().toString());
		service.stop();
	}
}
