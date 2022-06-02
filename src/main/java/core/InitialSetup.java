package core;

import core.constants.Credentials;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class InitialSetup {
    public static DesiredCapabilities initiateCapabilities(String sessionName, Credentials.Environment environment) {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "danchivu_JrO3PZ");
        caps.setCapability("browserstack.key", "7MrEyLYzpzQEJsgqrzqv");

        // Set URL of the application under test

        switch (environment) {
            case STAGE: //4.12 Staging
                caps.setCapability("app", "bs://f8707c2532b1017af3465aaa392502bf1f889fd9");
                break;
            case PROD:
                //4.12 Prod
                caps.setCapability("app", "bs://3ad02a4fcba5275b4602080bf00a438f8967e121");
                break;
        }


        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S20 Ultra");
        caps.setCapability("os_version", "10.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "FSD Carrefour Automation Staging");
        caps.setCapability("build", "SP1");
        caps.setCapability("name", sessionName);

        return caps;
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
    }

    public static AndroidDriver<AndroidElement> initiateDriver(Capabilities caps) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);
        return driver;
    }

    public static void allowPolicies(AndroidDriver driver, Credentials.Environment environment) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        if (driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")").isEnabled()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")").click();
        }
        if (environment == Credentials.Environment.STAGE) {
            wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\")")))).click();
        }


       /* try {
            UiObject.click(wait,driver, "com.carrefourpay.ro:id/btn_activate_all");
        } catch (Exception e) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();
        }*/
        //  driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();*/
    }
}

