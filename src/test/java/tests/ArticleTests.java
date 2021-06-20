package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {


    @Test
    public void testTitlePresentWithoutWait_ex6() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertElementPresent();
    }


    @Test
    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

     ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
}
