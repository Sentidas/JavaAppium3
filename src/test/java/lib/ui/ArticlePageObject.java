package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
                    NAME_OF_MY_LIST_TPL,
            CLOSE_ARTICLE_BUTTON,
            OPTION_REMOVE_FROM_MY_LIST_BUTTON;

    public static String getSavedListXpathByNameTpl(String nameOfFolder) {
        return NAME_OF_MY_LIST_TPL.replace("{NAME_LIST}", nameOfFolder);
    }

    public ArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForELementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public WebElement notWaitForTitleElementOnlyClick(){
        return this.waitForELementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        }else if(Platform.getInstance().isIOS()) {
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }

    }
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if(Platform.getInstance().isIOS()) {
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }else {
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
        }
            this.waitForELementPresent(OPTION_ADD_TO_MY_LIST_BUTTON,
            "Cannot find button to add an article to saved list after removind it from this list before");

    }

    public void addArticleToNewList(String nameOfFolder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
        }


     public void closeButtonSyncMySavedArticlesMyLists(){

         this.waitForElementAndClick(
                 BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES,
                 "Cannot find button to close for sync my saved articles" ,
                 5);
     }
    public void addArticleToPresentList(String nameOfFolder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        String listNameXpath = getSavedListXpathByNameTpl(nameOfFolder);
        this.waitForElementAndClick(
                listNameXpath,
                "Cannot find created folder",
                15
        );
    }

    public void closeArticle(){
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find x link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void assertElementPresent() {
        this.assertElementPresent(
                TITLE,
                "Cannot find title in article"
        );
    }
}
