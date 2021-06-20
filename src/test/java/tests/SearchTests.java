package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class SearchTests extends CoreTestCase {

    @Test
    public void testCheckTextInElement_ex2() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
       searchPageObject.checkTextInElement("Search Wikipedia");


    }

    @Test
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
    public void testCheckTitleInArticlesInSearchPage_ex4() {

        String valueSearch = "java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(valueSearch);
        System.out.println(searchPageObject.getAmountOfFoundArticles());

        int errors = searchPageObject.waitForElementsAndCheckNameinArticles(valueSearch);
        System.out.println("количество ошибок: " + errors);
        assertTrue("Not all titles of articles contain search_value",
                errors == 0);

    }

    @Test
    public void testSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
