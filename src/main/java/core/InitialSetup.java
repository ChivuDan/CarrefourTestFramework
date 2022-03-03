package core;

import io.appium.java_client.android.AndroidDriver;

public class InitialSetup {
    public static void allowPolicies(AndroidDriver driver){
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")").click();
        if(driver.findElementByAndroidUIAutomator(new UiSelector().isClickable("com.carrefourpay.ro:id/btn_close")) {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();
        }

        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_activate_all\")").click();
        } catch (Exception e){
            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();
        }
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.carrefourpay.ro:id/btn_close\n\")").click();
    }
}
