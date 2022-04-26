package tests;

import core.InitialSetup;
import core.UiObject;
import core.constants.Credentials;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class Features {
    @Test
    public static void login(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps, String userID, String password, Credentials.Environment environment) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        try {
            if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_title\")")).isEnabled()) {
                UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_close");
            }
        } catch (Exception e) {
            InitialSetup.allowPolicies(driver, environment);
        }
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_account");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_login_register");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_user_id\")"))).sendKeys(userID);
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_continue");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")"))));

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_password\")").sendKeys(password);
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_login");
        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_send\")")).isDisplayed()) {
            UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_cancel");
        }
        if (environment == Credentials.Environment.STAGE) {
            UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_activate_all");
        }
        /*if (((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/container_consents\")")).isEnabled())) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_activate_all\")").click();
        }*/
        /*
        The consents form has been removed from this state as of 4.12 on Prod

        if ((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/container_consents\")")).isDisplayed()) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_activate_all\")").click();
        }*/
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_logo_home\")").isDisplayed());
    }

    @Test
    public static void addItemToEShopBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        caps.setCapability("name", "Add Item To E-Shop Basket");

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/action_eshop\")").click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().text(\"IT & C\")")))).click();

/*
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"IT & C +\")").click();
*/
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")").click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_init_add_to_cart\")").click();
        String itemAddedName = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_whole_price\")").getAttribute("text");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cart\")"))));
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_cart\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_online_checkout\")").click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector()" +
                    ".resourceId(\"com.carrefourpay.ro:id/tv_whole_price\")")
                    .getAttribute("text").equals(itemAddedName));
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test passed!\"}}");

        // jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }

    @Test
    public static void logOut(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) {
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_account\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_logout\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_continue\")").click();
    }

    @Test
    public static void removeItemFromBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps, boolean finalStep) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")"))));
        Integer itemCount = Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")").getText());
        if (itemCount > 1) {
            decrementNumberOfItemsInBasket(driver, caps, false);
        } else if (itemCount == 1) {
            wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/iv_subtract\")"))));
            UiObject.click(wait, driver, "com.carrefourpay.ro:id/iv_subtract");
        }
        try {
            assertTrue(0 == Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_whole_price\")").getText()));
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test passed!\"}}");
            driver.quit();
        }
    }

    @Test
    public static void incrementNumberOfItemsInBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps, boolean finalStep) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_online_checkout");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")"))));
        Integer itemCount = Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")").getText());
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/iv_add");

        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")"))));
        try {
            assertTrue(itemCount + 1 == Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")")
                    .getText()));

        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test passed!\"}}");
            driver.quit();
        }
    }

    @Test
    public static void decrementNumberOfItemsInBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps, boolean finalStep) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_online_checkout");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")"))));
        Integer itemCount = Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")").getText());
        if (itemCount < 1) {
            UiObject.click(wait, driver, "com.carrefourpay.ro:id/iv_add");
        } else {
            wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")"))));
            itemCount = Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")").getText());
            UiObject.click(wait, driver, "com.carrefourpay.ro:id/iv_subtract");
        }
        try {
            assertTrue(itemCount - 1 == Integer.parseInt(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_count\")").getText()));
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test passed!\"}}");
            driver.quit();
        }
    }

    @Test
    public static void signUp(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 30);

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

    @Test
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

    @Test
    public static void eShopBasketAddItemToFavorites(AndroidDriver<AndroidElement> driver, Capabilities caps) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_online_checkout");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_move_to_favorites");

        try {
            assertTrue(wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator
                    ("new UiSelector().resourceId(\"com.carrefourpay.ro:id/recycler\")")))).isEnabled());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test passed!\"}}");

    }

    @Test
    public static void addItemToFavoritesFromEshop(AndroidDriver<AndroidElement> driver, Capabilities caps, boolean finalStep) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;


        UiObject.click(wait, driver, "com.carrefourpay.ro:id/action_eshop");
        //    UiObject.click(wait,driver, "com.carrefourpay.ro:id/btn_account");

        List<AndroidElement> elements = driver.findElements(By.className("android.widget.TextView"));
        for (WebElement element : elements) {
            if (element.getText().equals("TEX & Fashion")) {
                element.click();
                break;
            }
        }

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/product_info");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_name\")")))).isEnabled();
        String tv_product_name = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_name\")").getText();
        System.out.println("productName is " + tv_product_name);
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_favorite");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\")")))).click();
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\")")))).click();
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_account");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_wishlist");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_favorite_name\")")))).isEnabled();
        String tv_favorite_name = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_favorite_name\")").getText();
        boolean namesAreSame = tv_product_name.equals(tv_favorite_name);
        System.out.println("productName is " + tv_product_name);
        System.out.println("favoriteName is " + tv_favorite_name);
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/iv_favorite_remove\")")))).click();
        try {
            assertTrue(namesAreSame && wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator
                    ("new UiSelector().resourceId(\"com.carrefourpay.ro:id/refresh_layout\")")))).isEnabled());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test passed!\"}}");
        }
    }

    @Test
    public static void addNewAddress(AndroidDriver<AndroidElement> driver, Capabilities caps) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_account");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_addresses");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_add_address");

        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_address_name\")")))).sendKeys("Adresa De test");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_last_name\")")))).sendKeys("Nume De test");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_first_name\")")))).sendKeys("Prenume De test");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_first_name\")")))).sendKeys("Prenume De test");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_phone_number\")")))).sendKeys("0744555999");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_street\")")))).sendKeys("StradaDeTest");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_street_no\")")))).sendKeys("12");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_building\")")))).sendKeys("1");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_entrance\")")))).sendKeys("a");

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(117, 1900))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(117, 963))
                .release();
        action.perform();

        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_floor\")")))).sendKeys("8");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_apartment\")")))).sendKeys("15");
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_intercom\")")))).sendKeys("10");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/et_county");
        action.press(PointOption.point(117, 1102))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))
                .release();
        action.perform();
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/et_city");
        action.press(PointOption.point(117, 1230))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))
                .release();
        action.perform();

      /*  wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_county\")")))).click();

        List<AndroidElement> judete = driver.findElements(By.id("com.carrefourpay.ro:id/tab_text"));
        for (WebElement element : judete) {
            if (element.getText().equals("Arad")) {
                element.click();
                break;
            }}*/
  /*      wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_city\")")))).click();
        List<AndroidElement> orase = driver.findElements(By.id("com.carrefourpay.ro:id/tab_text"));
        for (WebElement element : orase) {
            if (element.getText().equals("Aciuta")) {
                element.click();
                break;
            }}*/
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_zip\")")))).sendKeys("004400");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_add_address");
    }

    @Test
    public static void enterAFGExperiente(AndroidDriver<AndroidElement> driver, Capabilities caps, boolean finalStep) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;


        UiObject.click(wait, driver, "com.carrefourpay.ro:id/action_loyalty");
        List<AndroidElement> elements = driver.findElements(By.id("com.carrefourpay.ro:id/tab_text"));
        for (WebElement element : elements) {
            if (element.getText().equals("Experiențe")) {
                element.click();
                break;
            }
        }
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_enroll");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_continue");
        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/container_details\")").isEnabled());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\", \"reason\": \"Test passed\"}}");
            driver.quit();
        }
    }

    @Test
    public static void emptyBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps, boolean finalStep) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_empty_cart");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_continue");
        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/recycler\")").isEnabled());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\", \"reason\": \"Test passed!\"}}");
        }
    }

    @Test
    public static void addItemFromFavoritesToBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_account");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_wishlist");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_add_to_cart");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_cart");

        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_product_added\")").isEnabled());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_online_checkout\")")))).click();
        emptyBasket(driver, caps, false);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\", \"reason\": \"Test passed!\"}}");

    }

    @Test
    public static void removeItemFromFavoritesToBasket(AndroidDriver<AndroidElement> driver, DesiredCapabilities caps, boolean finalStep) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/iv_favorite_remove");

        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/refresh_layout\")").isEnabled());
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_online_checkout\")")))).click();
        emptyBasket(driver, caps, false);
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\", \"reason\": \"Test passed!\"}}");
        }
    }

    @Test
    public static void addEshopProduct(AndroidDriver<AndroidElement> driver, Capabilities caps, boolean finalStep) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        UiObject.click(wait, driver, "com.carrefourpay.ro:id/action_eshop");

        List<AndroidElement> elements = driver.findElements(By.className("android.widget.TextView"));
        for (WebElement element : elements) {
            if (element.getText().equals("TEX & Fashion")) {
                element.click();
                break;
            }
        }
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/product_info");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_order");
        List<AndroidElement> productSizes = driver.findElements(By.className("android.widget.TextView"));
        for (WebElement productSize : productSizes) {
            if (productSize.getText().equals("37/38")) {
                productSize.click();
                break;
            }
        }
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_order");
        UiObject.click(wait, driver, "com.carrefourpay.ro:id/btn_cart");

        try {
            assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/tv_whole_price\")").getText() != null);
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
        if (finalStep) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\", \"reason\": \"Test passed!\"}}");
            driver.quit();
        }

    }
}