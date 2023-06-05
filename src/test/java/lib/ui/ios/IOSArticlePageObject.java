package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "id:Java (programming language)";
        TITLE_ID_SECOND = "id:JavaScript";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTION_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Search";
    }
    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
