package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic(value = "Core tests")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Feature(value = "Orientation screen")
    @DisplayName("Change screen orientation on search screen for iOS and Android")
    @Step("Starting test testChangeScreenOrientationOnSearchScreen")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchScreen() {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Title of article have been changed after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Title of article have been changed after second rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }

    @Test
    @Feature(value = "Background App")
    @DisplayName("Check search article after background app for iOS and Android")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
