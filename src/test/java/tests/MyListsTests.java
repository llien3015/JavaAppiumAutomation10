package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic(value = "Tests for My favorite article list")
public class MyListsTests extends CoreTestCase {

    private static final String
            name_of_folder = "Learning programming",
            login = "VeraQa",
            password = "vero2502";

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article"),@Feature(value = "My favorite list")})
    @DisplayName("Saving first article in My favorite list")
    @Description("Saving article in my favorite list and checking deleting it from list")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if(Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            String url = driver.getCurrentUrl();
            String new_url = url.substring(0,11) + "m." + url.substring(11);
            driver.get(new_url);

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            MyListPageObject.closePopup();
        }
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openListByName(name_of_folder);
        }
        MyListPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article"),@Feature(value = "My favorite list")})
    @DisplayName("Saving some articles in my favorite list")
    @Description("Saving some article in my favorite list and checking deleting one of then from my favorite list")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSavingSecondArticleInFavorite() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title_first = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if(Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title_first,
                    ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("igh-level programming language");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
        } else {
            ArticlePageObject.waitForTitleElementSecond();
        }

        String article_title_second = ArticlePageObject.getArticleSecond();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList();
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openListByName(name_of_folder);
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openListByName(name_of_folder);
        } else {
            MyListPageObject.closePopup();
        }
        MyListPageObject.swipeArticleToDelete(article_title_first);
        MyListPageObject.waitForArticleToAppearByTitle(article_title_second);
    }
}
