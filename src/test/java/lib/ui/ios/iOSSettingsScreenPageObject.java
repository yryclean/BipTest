package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SettingsScreenPageObject;

public class iOSSettingsScreenPageObject extends SettingsScreenPageObject {
    static {
        NOTIFICATIONS_OPTION = "id:Notifications";
    }
    public iOSSettingsScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
}
