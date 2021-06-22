package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
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

    @Step("Get saved list")
    public static String getSavedListXpathByNameTpl(String nameOfFolder) {
        return NAME_OF_MY_LIST_TPL.replace("{NAME_LIST}", nameOfFolder);
    }

    public ArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement(){
        return this.waitForELementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public WebElement notWaitForTitleElementOnlyClick(){
        return this.waitForELementPresent(TITLE, "Cannot find article title on page!", 15);
    }
    @Step("Get article title")
    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        }else if(Platform.getInstance().isIOS()) {
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }

    }
    @Step("Swiping to footer on article page")
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
    @Step("Removing the article from saved if it has been added")
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
    @Step("Adding the article to my list")
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
    @Step("Adding the article to my saved articles")
    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
        }

     @Step("Closing the button_sync for saved articles in my list")
     public void closeButtonSyncMySavedArticlesMyLists(){

         this.waitForElementAndClick(
                 BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES,
                 "Cannot find button to close for sync my saved articles" ,
                 5);
     }
    @Step("Adding the article in present list")
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

    @Step("Closing the article")
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
    @Step("Assert the present element")
    public void assertElementPresent() {
        this.assertElementPresent(
                TITLE,
                "Cannot find title in article"
        );
    }
}
