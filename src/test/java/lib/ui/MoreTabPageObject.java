package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

abstract public class MoreTabPageObject extends MainPageObject {
    protected static String
            MORE_TAB,
            SETTINGS_OPTION_MORE,
            STARRED_MESSAGES_OPTION;
    public MoreTabPageObject (AppiumDriver driver) {
        super(driver);
    }

    public void tapMoreTab()
    {
    this.waitForElementAndClick(
            MORE_TAB,
            "Can't tap and open More tab",
            15
    );
}
    public void scrollToSettings() {
//        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains('Settings').instance(0))"));
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(text(\"Settings \"))"));
    }
    public void openSettingsScreen() {
        this.waitForElementAndClick(
                SETTINGS_OPTION_MORE,
                "Can't open Settings screen",
                15
        );
    }
    public void openStarredMessagesScreen() {
        this.waitForElementAndClick(
                STARRED_MESSAGES_OPTION,
                "Can't open Starred Messages screen",
                20
        );
    }
}

