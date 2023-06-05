package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "css:#content h1";
        TITLE_ID_SECOND = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTION_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch.watched";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
