package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SettingsScreenPageObject extends MainPageObject {
    protected static String
            NOTIFICATIONS_OPTION,
            STORAGE_MANAGEMENT;

    public SettingsScreenPageObject (AppiumDriver driver) {
        super(driver);
    }

    public void openNotificationsScreen() {
        this.waitForElementAndClick(
                NOTIFICATIONS_OPTION,
                "Can't find and open Notifications screen",
                10
        );
    }
    public void openStorageManagement() {
        this.waitForElementAndClick(
                STORAGE_MANAGEMENT,
                "Can't open Storage management screen",
                20
        );
    }
}
