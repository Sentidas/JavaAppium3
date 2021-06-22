package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


public class SearchTests extends CoreTestCase {

    @Ignore
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check text in element")
    @Description("We check text in element")
    @Step("Starting test testCheckTextInElement_ex2 (this method does nothing for Mobile Web)")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckTextInElement_ex2() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.checkTextInElement("Search Wikipedia");


    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value = "Smoke_testing")})
    @DisplayName("Cancel search")
    @Description("We cancel search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch_ex3() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        System.out.println(searchPageObject.getAmountOfFoundArticles());
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
       // searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value = "Smoke_testing")})
    @DisplayName("Cancel search")
    @Description("We cancel search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        System.out.println(searchPageObject.getAmountOfFoundArticles());
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }


    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value = "Smoke_testing")})
    @DisplayName("Check title in the articles in search page")
    @Step("Starting test testCheckTitleInArticlesInSearchPage")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckTitleInArticlesInSearchPage_ex4() {

        String valueSearch = "java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(valueSearch);
        System.out.println(searchPageObject.getAmountOfFoundArticles());

        int errors = searchPageObject.waitForElementsAndCheckNameinArticles(valueSearch);
        System.out.println("количество ошибок: " + errors);
        Assert.assertTrue("Not all titles of articles contain search_value",
                errors == 0);

    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value = "Smoke_testing")})
    @DisplayName("Check search")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
