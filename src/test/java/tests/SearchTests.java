package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic(value = "Tests for Search")
public class SearchTests extends CoreTestCase {

    @Test
    @Feature(value = "Search")
    @DisplayName("Search article by input 'Java'")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Cancel searching")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Checking amount result of searching 'Linkin Park Diskography'")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found to few results!",
                amountOfSearchResults > 0
        );
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check empty result of search 'ddddddrrff'")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String searchLine = "ddddddrrff";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Checking and cancelling searching of 'Java'")
    @Step("Starting test testCancelResultOfSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelResultOfSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String titleOfSearch = "Java";
        SearchPageObject.typeSearchLine(titleOfSearch);
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
        SearchPageObject.assertCheckResultOfTest(titleOfSearch);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check three items in result of search 'Java'")
    @Step("Starting test testSearchResultByTitleAndSubstring")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchResultByTitleAndSubstring() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String titleOfSearch = "Java";
        SearchPageObject.typeSearchLine(titleOfSearch);
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia, Southeast Asia");
            SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
            SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        } else {
            SearchPageObject.waitForSearchResult("sland in Indonesia");
            SearchPageObject.waitForSearchResult("bject-oriented programming language");
            SearchPageObject.waitForSearchResult("igh-level programming language");
        }
    }
}
