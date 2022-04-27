import java.net.MalformedURLException;

import core.constants.Credentials;
import tests.Flows;

//demo class for Browserstack
public class Runner {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        String sessionName = "Sign-up + obtainAFG + deleteAccount";
        String sessionName2 = "Log-in + Eshop Basket + logOut";

/*        Flows.selfScanCheckout(Credentials.Environment.PROD, true);
        Flows.eShopCheckout(Credentials.Environment.PROD);
        Flows.multipleChancesEvents(Credentials.Environment.STAGE);
        Flows.inputNewAddress(Credentials.Environment.PROD,true);
        Flows.incrementNumberOfItems(Credentials.Environment.PROD);
        Flows.decrementNumberOfItems(Credentials.Environment.PROD);
        Flows.removeItemFromBasket(Credentials.Environment.PROD, true);
        Flows.donateAFGPointsToSingleEvent();*/
        //Flows.favoriteProductsAddItemToFavoritesFromEShop(Credentials.Environment.PROD);
        Flows.favoriteProductsAddItemToFavoritesFromEShopBasket(Credentials.Environment.PROD);
        Flows.favoriteProductsAddItemToBasketFromFavoritesList(Credentials.Environment.PROD);
        Flows.favoriteProductsRemoveItemFromFavoritesList(Credentials.Environment.PROD);
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

