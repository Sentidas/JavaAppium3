package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static{
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        //SEARCH_TITLE_IN_ARTICLE = "xpath://XCUIElementTypeStaticText";
        SEARCH_CANCEL_BUTTON = "css:button.clear";
       // SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TRL = "xpath://div[contains(@class, 'wikidata-description')]" +
                "[contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results']";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
