package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject{
    protected static String
            LIST_BY_NAME_XPATH_TPL,
            ARTICLE_BY_NAME_XPATH_TPL,
            CLOSE_POPUP_BUTTON,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getListByNameXpath(String titleOfList) {
        return LIST_BY_NAME_XPATH_TPL.replace("{LIST_NAME}", titleOfList);
    }
    private static String getArticleByNameXpath(String article_title) {
        return ARTICLE_BY_NAME_XPATH_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{ARTICLE_TITLE}", article_title);
    }
    //TEMPLATES METHODS

    @Step("Open favorite list with '{titleOfList}' title")
    public void openListByName(String titleOfList) {
        String title_of_list = getListByNameXpath(titleOfList);
        this.waitForElementAndClick(
                title_of_list,
                "Cannot find ny list with name" + titleOfList,
                5
        );
    }

    @Step("Waiting for article with title '{article_title}'")
    public void waitForArticleToAppearByTitle(String article_title) {
        String title_of_article = getArticleByNameXpath(article_title);
        this.waitForElementPresent(title_of_article,"Cannot find article with title " + article_title, 5);
    }

    @Step("Waiting for when article with title '{article_title}' to disappear")
    public void waitForArticleToDisappearByTitle(String article_title) {
        String title_of_article = getArticleByNameXpath(article_title);
        this.waitForElementNotPresent(title_of_article,"Saved article with title " + article_title + "is presented still", 5);
    }
    @Step("Swipping article '{article_title}' for delete from list")
    public void swipeArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String title_of_article = getArticleByNameXpath(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    title_of_article,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(title_of_article, "Cannot find saved article");
        }
        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Closing popup after adding article in favorite list")
    public void closePopup() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_POPUP_BUTTON, "Cannot find close button on popup", 10);
        } else {
            System.out.println("Method closePopup() do nothing on platform " + Platform.getInstance().getPlatformVar());
        }
    }
}