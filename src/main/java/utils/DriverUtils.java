package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverUtils {

	private AppiumDriver driver;

	private String getAppPath() {
	 Properties prop=null;
	try {
		prop = new Properties();
		 FileInputStream fis = new FileInputStream("config.properties");
		 prop.load(fis);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 return prop.getProperty("appPath");
 }

	public DriverUtils initializeDriver() {
		// Fetch platform from VM arguments. Default is Android.
		String platform = System.getProperty("platform", "Android");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		try {
			if (platform.equalsIgnoreCase("Android")) {
				// Set Android-specific capabilities
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", "emulator-5554");
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("app", getAppPath());

				// Initialize Android driver and set it to ThreadLocal
				driver = new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);

			} else if (platform.equalsIgnoreCase("iOS")) {
				// Set iOS-specific capabilities
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", "iPhone 14");
				capabilities.setCapability("platformVersion", "16.4");
				capabilities.setCapability("automationName", "XCUITest");
				capabilities.setCapability("app", System.getProperty("user.dir") + "/app/latest.ipa");

				// Initialize iOS driver and set it to ThreadLocal
				driver = new IOSDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);

			} else {
				throw new Exception("Unsupported platform: " + platform);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Appium server URL is invalid", e);
		} catch (Exception e) {
			System.err.println("Unsupported platform:\n" + e.getLocalizedMessage());
		}
		return this;
	}

	public AppiumDriver getDriver() {
		if (driver == null) {
			throw new IllegalStateException("Driver not initialized. Please call initializeDriver first.");
		}
		return driver;
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
