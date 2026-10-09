package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.AllSharedMediaScreePageObject;

/**
 * iOS counterpart of {@link lib.ui.android.AndroidAllSharedMediaScreePageObject}.
 *
 * <p>The grid names each cell after what it holds and numbers it —
 * {@code send_image_3}, {@code send_video_1} — so the two item locators match
 * by prefix. Android gets the same distinction from a duration label being
 * present or not.
 */
public class iOSAllSharedMediaScreePageObject extends AllSharedMediaScreePageObject {
    static {
            // The tab strip is the one thing on this screen that is always
            // there, whichever tab is open; Media carries value="1" when it is
            // the selected one.
            ALL_SHARED_MEDIA_SCREEN_OVERVIEW = "xpath://XCUIElementTypeButton[@name=\"Media\"]";
            BACK_TO_CHAT_BUTTON = "id:BackButton";
            MEDIA_TAB = "xpath://XCUIElementTypeButton[@name=\"Media\"]";
            // "Docs", not Android's "DOCUMENTS".
            DOCUMENTS_TAB = "xpath://XCUIElementTypeButton[@name=\"Docs\"]";
            LINKS_TAB = "xpath://XCUIElementTypeButton[@name=\"Links\"]";
            NAME_OF_CHAT_TPL = "xpath://XCUIElementTypeNavigationBar[@name=\"{CHAT_NAME}\"]";
            SHARED_PHOTO = "xpath://XCUIElementTypeCell[starts-with(@name,\"send_image\")]";
            SHARED_VIDEO = "xpath://XCUIElementTypeCell[starts-with(@name,\"send_video\")]";
    }

    public iOSAllSharedMediaScreePageObject(AppiumDriver driver) {super(driver);}
}
