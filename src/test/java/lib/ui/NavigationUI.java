package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
            MY_LIST,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Opening navigation menu")
    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button.", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Opening My favorite list in navigation menu")
    public void clickMyList() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST,
                    "Cannot find My list in navigation menu",
                    5
            );
        }
        this.waitForElementAndClick(
                MY_LIST,
                "Cannot find My list in navigation menu",
                5
        );
    }
}
