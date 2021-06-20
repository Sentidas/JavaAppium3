package lib.ui.mobile_web;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'Lisbon')]/../../a[contains(@class, 'watched')]";


    }
    public MWMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
