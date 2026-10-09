package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SharedMediaScreenPageObject;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

/**
 * iOS counterpart of {@link lib.ui.android.AndroidSharedMediaScreenPageObject}
 * — the full-screen viewer reached by tapping a photo or video in a chat.
 *
 * <p><b>Edit is the one control here with no accessibility identifier.</b> It
 * is an {@code XCUIElementTypeButton} with no {@code name} and no
 * {@code label} at all, which is why no search over names or labels will ever
 * turn it up. What makes it addressable anyway is that it is the <em>only</em>
 * anonymous button in this navigation bar — the other three (Back, the title
 * block, More) are all named — so {@code [not(@name)]} picks it out exactly.
 *
 * <p>That is a positional locator in all but spelling, and it will break the
 * day a second unnamed button joins the bar. The alternative was to key off
 * the pixel column or off being the sibling before More, both of which break
 * sooner. If this starts matching the wrong thing, the fix is an accessibility
 * identifier in the app, not a cleverer XPath.
 */
public class iOSSharedMediaScreenPageObject extends SharedMediaScreenPageObject {

    /** The row in the More sheet that leads to All Shared Media. */
    private static final String MORE_MENU_SHOW_ALL_MEDIA =
            "xpath://XCUIElementTypeStaticText[@name=\"Show all media\" and @visible=\"true\"]";

    /** The sliders icon left of More — see the class javadoc for why it is spelled this way. */
    private static final String VIEWER_ANONYMOUS_EDIT_BUTTON =
            "xpath://XCUIElementTypeNavigationBar[@name=\"SharedMedia.SharedMediaPageVC\"]"
                    + "/XCUIElementTypeButton[not(@name)]";

    static {
            SHARED_MEDIA_SCREEN_OVERVIEW = "id:big_image";
            SHARED_MEDIA_SCREEN_TOP_PANEL = "xpath://XCUIElementTypeNavigationBar[@name=\"SharedMedia.SharedMediaPageVC\"]";
            BACK_TO_CHAT_BUTTON = "id:BackButton";
            DELETE_MEDIA_BUTTON = "id:SharedMediaContentDeleteButton";
            THREE_DOT_BUTTON = "id:SharedMediaMoreButton";
            THREE_DOT_MENU = "xpath://XCUIElementTypeStaticText[@name=\"More\" and @visible=\"true\"]";
            // Behind the More sheet here, not in the header as on Android —
            // see openMediaOverflowMenu below.
            OPEN_ALL_MEDIA_BUTTON = MORE_MENU_SHOW_ALL_MEDIA;
            EDIT_PHOTO_BUTTON = VIEWER_ANONYMOUS_EDIT_BUTTON;

            // Unset on purpose:
            // NEXT_MEDIA_* (four fields) — iOS has no arrows; the viewer is a
            //                              paged scroll view, so moving between
            //                              items is a swipe. See the override.
            // VIDEO_PLAYING, GIF_PLAYING, VIDEO_PLAY_BUTTON,
            // THREE_DOT_MENU_SAVE_GIF, MEDIA_FROM_GROUP_OPEN_ITEM
            //                            — the video, gif and grouped-media
            //                              viewers have not been walked yet.
    }

    public iOSSharedMediaScreenPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /**
     * No arrows to read, so there is nothing to branch on: swipe and let the
     * pager decide. At the end of the roll the swipe simply does not move,
     * which is the same outcome Android gets from a disabled arrow.
     */
    @Override
    public void switchRightOrLeftBetweenMedia() {
        ((JavascriptExecutor) driver).executeScript("mobile: swipe",
                Map.of("direction", "left"));
    }
}
