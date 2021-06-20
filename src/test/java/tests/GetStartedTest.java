package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testThroughWelcome(){

        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) {
            return;
        }

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddAndEditLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollected();
        welcomePageObject.clickGetStartedButton();

    }




}
