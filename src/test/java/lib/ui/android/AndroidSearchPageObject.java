package lib.ui.android;


import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static{
    SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
    SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
    SEARCH_TITLE_IN_ARTICLE = "id:org.wikipedia:id/page_list_item_title";
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TRL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
