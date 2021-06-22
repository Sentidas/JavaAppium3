package lib.ui;


import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static  String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_TITLE_IN_ARTICLE,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TRL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    public static String getResultSearchElement (String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TRL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS  - методы шаблонов */


    @Step("Initialising the search field")
    public void initSearchInput () {

        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForELementPresent(SEARCH_INPUT, "Cannot find search input after clicking init element", 15);
    }

    public void checkTextInElement(String valueForCheck) {
        this.assertElementHasText(SEARCH_INIT_ELEMENT,  valueForCheck, "We don't see right text");
    }
    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear(){

        this.waitForELementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }
    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear(){

        this.waitForELementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }
    @Step("Clicking button to cancel search result")
    public void clickCancelSearch(){

        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }
    @Step("Typing '{searchLine}' to the search line")
    public void typeSearchLine (String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search input", 5);

    }
    @Step("Waiting for search result")
    public void waitForSearchResult (String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForELementPresent(searchResultXpath,  "Cannot find search result with substring");
    }
    public void waitForArticleWithName (String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForELementPresent(searchResultXpath,  "Cannot find article in save list with title: " + substring);
    }
    @Step("Waiting search result and select the article ty substring in article title")
    public void clickByArticleWithSubstring(String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath,  "Cannot find and click search result with substring", 15);

    }
    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles(){
        this.waitForELementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultLabel(){

        this.waitForELementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);

    }
    @Step("Making sure there are not results for the search")
    public void assertThereIsNoResultsOfSearch(){
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Not supposed to find any results");
    }

    public void assertElementPresent(){
        this.assertElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Not supposed to find any results");
    }

    @Step("Waiting for elements and checking title in articles")
    public int waitForElementsAndCheckNameinArticles(String search) {
        int numbers = this.waitForElementsAndCheckNameinArticles(
                SEARCH_TITLE_IN_ARTICLE,
                search,
                "Not find articles with search "
        );
                return numbers;
    }
}