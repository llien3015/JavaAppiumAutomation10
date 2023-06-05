package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic(value = "Test for Welcome screen")
public class GetStartedTests extends CoreTestCase {
    @Test
    @Feature(value = "Welcome page for iOS")
    @DisplayName("Check welcome page for iOS")
    @Description("Checking and closing welcome pages")
    @Step("Starting test testPassTroughWelcome")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassTroughWelcome() {
        if((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }

        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditLanguages();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
