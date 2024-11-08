package lib.ui;

import io.appium.java_client.AppiumDriver;

public class StarredMessagesScreenPageObject extends MainPageObject {

    protected static String
            STARRED_MESSAGES_SCREEN_NAME,
            STARRED_SCREEN_BACK_BUTTON,
            ANY_STARRED_PHOTO;

    public StarredMessagesScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void tapOpenStarredPhotoInFullScreen() {
        this.waitForElementAndClick(
                ANY_STARRED_PHOTO,
                "Can't tap and open starred photo",
                20
        );
    }

}
