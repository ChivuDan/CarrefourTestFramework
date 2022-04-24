package core;

import api.android.Android;
import com.sun.org.apache.xpath.internal.operations.And;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class UiObject {

    private String locator;

    UiObject(String locator){
        this.locator=locator;
        System.out.println(this.locator);

    }
    private boolean isXpath(){
        return !locator.contains("UiSelector");
    }

    public boolean exists(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public static void click(WebDriverWait wait, AndroidDriver driver, String locator){
        wait.until(ExpectedConditions.elementToBeClickable((driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\""+locator+"\")")))).click();

    }
}
