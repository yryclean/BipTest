package lib.ui;

import io.appium.java_client.AppiumDriver;

public class ChatInfoScreenPageObject extends MainPageObject {
    protected static String
    SEE_ALL_MEDIA_BUTTON,
    STARRED_MESSAGES;

    public ChatInfoScreenPageObject(AppiumDriver driver) {super(driver);}

    public void openAllSharedMedia() {
        this.waitForElementAndClick(
                SEE_ALL_MEDIA_BUTTON,
                "Can't open All Shared Media screen",
                20
        );
    }
    public void openStarredMessages() {
        this.waitForElementAndClick(
                STARRED_MESSAGES,
                "Can't open Starred messages",
                20
        );
    }
}
