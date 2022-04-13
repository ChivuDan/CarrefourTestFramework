package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class InitialSetup {
    public  static  DesiredCapabilities initiateCapabilities (String sessionName){
        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "danchivu_JrO3PZ");
        caps.setCapability("browserstack.key", "7MrEyLYzpzQEJsgqrzqv");

        // Set URL of the application under test
        caps.setCapability("app", "bs://37a24101d4d2bb8a7b37c0040f1042907c4d5be9");

        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S20 Ultra");
        caps.setCapability("os_version", "10.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "FSD Carrefour automation framework");
        caps.setCapability("build", "browserstack-build-2");
        caps.setCapability("name", sessionName);

        return caps;
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
    }
    public static AndroidDriver<AndroidElement> initiateDriver(Capabilities caps) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);
        return driver;
    }
    public static void allowPolicies(AndroidDriver driver){
    if(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")").isEnabled()){
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")").click();
    }
       /* if(driver.findElementByAndroidUIAutomator(new UiSelector().isClickable("com.carrefourpay.ro:id/btn_close")) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();
        }
*/
      /*  try {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_activate_all\")").click();
        } catch (Exception e){
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();
        }
      //  driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();*/

    }
}
