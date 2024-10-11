package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NotificationsScreenPageObject;
import lib.ui.android.AndroidNotificationsScreenPageObject;
import lib.ui.ios.iOSNotificationsScreenPageObject;

public class NotificationsScreenPageObjectFactory {
    public static NotificationsScreenPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNotificationsScreenPageObject(driver);
        } else {
            return new iOSNotificationsScreenPageObject(driver);
        }
    }
}
