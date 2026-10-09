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

    public void assertTopPanelDisplayed() {
        this.waitForElementPresent(
                SHARED_MEDIA_SCREEN_TOP_PANEL,
                "To panel not found on the screen",
                15
        );
    }

    public void assertEditButtonDisplayed() {
        this.revealEditButton();
        this.waitForElementPresent(
                EDIT_PHOTO_BUTTON,
                "Can't find Edit button",
                15
        );
        Assert.assertTrue(isElementPresent(EDIT_PHOTO_BUTTON));
        this.hideEditButton();
        System.out.println("Edit button is displayed as expected");
    }

    public void assertEditButtonNotDisplayed() {
        this.revealEditButton();
        this.waitForElementNotPresent(
                EDIT_PHOTO_BUTTON,
                "Edit button is displayed",
                15
        );
        Assert.assertFalse(isElementPresent(EDIT_PHOTO_BUTTON));
        this.hideEditButton();
        System.out.println("Edit button is not displayed as expected");

    }

    public void tapOnEditPhotoButton() {
        this.assertTopPanelDisplayed();
        this.revealEditButton();
        if (isElementPresent(EDIT_PHOTO_BUTTON)) {
            this.waitForElementAndClick(
                    EDIT_PHOTO_BUTTON,
                    "Can't tap on Edit button",
                    15
            );
        } else {
            // Nothing was tapped, so whatever reveal opened is still open and
            // would sit on top of the next step.
            this.hideEditButton();
            System.out.println("Edit button not available for opened media");
        }
    }

    /**
     * iOS puts Edit straight in the viewer's toolbar, so there is nothing to
     * reveal. Android keeps it as a row of the three-dot menu, which means the
     * button is not merely hidden but absent from the tree until the menu is
     * open — and "absent from the tree" is exactly what
     * {@link #assertEditButtonNotDisplayed()} tests for. Without this the
     * negative assertions would pass on Android no matter what the app did.
     *
     * <p>Paired with {@link #hideEditButton()} so that asking the question
     * leaves the screen as it found it.
     */
    protected void revealEditButton() {
    }

    /** @see #revealEditButton() */
    protected void hideEditButton() {
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

    /**
     * Android draws prev/next arrows over the photo and disables the one that
     * has nowhere to go, so which arrow is live says which way we can move.
     * iOS has no arrows — the viewer is a paged scroll view — so the iOS
     * override swipes instead of tapping. The decision of which way to go
     * cannot be shared because the two platforms don't expose the same thing
     * to decide on.
     */
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
        this.openMediaOverflowMenu();
        this.waitForElementAndClick(
                OPEN_ALL_MEDIA_BUTTON,
                "Can't open All Shared Media screen",
                20
        );
    }

    /**
     * Both platforms keep "all media" behind the viewer's three-dot control —
     * a popup list on Android, an action sheet on iOS — so the step is shared
     * and only the two locators differ. Android used to reach it through a
     * header link instead; that link is gone from the build, which is why this
     * is no longer platform-specific.
     */
    protected void openMediaOverflowMenu() {
        this.waitForElementAndClick(
                THREE_DOT_BUTTON,
                "Can't open the More menu",
                20
        );
        this.waitForElementPresent(
                THREE_DOT_MENU,
                "More menu is not displayed",
                15
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
