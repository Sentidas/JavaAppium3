package tests;


import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String nameOfFolder = "My stories";
    private static final String
            login = "sky474",
            password = "123456Sa";

    @Test
    @Features(value = {@Feature(value="MyList"),@Feature(value="Article"), @Feature(value = "Smoke_testing")})
    @DisplayName("Save two articles in my list and then delete one")
    @Description("We save wwo articles in my list and then delete one")
    @Step("Starting test testSaveTwoArticleInMyListAndDeleteOne")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticleInMyListAndDeleteOne_ex5_ex11_ex17() {
        String articleTitle = null;
        String articleTitle2 = null;

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("city of Portugal");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (!Platform.getInstance().isIOS()) {
            articlePageObject.waitForTitleElement();
            articleTitle = articlePageObject.getArticleTitle();
        }
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList(nameOfFolder);
        }
        articlePageObject.addArticlesToMySaved();

        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeButtonSyncMySavedArticlesMyLists();
        }
        if (Platform.getInstance().isMw()) {
            AutorizationPageObject autorizationPageObject = new AutorizationPageObject(driver);
            autorizationPageObject.clickAuthButton();
            autorizationPageObject.enterLoginData(login, password);
            autorizationPageObject.submitForm();

            articlePageObject.waitForTitleElement();
            System.out.println("заголовок статьи 1 " + articleTitle);

            Assert.assertEquals("We are not on the same page after login",
                    articleTitle,
                    articlePageObject.getArticleTitle()
            );
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clickCancelSearch();
        }
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        articleTitle2 = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToPresentList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();

            articlePageObject.waitForTitleElement();
            System.out.println("заголовок статьи 2" + articleTitle2);
            Assert.assertEquals("We are not on the same page after login",
                    articleTitle2,
                    articlePageObject.getArticleTitle()
            );
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(nameOfFolder);
            myListPageObject.swipeToArticleToDelete(articleTitle2);
            myListPageObject.waitForArticleToAppearByTitleAndClick(articleTitle);
            myListPageObject.waitForArticleToAppearByTitle(articleTitle);
        } else {
            myListPageObject.swipeToArticleToDelete(articleTitle2);
        }
        if (Platform.getInstance().isMw()) {
            myListPageObject.waitForArticleToAppearByTitle("Java (programming language)");
        } else {
            searchPageObject.waitForArticleWithName("Java (programming language)");
        }
    }

    @Ignore
    @Test
    @Features(value = {@Feature(value="MyList"),@Feature(value="Article"), @Feature(value = "Smoke_testing")})
    @DisplayName("Save two articles in my list and then delete one for Android")
    @Description("We save wwo articles in my list and then delete one")
    @Step("Starting test testSaveTwoArticleInMyListAndDeleteOne (this method is only for Android)")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticleInMyListAndDeleteOne_ex5_onlyForAndroid() {


        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToNewList(nameOfFolder);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToPresentList(nameOfFolder);

        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        myListPageObject.openFolderByName(nameOfFolder);
        myListPageObject.swipeToArticleToDelete(articleTitle2);
        myListPageObject.waitForArticleToAppearByTitleAndClick(articleTitle);
        myListPageObject.waitForArticleToAppearByTitle(articleTitle);

}
    @Ignore
    @Test
    @Features(value = {@Feature(value="MyList"),@Feature(value="Article"), @Feature(value = "Smoke_testing")})
    @DisplayName("Save two articles in my list and then delete one for iOS")
    @Description("We save wwo articles in my list and then delete one")
    @Step("Starting test testSaveTwoArticleInMyListAndDeleteOne (this method is only for iOS)")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticleInMyListAndDeleteOne_ex11_onlyForIos() {
        String articleTitle = null;
        String articleTitle2 = null;

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        articlePageObject.addArticlesToMySaved();
        articlePageObject.closeButtonSyncMySavedArticlesMyLists();
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.clickCancelSearch();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        articleTitle2 = articlePageObject.getArticleTitle();

        articlePageObject.addArticlesToMySaved();

        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        myListPageObject.swipeToArticleToDelete(articleTitle2);
        searchPageObject.waitForArticleWithName("Lisbon");
    }
}

