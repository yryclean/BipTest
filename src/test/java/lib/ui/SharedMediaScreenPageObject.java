package lib.ui;

import io.appium.java_client.AppiumDriver;

public abstract class SharedMediaScreenPageObject extends MainPageObject {
    protected static String
            SHARED_MEDIA_SCREEN_OVERVIEW,
            EDIT_PHOTO_BUTTON,
            NEXT_MEDIA_LEFT_BUTTON,
            NEXT_MEDIA_RIGHT_BUTTON,
            DELETE_MEDIA_BUTTON,
            OPEN_ALL_MEDIA_BUTTON,
            BACK_TO_CHAT_BUTTON,
            VIDEO_PLAY_BUTTON;

    public SharedMediaScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void isEditButtonDisplayed() {
        this.waitForElementPresent(
                EDIT_PHOTO_BUTTON,
                "Can't find Edit button",
                15
        );
    }
    public void tapOnEditPhotoButton() {
        this.waitForElementAndClick(
                EDIT_PHOTO_BUTTON,
                "Can't tap on Edit button",
                15
        );
    }


}
