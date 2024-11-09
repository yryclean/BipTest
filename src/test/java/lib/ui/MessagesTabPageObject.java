package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

public class MessagesTabPageObject extends MainPageObject {
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

    public void isMessagesTabScreenOpened() {
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
    public void openChatWithName(String chat_name) {
        String chat_xpath = getChatNameByXpathName(chat_name);
        this.waitForElementAndClick(
                (chat_xpath),
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
    public void isNotificationsSettingsOpened() {
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
    public void isNotificationsPopUpNotDisplayed() {
        this.waitForElementNotPresent(
                TURN_ON_NOTIFICATION_POP_UP,
                "Notification pop up is displayed",
                15
        );
        Assert.assertFalse(isElementPresent(TURN_ON_NOTIFICATION_POP_UP));
    }
}
