import java.net.MalformedURLException;

import core.InitialSetup;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.Features;
import tests.Flows;

//demo class for Browserstack
public class Runner {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
    String sessionName = "Sign-up + obtainAFG + deleteAccount";
    String sessionName2 = "Log-in + Eshop Basket + logOut";

    //  Flows.selfScanCheckout();
    Flows.EShopCheckout();
       /*DesiredCapabilities caps = InitialSetup.initiateCapabilities(sessionName);
       AndroidDriver<AndroidElement> driver = InitialSetup.initiateDriver(caps);
       InitialSetup.allowPolicies(driver);
       Features.signUp(driver,caps);
       Features.obtainAFGCode(driver,caps);
       Features.deleteAccount(driver,caps);
       driver.quit();*/



    /*   String userID = "0722812150";
       String password = "123987";
       DesiredCapabilities caps1 = InitialSetup.initiateCapabilities(sessionName2);
       AndroidDriver<AndroidElement> driver1 = InitialSetup.initiateDriver(caps1);
       InitialSetup.allowPolicies(driver1);
       Features.login(driver1,caps1, userID, password);
       Features.addItemToEShopBasket(driver1,caps1);
       Features.logOut(driver1,caps1);
       driver1.quit();*/

    }
}

