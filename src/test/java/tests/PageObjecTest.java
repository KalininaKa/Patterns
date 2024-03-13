package tests;

import org.junit.jupiter.api.Test;

import static constants.Constant.Urls.REALT_HOME_PAGE;

public class PageObjecTest extends BaseTest {
    @Test
    public void checkIsRedirectToListing() {
        basePage.open(REALT_HOME_PAGE);
        realtHomePage
                .enterCountRooms()
                .clickToFind();
        realtListingPage.checkCountCards();
    }
}