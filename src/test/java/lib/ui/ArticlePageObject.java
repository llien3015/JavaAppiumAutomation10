package lib.ui;


import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract  public class ArticlePageObject extends MainPageObject{
    protected static  String
            TITLE_ID,
            TITLE_ID_SECOND,
            FOOTER_ELEMENT,
            MORE_OPTIONS_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            OPTION_REMOVE_FROM_MY_LIST_BUTTON,
            GOT_IT_ONBORDING_BUTTON,
            MY_LIST_NAME,
            OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;


    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE_ID,"Cannot find article title",5);
    }
    @Step("Waiting for second title on the screen")
    public WebElement waitForTitleElementSecond() {
        return this.waitForElementPresent(TITLE_ID_SECOND,"Cannot find article title",5);
    }

    @Step("Getting of article title")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Getting of second article title on screen")
    public String getArticleSecond() {
        WebElement title_element = waitForTitleElementSecond();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    @Step("Swiping article page to footer")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find end of screen", 40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find end of screen", 40);
        } else {
            this.scrollWebPageTillElementNotVisible (FOOTER_ELEMENT, "Cannot find end of screen", 40);
        }
    }

    @Step("Adding article to my favorite list")
    public void addArticleToMyList(String titleOfList) {
        this.waitForElementAndClick(
                MORE_OPTIONS_BUTTON,
                "Cannot find More options button on screen",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to reading list in menu",
                5
        );
        this.waitForElementAndClick(
                GOT_IT_ONBORDING_BUTTON,
                "Cannot find button Got it on onbording",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME,
                "Cannot find input to set name of article",
                5
        );
        this.waitForElementAndSendKey(
                MY_LIST_NAME,
                titleOfList,
                "Cannot put text in input Name of this list",
                5
        );
        this.waitForElementAndClick(
                OK_BUTTON,
                "Cannot find button OK",
                5
        );
    }

    @Step("Adding article to my favorite list if article already added")
    public void addArticleToMySaved() {
        if(Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find My saved button", 10);
    }

    @Step("Removing article from my favorite list")
    public void removeArticleFromSavedIfItAdded() {
        if(this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTION_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved after removing it from this list before"
            );
        }
    }

    @Step("Closing article page")
    public void closeArticle() {
        if ((Platform.getInstance().isIOS()) || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot find X button",
                    5
            );
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Adding second article to my favorite list")
    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(
                MORE_OPTIONS_BUTTON,
                "Cannot find More options button on screen",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to reading list in menu",
                5
        );
    }

    @Step("Checking title of article")
    public void assertCheckTitleOfArticle() {
        assertElementPresent(
                TITLE_ID,
                "Article does not have title"
        );
    }
}
