package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NotificationsScreenPageObject;

public class AndroidNotificationsScreenPageObject extends NotificationsScreenPageObject {
    static {
        NOTIFICATIONS_PUSH_NOTIFICATIONS_ENABLE_WARNING = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/settings_notification_header_text' and @text='Warning: Push Notifications are disabled. To enable visit: Go to Settings']";
    }
    public AndroidNotificationsScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
}
