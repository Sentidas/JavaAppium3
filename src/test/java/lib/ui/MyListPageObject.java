package lib.ui;


import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            FIRST_ARTICLE_TO_DELETE,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static String getFolderXpathByNameTpl(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    public static String  getSavedArticleXpathByTitleTpl(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public static String  getRevomeButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    @Step("Opening folder by name")
    public void openFolderByName(String nameOfFolder) {

        String nameOfFolderXpath = getFolderXpathByNameTpl(nameOfFolder);

        this.waitForElementAndClick(
                nameOfFolderXpath,
                "Cannot find folder by name " + nameOfFolder,
                5
        );
    }
    @Step("Waiting to appear article by title")
    public void waitForArticleToAppearByTitle(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        this.waitForELementPresent(articleTitleXpath, "Cannot find saved article by title " + articleTitle, 15);
    }
    @Step("Waiting to disappear article by title")
    public void waitForArticleToDisappearByTitle(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        this.waitForELementNotPresent(articleTitleXpath, "Saved article still present with title " + articleTitle, 15);
    }

    @Step("Swiping to the article for delete")
    public void swipeToArticleToDelete(String articleTitle) {

        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitleTpl(articleTitle);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    articleXpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRevomeButtonByTitle(articleTitle);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved.",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find and click to the right upper cornet");
        }

        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }
        if (!Platform.getInstance().isMw()) {
            this.waitForArticleToDisappearByTitle(articleTitle);
        }
    }
    @Step("Waiting the article to appear and click by title")
    public void waitForArticleToAppearByTitleAndClick(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        // this.waitForELementPresent(By.xpath(articleTitleXpath), "Cannot find saved article by title" + articleTitle, 15);
        this.waitForElementAndClick(articleTitleXpath, "Cannot find saved article by title" + articleTitle, 15);
    }
}
