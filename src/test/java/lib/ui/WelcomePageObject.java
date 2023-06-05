package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
        STEP_LEARN_MORE_ABOUT_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";


    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for 'More about' link on welcome screen")
    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_LINK,"Cannot find element More about link",10);
    }
    @Step("Waiting for 'New ways to explore' text on welcome screen")
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,"Cannot find 'New ways to explore' text",10);
    }
    @Step("Waiting for 'Add or edit languages' link on welcome screen")
    public void waitForAddOrEditLanguages() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK,"Cannot find element Add or edit languages link",10);
    }

    @Step("Waiting for 'Learn more about data collected' link on welcome screen")
    public void waitForLearnMoreAboutDataCollectedLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Cannot find link 'Learn more about data collected'",10);
    }

    @Step("Waiting for and clicking 'Get started' button on screen")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find Get started button", 10);
    }

    @Step("Waiting for and clicking 'Next' button on screen")
    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK,"Cannot find and click on element Next",10);
    }

    @Step("Waiting for and clicking 'Skip' button on screen")
    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find Skip button", 10);
    }
}
