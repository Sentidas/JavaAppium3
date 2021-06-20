package lib.ui.mobile_web;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static{
                TITLE = "css:#content h1";
                FOOTER_ELEMENT = "css:footer" ;
              //  OPTIONS_BUTTON;
               // BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES;
                OPTION_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch[title='Unwatch']";

        // ADD_TO_MY_LIST_OVERLAY;
               // MY_LIST_NAME_INPUT;
              //  MY_LIST_OK_BUTTON;
              //  NAME_OF_MY_LIST_TPL;

    }

    public MWArticlePageObject(RemoteWebDriver driver){ super(driver);
    }
}
