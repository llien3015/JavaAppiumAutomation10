package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        MORE_OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
        GOT_IT_ONBORDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
