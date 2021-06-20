package lib.ui.mobile_web;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://span[text() ='Watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        CLICK_LIST = "//a[text()='List']";

    }
    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
