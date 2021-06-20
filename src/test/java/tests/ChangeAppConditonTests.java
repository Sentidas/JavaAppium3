package tests;

import javafx.print.PageLayout;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditonTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientaionOnSearchResults() {
        if(Platform.getInstance().isMw()){
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        String titleBeforeRotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String titleAfterRotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have bee changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation
        );

        this.rotateScreenPortrait();

        String titleAfterSecondRotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have bee changed after screen rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground() {
     if(Platform.getInstance().isMw()){
        return;
    }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.waitForSearchResult("Capital and largest city of Cuba");
       // this.backgroundApp(2);

        searchPageObject.waitForSearchResult("Capital and largest city of Cuba");
    }

}
