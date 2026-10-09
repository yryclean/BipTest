package lib.ui;

import io.appium.java_client.AppiumDriver;

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
    /**
     * Was a UiAutomator scrollIntoView, which only exists on Android and spelled
     * the row's label a second time — so the one place the label is supposed to
     * live, {@code SETTINGS_OPTION_MORE}, was not the place that found it. The
     * generic swipe works on both platforms and takes the locator it already has.
     */
    public void scrollToSettings() {
        this.swipeUpToFindElement(
                SETTINGS_OPTION_MORE,
                "Can't find Settings in the More tab",
                5
        );
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

