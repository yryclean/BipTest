package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NotificationsScreenPageObject extends MainPageObject {
    protected static String
            NOTIFICATIONS_PUSH_NOTIFICATIONS_ENABLE_WARNING;
    public NotificationsScreenPageObject (AppiumDriver driver) {
        super(driver);
    }

    public void openSettingsFromNotificationScreen() {
        this.waitForElementAndClick(
                NOTIFICATIONS_PUSH_NOTIFICATIONS_ENABLE_WARNING,
                "Can't open Settings from Notifications screen",
                10
        );
    }
    public void isNotificationsWarningDisplayed() {
        this.waitForElementPresent(
                NOTIFICATIONS_PUSH_NOTIFICATIONS_ENABLE_WARNING,
                "Warning for disabled notifications is not displayed",
                10
        );
    }
    public void isNotificationsWarningNotDisplayed() {
        this.waitForElementNotPresent(
                NOTIFICATIONS_PUSH_NOTIFICATIONS_ENABLE_WARNING,
                "Warning for disabled notifications is not displayed",
                15
        );
    }
    public void openNotificationsSettingsFromWarning() {
        this.waitForElementAndClick(
                NOTIFICATIONS_PUSH_NOTIFICATIONS_ENABLE_WARNING,
                "Can't tap and open Notifications Settings",
                15
        );
    }

}