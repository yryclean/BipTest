package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SettingsScreenPageObject;

public class AndroidSettingsScreenPageObject extends SettingsScreenPageObject {
    static {
        NOTIFICATIONS_OPTION = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/settings_item_textview' and @text='Notifications']";
    }
    public AndroidSettingsScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
}
