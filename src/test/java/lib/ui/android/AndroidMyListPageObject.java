package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {
    static {
        LIST_BY_NAME_XPATH_TPL = "xpath://*[@text='{LIST_NAME}']";
        ARTICLE_BY_NAME_XPATH_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
    }
    public AndroidMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
