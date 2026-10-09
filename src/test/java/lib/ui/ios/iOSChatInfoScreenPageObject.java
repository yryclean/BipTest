package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ChatInfoScreenPageObject;

/**
 * iOS counterpart of {@link lib.ui.android.AndroidChatInfoScreenPageObject} —
 * the "Contact info" page reached by tapping the chat header's name.
 *
 * <p>Both rows are addressed by their {@code label} rather than their
 * {@code name}, because the names carry live counts:
 * {@code sharedMedia(count: 42)} and {@code starredMessages(count: 0)}. Those
 * change every time the chat does, so a name-based locator would pass once and
 * then quietly start missing. The label is the row's visible title and stays
 * put.
 *
 * <p>Tapping either row lands on a screen this suite already models —
 * {@link iOSAllSharedMediaScreePageObject} for media, the starred messages
 * screen for the other — so nothing further is needed here.
 */
public class iOSChatInfoScreenPageObject extends ChatInfoScreenPageObject {
    static {
        SEE_ALL_MEDIA_BUTTON = "xpath://XCUIElementTypeCell[@label=\"Media, links and docs\"]";
        STARRED_MESSAGES = "xpath://XCUIElementTypeCell[@label=\"Starred messages\"]";
    }

    public iOSChatInfoScreenPageObject(AppiumDriver driver) {super(driver);}
}
