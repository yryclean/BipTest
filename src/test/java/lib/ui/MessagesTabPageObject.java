package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import lib.Platform;
import org.junit.Assert;

public class MessagesTabPageObject extends MainPageObject {

    /** Deep enough for the longest screen stack the suite reaches, short enough
     *  that a broken locator fails fast instead of backing out of the app. */
    private static final int MAX_BACK_PRESSES = 6;

    /** Only used where the platform has no scroll-into-view-by-text — see
     *  {@link #openChatWithName}. Covers a chat well down a busy list. */
    private static final int MAX_CHAT_LIST_SWIPES = 10;

    protected static String
    MESSAGES_TAB_SCREEN,
    MORE_TAB,
    BIP_CONTACTS_ACCESS_PERMISSION_POP_UP,
    ALLOW_CONTACTS_ACCESS_PERMISSION,
    DENY_CONTACTS_ACCESS_PERMISSION,
    TURN_ON_NOTIFICATION_POP_UP,
    CLOSE_NOTIFICATION_POP_UP_BUTTON,
    GO_TO_SETTINGS_NOTIFICATION_POP_UP,
    ALLOW_SETTINGS_NOTIFICATIONS,
    ALLOW_SETTINGS_NOTIFICATIONS_ON_OFF,
    CLOSE_SETTINGS_NOTIFICATIONS,
    BATTERY_OPTIMIZATION_CONTINUE,
    BATTERY_OPTIMIZATION_POP_UP,
    ALLOW_BATTERY_OPTIMIZATION,
    DENY_BATTERY_OPTIMIZATION,
    CHAT_CELL_WITH_NAME,
    CHAT_WITH_NAME_TPL;

    public MessagesTabPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static String getChatNameByXpathName(String chat_name) {
        return CHAT_WITH_NAME_TPL.replace("{CHAT_NAME}", chat_name);
    }

    public boolean isChatListOpen() {
        return isElementPresent(MESSAGES_TAB_SCREEN);
    }

    /**
     * Best effort walk back towards the chat list. Deliberately silent about
     * failure: enough back presses will leave BiP altogether, and only the
     * caller can bring it back, so it reports nothing and lets the caller
     * re-check with {@link #isChatListOpen()}.
     */
    public void pressBackTowardsChatList() {
        for (int i = 0; i < MAX_BACK_PRESSES && !isChatListOpen(); i++) {
            driver.navigate().back();
        }
    }

    public void waitForChatList() {
        this.waitForElementPresent(
                MESSAGES_TAB_SCREEN,
                "Could not get back to the chat list",
                20
        );
    }

    public void assertMessagesTabScreenOpened() {
        this.waitForElementPresent(
                MESSAGES_TAB_SCREEN,
                "Messages tab screen is not opened",
                20
        );
    }
    public void contactsAccessPermissionAllow()
    {
        this.waitForElementAndClick(
                ALLOW_CONTACTS_ACCESS_PERMISSION,
                "Can't tap and allow contacts permission",
                10
        );
    }
    /**
     * Scrolling the chat list is the one part of this class that is not just a
     * locator: UiAutomator2 can be asked to scroll a row into view by text in a
     * single call, XCUITest has no equivalent, so there we swipe until the row
     * shows up. {@code androidUIAutomator} throws outright on iOS, which is why
     * this cannot stay unconditional.
     */
    public void openChatWithName(String chat_name) {
        String chat_locator = getChatNameByXpathName(chat_name);
        if (Platform.getInstance().isAndroid()) {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + chat_name + "\").instance(0))"));
        } else {
            this.swipeUpToFindElement(
                    chat_locator,
                    "Cannot find chat " + chat_name + " in the list",
                    MAX_CHAT_LIST_SWIPES
            );
        }
        this.waitForElementAndClick(
                (chat_locator),
                "Cannot open chat " + chat_name,
                15
        );
    }

    public void waitForNotificationsPopUp() {
        this.waitForElementPresent(
                TURN_ON_NOTIFICATION_POP_UP,
                "Can't find notification pop up",
                10
        );
        Assert.assertTrue(isElementPresent(TURN_ON_NOTIFICATION_POP_UP));
    }
    public void openSettingsFromNotificationPopUp() {
        this.waitForElementAndClick(
                GO_TO_SETTINGS_NOTIFICATION_POP_UP,
                "Can't find and tap Go to settings button",
                10
        );
    }
    public void batteryOptimizationContinue() {
        this.waitForElementAndClick(
                BATTERY_OPTIMIZATION_CONTINUE,
                "Can't find and tap continue optimization button",
                10
        );
    }
    public void batteryOptimizationPopUP() {
        this.waitForElementPresent(
                BATTERY_OPTIMIZATION_POP_UP,
                "Can't find and tap continue optimization button",
                10
        );
    }
    public void batteryOptimizationAllow() {
        this.waitForElementAndClick(
                ALLOW_BATTERY_OPTIMIZATION,
                "Can't find and tap continue optimization button",
                10
        );
    }
    public void batteryOptimizationDeny() {
        this.waitForElementAndClick(
                DENY_BATTERY_OPTIMIZATION,
                "Can't find and tap continue optimization button",
                10
        );
    }
    public void assertNotificationsSettingsOpened() {
        this.waitForElementPresent(
                ALLOW_SETTINGS_NOTIFICATIONS,
                "Notification settings is not opened",
                10
        );
        Assert.assertTrue(ALLOW_SETTINGS_NOTIFICATIONS,true);
    }

    public void tapNotificationsSettingsButton() {
        this.waitForElementAndClick(
                ALLOW_SETTINGS_NOTIFICATIONS_ON_OFF,
                "Can't tap on the Allow notifications button",
                10
        );
    }

    public void closeNotificationsSettings() {
        this.waitForElementAndClick(
                CLOSE_SETTINGS_NOTIFICATIONS,
                "Can't close notification settings",
                10
        );
    }
    public void closeNotificationsPopUp()
    {
        this.waitForElementAndClick(
                CLOSE_NOTIFICATION_POP_UP_BUTTON,
                "Can't close notifications pop-up",
                10
        );
    }
    public void openMoreTab() {
        this.waitForElementAndClick(
                MORE_TAB,
                "Can't open More tab",
                10
        );
    }
    public void assertNotificationsPopUpNotDisplayed() {
        this.waitForElementNotPresent(
                TURN_ON_NOTIFICATION_POP_UP,
                "Notification pop up is displayed",
                15
        );
        Assert.assertFalse(isElementPresent(TURN_ON_NOTIFICATION_POP_UP));
    }
}
