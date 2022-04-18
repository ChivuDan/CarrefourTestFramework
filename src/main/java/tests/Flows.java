package tests;

import core.InitialSetup;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class Flows {
    @Test
    public static void selfScanCheckout() throws MalformedURLException, InterruptedException {
        String sessionName = "Self-Scan Checkout Golden Path";
        String userID = "0744335566";
        String password = "Pw0744335566";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;
        WebDriverWait wait = new WebDriverWait(driver, 5000);

        Features.login(driver, caps, userID, password);

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
    public static void eShopCheckout() throws MalformedURLException, InterruptedException {
        String sessionName = "EShopCheckout Golden Path";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login(driver, caps, userID, password);
        Features.addEshopProduct(driver, caps);

    }
    @Test
    public static void inputNewAddress() throws MalformedURLException, InterruptedException {
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
    public static void multipleChancesEvents() throws MalformedURLException, InterruptedException {
        String sessionName = "EShopCheckout Golden Path";
        String userID = "0744556600";
        String password = "Pw0744556600";

        DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName);
        AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
        JavascriptExecutor jse = driver;

        Features.login( driver,  caps,  userID, password);

    }
    @Test
    public static void donateAFGPointsToSingleEvent() throws MalformedURLException, InterruptedException {
    }
    @Test
    public static void favoriteProductsAddItemToFavoritesFromEShop() throws MalformedURLException, InterruptedException {
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
