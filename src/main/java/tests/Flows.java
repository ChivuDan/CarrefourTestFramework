package tests;

import core.InitialSetup;
import core.UiObject;
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
    public static void selfScanCheckout(Credentials.Environment environment, boolean finalStep) throws MalformedURLException, InterruptedException {
        String sessionName = "Self-Scan Checkout";
        String userID = "0744335566";
        String password = "Pw0744335566";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        WebDriverWait wait = new WebDriverWait(driver, 5000);

        Features.login(driver, caps, userID, password, environment);

        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cart\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_self_checkout\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_pay\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\")")))).click();

        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_barcode\")")
                    .isEnabled());
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Barcode not found\"}}");
        }
        if (finalStep) {
            ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"Passed\", \"reason\": \"Checkout barcode found\"}}");
            driver.quit();
        }
    }

    @Test
    public static void eShopCheckout(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "EShopCheckout";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password, environment);
        Features.addEshopProduct(driver, caps, true);
    }

    @Test
    public static void inputNewAddress(Credentials.Environment environment, boolean finalStep) throws MalformedURLException, InterruptedException {
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
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"Passed\", \"reason\": \"New Address successfully added\"}}");
            driver.quit();
        }
    }

    @Test
    public static void incrementNumberOfItems(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Increment Number of items in basket";
        String userID = "0744556600";
        String password = "Pw0744556600";
        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        Features.login(driver, caps, userID, password, environment);
        Features.addEshopProduct(driver, caps, false);
        Features.incrementNumberOfItemsInBasket(driver, caps,true);

    }

    @Test
    public static void decrementNumberOfItems(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Decrement Number of items in basket";
        String userID = "0744556600";
        String password = "Pw0744556600";
        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password, environment);
        Features.addEshopProduct(driver, caps, false);
        Features.decrementNumberOfItemsInBasket(driver, caps,true);
    }

    @Test
    public static void addMultipleItemsToBasket() throws MalformedURLException, InterruptedException {

    }

    @Test
    public static void removeItemFromBasket(Credentials.Environment environment,boolean finalStep) throws MalformedURLException, InterruptedException {
        String sessionName = "Remove item from basket";
        String userID = "0744999444";
        String password = "Pw0744999444";
        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
       if(finalStep) Features.login(driver,caps,userID,password,environment);
        Features.addEshopProduct(driver,caps,false);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        UiObject.click(wait,driver,"com.carrefourpay.ro:id/btn_online_checkout");
        Features.removeItemFromBasket(driver, caps,true);
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
        Features.enterAFGExperiente(driver, caps, true);
    }

    @Test
    public static void donateAFGPointsToSingleEvent() throws MalformedURLException, InterruptedException {
    }

    @Test
    public static void favoriteProductsAddItemToFavoritesFromEShop(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Add an item to favorites from EShop";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password, environment);
        //  Features.addItemToEShopBasket(driver,caps);
        Features.addItemToFavoritesFromEshop(driver, caps, true);
    }

    @Test
    public static void favoriteProductsAddItemToFavoritesFromEShopBasket(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Add item to favorites from Basket";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        Features.login(driver, caps, userID, password, environment);
        Features.addEshopProduct(driver, caps, false);
        Features.eShopBasketAddItemToFavorites(driver, caps);

    }


    @Test
    public static void favoriteProductsRemoveItemFromFavoritesList(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Add item to favorites from Basket";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password, environment);
        Features.addItemToFavoritesFromEshop(driver, caps, false);
        Features.removeItemFromFavoritesToBasket(driver, caps, true);
    }

    @Test
    public static void favoriteProductsAddItemToBasketFromFavoritesList(Credentials.Environment environment) throws MalformedURLException, InterruptedException {
        String sessionName = "Add item to Basket from favorites";
        String userID = "0744888000";
        String password = "Pw0744888000";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName, environment);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        Features.login(driver, caps, userID, password, environment);
        Features.addItemFromFavoritesToBasket(driver, caps);

    }
}
