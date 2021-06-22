package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.Assert;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Article"), @Feature(value = "device")})
    @DisplayName("Change the screen orientation")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults() {
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
