package lib.ui.ios;
import io.appium.java_client.AppiumDriver;
import lib.ui.MessagesTabPageObject;

/**
 * iOS counterpart of {@link lib.ui.android.AndroidMessagesTabPageObject}.
 *
 * <p>What was here before were Android locators —
 * {@code com.android.permissioncontroller:id/…} — copied across wholesale.
 * They could never match on iOS; they just made the class look populated.
 */
public class iOSMessagesTabPageObject extends MessagesTabPageObject {
    static {
            MESSAGES_TAB_SCREEN = "xpath://XCUIElementTypeNavigationBar[@name=\"Chats\"]";
            MORE_TAB = "id:TabbarMoreItem";
            // The list keeps recycled rows for the same chat off-screen, so the
            // visible one has to be named explicitly — see the same note in
            // iOSChatScreenPageObject.
            CHAT_WITH_NAME_TPL = "xpath://XCUIElementTypeCell[@name=\"chat_id\" and @visible=\"true\"]//XCUIElementTypeStaticText[@name=\"{CHAT_NAME}\"]";
            CHAT_CELL_WITH_NAME = "xpath://XCUIElementTypeCell[@name=\"chat_id\" and @visible=\"true\"]";

            // The permission and battery-optimization pop-ups have no iOS
            // equivalent in this form: contacts access is a system alert, and
            // Android's battery optimization simply does not exist here. Left
            // unset until the first-launch flow is walked on the device.
    }
    public iOSMessagesTabPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
