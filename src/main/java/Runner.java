import java.net.URL;
import java.net.MalformedURLException;

import core.InitialSetup;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
//demo class for Browserstack
public class Runner {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "danchivu_JrO3PZ");
        caps.setCapability("browserstack.key", "7MrEyLYzpzQEJsgqrzqv");

        // Set URL of the application under test
        caps.setCapability("app", "bs://8da6a596cfe72f0c5a3fa9bbb2b9e7f5a6c5a06d");

        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S20 Ultra");
        caps.setCapability("os_version", "10.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps);
                InitialSetup.allowPolicies(driver);
        // Write your test case statements here

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();

    }
}

