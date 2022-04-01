import java.net.URL;
import java.net.MalformedURLException;

import core.InitialSetup;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.Flows;

//demo class for Browserstack
public class Runner {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
    String sessionName = "Sign-up + obtainAFG + deleteAccount";
    String sessionName2 = "Log-in + Eshop Basket + logOut";


       DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName);
       AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
       InitialSetup.allowPolicies(driver);
       Flows.signUp(driver,caps);
       Flows.obtainAFGCode(driver,caps);
       Flows.deleteAccount(driver,caps);
       driver.quit();



       DesiredCapabilities caps1 = InitialSetup.initiateCapabilities(sessionName2);
       AndroidDriver<AndroidElement> driver1 = InitialSetup.initiateDriver(caps1);
       InitialSetup.allowPolicies(driver1);
       Flows.login(driver1,caps1);
       Flows.addItemToEShopBasket(driver1,caps1);
       Flows.logOut(driver1,caps1);
       driver1.quit();

    }
}

