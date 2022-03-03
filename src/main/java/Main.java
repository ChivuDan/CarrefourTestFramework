import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.net.MalformedURLException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException, InterruptedException{


    DesiredCapabilities caps = new DesiredCapabilities();

    // Set URL of the application under test

    // Specify device and os_version for testing
            caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability("platformName", "Android");
          //  caps.setCapability("app", "C:\\Users\\Dan Chivu\\Downloads\\Carrefour 4.10.0 debug gmsStaging 02.01.2022.apk");

        // Initialise the remote Webdriver using Appium local URL
    // and desired capabilities defined above
    AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    driver.unlockDevice();
    driver.findElementByAndroidUIAutomator("new UiSelector().description(\"Carrefour\")").click();

    // Write your test case statements here

    // Invoke driver.quit() after the test is done to indicate that the test is completed.
            driver.quit();

}}
