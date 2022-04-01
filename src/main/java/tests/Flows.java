package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.sql.Driver;
import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Flows {
    @Test
    public static void login(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) {
        WebDriverWait wait = new WebDriverWait(driver, 5000);

        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_title\")")).isDisplayed()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\")").click();
        }
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_login_register\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_user_id\")").sendKeys("0722812150");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
        // driver.findElementByAndroidUIAutomator(UiSelector.className("android.widget.TextView")).sendKeys("123987";
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")").sendKeys("123987");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_login\")").click();
        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_send\")")).isDisplayed()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cancel\")").click();
        }
        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/container_consents\")")).isDisplayed()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_activate_all\")").click();
        }
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_logo_home\")").isDisplayed());
    }
    @Test
    public static void addItemToEShopBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5000);

        caps.setCapability("name", "Add Item To E-Shop Basket");

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/action_eshop\")").click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().text(\"IT & C +\")")))).click();

/*
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"IT & C +\")").click();
*/
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")").click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")").click();
        String itemAddedName = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_whole_price\")").getAttribute("text");
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cart\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cart\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_online_checkout\")").click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        try{ assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector()" +
                ".resourceId(\"com.carrefourpay.ro:id/tv_whole_price\")")
                .getAttribute("text").equals(itemAddedName));}
        catch (Exception e)
        {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
           // jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    @Test
    public static void logOut(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps){
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_logout\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
    }
    @Test
    public static void signUp(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 5000);

        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_title\")")).isDisplayed()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\")").click();
        }
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_login_register\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_user_id\")").sendKeys("testAcc@gmail.com");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_last_name\")").sendKeys("John");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_first_name\")").sendKeys("Doe");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_phone_number\")").sendKeys("0744333555");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")").sendKeys("Pw0744333555");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password_confirmation\")").sendKeys("Pw0744333555");
         TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(117, 1900))
        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(117, 963))
        .release();
        action.perform();
        Thread.sleep(1000);

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/cb_terms\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/cb_marketing\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_register\")").click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector()" +
                ".resourceId(\"com.carrefourpay.ro:id/btn_continue\")"))));

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cancel\")").click();
        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/container_consents\")")).isDisplayed()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_activate_all\")").click();
        }
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")")
                .isDisplayed());


    }
    @Test
    public static void deleteAccount(AndroidDriver<AndroidElement> driver, Capabilities caps) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 6000);
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")").click();
Thread.sleep(2000);
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_edit_profile\")").click();
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(117, 1900))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(117, 963))
                .release();
        action.perform();
        Thread.sleep(2000);
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_delete\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
        Thread.sleep(2000);
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"OK\")").click();

      //  driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();

    }
    public static void obtainAFGCode(AndroidDriver<AndroidElement> driver, Capabilities caps) throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_loyalty_enroll\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_forward\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_forward\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_forward\")").click();
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(68, 954))
                .release();
        action.perform();
       // driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/cb_terms\")").click();

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_enroll\")").click();
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tab_container\")").isEnabled());

    }
}