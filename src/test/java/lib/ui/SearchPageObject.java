package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static  String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_RESULT_IS_EMPTY_LABEL,
            SEARCH_RESULT_TITLE_ELEMENT;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndSustring(String title, String substring){
        return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }
    //TEMPLATES METHODS

    @Step("Initializing the search field")
    public void initSearchInput(){
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element",5);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find cancel search button",5);
    }

    @Step("Waiting for search button cancel to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is presented still", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click on search cancel button", 5);
    }

    @Step("Typing '{searchLine}' to the search line")
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKey(SEARCH_INPUT, searchLine, "Cannot find and type i nto search input",5);
    }

    @Step("Waiting for search result for '{substring}'")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    @Step("Waiting for search result and select an article by substring '{substring}' in article title")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click on search result with substring " + substring,15);
    }

    @Step("Getting amount of found result")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anithing by request",
                15
        );
        return this.getAmountOfElement(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty search result")
    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(
                SEARCH_RESULT_IS_EMPTY_LABEL,
                "Cannot find empty result by request ",
                15
        );
    }

    @Step("Waiting article with title '{title}' and subtitle '{substring}' in search result")
    public void waitForElementByTitleAndDescription(String title, String substring) {
        String search_result_by_xpath = getResultSearchElementByTitleAndSustring(title, substring);
        this.waitForElementPresent(
                search_result_by_xpath,
                "Cannot find article at search result with title " + title + " and substring " + substring,
                5
        );
    }

    @Step("Waiting for thr empty result label")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We've found some result by request"
        );
    }

    @Step("Making sure there are no results for the search by title '{titleOfSearch}'")
    public void assertCheckResultOfTest(String titleOfSearch) {
        this.assertElementHasText(
                SEARCH_RESULT_TITLE_ELEMENT,
                titleOfSearch,
                "Cannot find article with " + titleOfSearch
        );
    }
}
