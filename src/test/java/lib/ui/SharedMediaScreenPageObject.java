package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

public abstract class SharedMediaScreenPageObject extends MainPageObject {
    protected static String
            SHARED_MEDIA_SCREEN_OVERVIEW,
            SHARED_MEDIA_SCREEN_TOP_PANEL,
            EDIT_PHOTO_BUTTON,
            VIDEO_PLAYING,
            GIF_PLAYING,
            NEXT_MEDIA_LEFT_BUTTON,
            NEXT_MEDIA_LEFT_BUTTON_ENABLED,
            NEXT_MEDIA_RIGHT_BUTTON,
            NEXT_MEDIA_RIGHT_BUTTON_ENABLED,
            DELETE_MEDIA_BUTTON,
            OPEN_ALL_MEDIA_BUTTON,
            BACK_TO_CHAT_BUTTON,
            VIDEO_PLAY_BUTTON,
            THREE_DOT_BUTTON,
            THREE_DOT_MENU,
            THREE_DOT_MENU_SAVE_GIF,
            MEDIA_FROM_GROUP_OPEN_ITEM;

    public SharedMediaScreenPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void isTopPanelDisplayed() {
        this.waitForElementPresent(
                SHARED_MEDIA_SCREEN_TOP_PANEL,
                "To panel not found on the screen",
                15
        );
    }

    public void isEditButtonDisplayed() {
        this.waitForElementPresent(
                EDIT_PHOTO_BUTTON,
                "Can't find Edit button",
                15
        );
        Assert.assertTrue(isElementPresent(EDIT_PHOTO_BUTTON));
        System.out.println("Edit button is displayed as expected");
    }

    public void isEditButtonNotDisplayed() {
        this.waitForElementNotPresent(
                EDIT_PHOTO_BUTTON,
                "Edit button is displayed",
                15
        );
        Assert.assertFalse(isElementPresent(EDIT_PHOTO_BUTTON));
        System.out.println("Edit button is not displayed as expected");

    }

    public void tapOnEditPhotoButton() {
        this.isTopPanelDisplayed();
        if (isElementPresent(EDIT_PHOTO_BUTTON)) {
            this.waitForElementAndClick(
                    EDIT_PHOTO_BUTTON,
                    "Can't tap on Edit button",
                    15
            );
        } else {
            System.out.println("Edit button not available for opened media");
        }
    }

    public void tapOnVideo() {
        this.waitForElementAndClick(
                VIDEO_PLAYING,
                "Can't tap on playing video",
                15
        );
    }

    public void tapOnGif() {
        this.waitForElementAndClick(
                GIF_PLAYING,
                "Can't tap on playing gif",
                15
        );
    }

    public void switchRightOrLeftBetweenMedia() {
        if (isElementPresent(NEXT_MEDIA_RIGHT_BUTTON_ENABLED)) {
            this.waitForElementAndClick(
                    NEXT_MEDIA_RIGHT_BUTTON_ENABLED,
                    "Can't tap on Next media button",
                    15
            );
        } else if (isElementPresent(NEXT_MEDIA_LEFT_BUTTON_ENABLED)) {
            this.waitForElementAndClick(
                    NEXT_MEDIA_LEFT_BUTTON_ENABLED,
                    "Can't tap on Previous media button",
                    15
            );
        } else if(isElementPresent(NEXT_MEDIA_RIGHT_BUTTON_ENABLED) & isElementPresent(NEXT_MEDIA_LEFT_BUTTON_ENABLED)) {
            this.waitForElementAndClick(
                    NEXT_MEDIA_RIGHT_BUTTON_ENABLED,
                    "Can't tap on Next media button",
                    15
            );
        } else {
            System.out.println("There's only one media available on Shared Media screen");
        }
    }

    public void tapPreviousMediaButton() {
            this.waitForElementAndClick(
            NEXT_MEDIA_RIGHT_BUTTON_ENABLED,
                    "Can't tap on Previous media button",
                            15
             );
    }
    public void tapNextMediaButton() {
        this.waitForElementAndClick(
                NEXT_MEDIA_LEFT_BUTTON_ENABLED,
                "Can't tap on Next media button",
                15
        );
    }

    public void tapThreeDotButtonOnSharedMedia() {
        this.waitForElementAndClick(
                THREE_DOT_BUTTON,
                "Can't find and tap 3 dot button",
                25
        );
        this.waitForElementAndClick(
                THREE_DOT_MENU_SAVE_GIF,
                "Can't find Save Gif button in 3 dot menu",
                25
        );
    }
    public void closeSharedMediaOpenChat() {
        this.waitForElementAndClick(
                BACK_TO_CHAT_BUTTON,
                "Can't open chat",
                20
        );
    }
    public void openAllSharedMediaScreen() {
        this.waitForElementAndClick(
                OPEN_ALL_MEDIA_BUTTON,
                "Can't open All Shared Media screen",
                20
        );
    }
    public void openMediaFromGroup() {
        this.waitForElementAndClick(
        MEDIA_FROM_GROUP_OPEN_ITEM,
                "Can't open media from group",
                25
        );
    }
}
