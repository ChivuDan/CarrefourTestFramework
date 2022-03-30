import java.net.URL;
import java.net.MalformedURLException;

import core.InitialSetup;
import core.UiSelector;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//demo class for Browserstack
public class Runner {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "danchivu_JrO3PZ");
        caps.setCapability("browserstack.key", "7MrEyLYzpzQEJsgqrzqv");

        // Set URL of the application under test
        caps.setCapability("app", "bs://f7aaacc5e194b66bbe725acc64d14457c2bc2bfb");

        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S20 Ultra");
        caps.setCapability("os_version", "10.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "FSD Carrefour automation framework");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "Log-in via phone number+password");


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps);
                InitialSetup.allowPolicies(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_login_register\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_user_id\")").sendKeys("0722812150");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
       // driver.findElementByAndroidUIAutomator(UiSelector.className("android.widget.TextView")).sendKeys("123987";
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")").sendKeys("123987");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_login\")").click();

assert(driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")").isDisplayed());
        // Write your test case statements here

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();

    }
}

