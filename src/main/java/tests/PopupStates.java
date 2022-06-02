package tests;

import core.UiObject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupStates {
    public static void locationCheck(AndroidDriver<AndroidElement> driver, Capabilities caps, WebDriverWait wait){
        if(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/et_user_id\")").isEnabled()){
            UiObject.click(wait,driver,"com.carrefourpay.ro:id/btn_retry");
            UiObject.click(wait,driver,"com.carrefourpay.ro:id/btn_continue");
        }
    }
}
