package tests;

import core.InitialSetup;
import core.UiSelector;
import core.constants.Credentials;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import javafx.stage.Stage;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.InitialSetup;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class Flows {
    @Test
    public static void selfScanCheckout(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Self-Scan Checkout Golden Path";
        String userID = "0744335566";
        String password = "Pw0744335566";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        WebDriverWait wait = new WebDriverWait(driver, 5000);

        Features.login(driver, caps, userID, password, environment);

        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cart\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_self_checkout\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_pay\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\")")))).click();

        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_message\")")
                    .isDisplayed());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        driver.quit();
    }

    @Test
    public static void eShopCheckout(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "EShopCheckout Golden Path";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password, environment);
        Features.addEshopProduct(driver, caps);

    }

    @Test
    public static void inputNewAddress(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Input new Address";
        String userID = "0744999000";
        String password = "Pw0744999000";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        Features.login(driver, caps, userID, password, environment);
        Features.addNewAddress(driver, caps);
        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_name\")").getText().equals("Prenume De test Nume De test"));
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
    }

    @Test
    public static void incrementNumberOfItems() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void decrementNumberOfItems() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void addMultipleItemsToBasket() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void removeItemFromBasket() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void multipleChancesEvents(Credentials.Environment environment) throws MalformedURLException, InterruptedException {

        // add an endpoint request to remove the user from the enscribed element
        String sessionName = "Add chances to a Multiple chance event";
        String userID = "0744555999";
        String password = "Pw0744555999";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        Features.login(driver, caps, userID, password, environment);
        Features.enterAFGExperiente(driver, caps);
    }

    @Test
    public static void donateAFGPointsToSingleEvent() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void favoriteProductsAddItemToFavoritesFromEShop(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "EShopCheckout Golden Path";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password, environment);
        Features.addItemToEShopBasket(driver,caps);
    }

    @Test
    public static void favoriteProductsAddItemToFavoritesFromEShopBasket() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void favoriteProductsRemoveItemFromEShopBasket() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void favoriteProductsRemoveItemFromFavoritesList() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void favoriteProductsAddItemToBasketFromFavoritesList() throws MalformedURLException, InterruptedException {
    }
}
