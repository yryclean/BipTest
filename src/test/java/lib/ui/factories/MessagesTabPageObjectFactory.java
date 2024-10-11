package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MessagesTabPageObject;
import lib.ui.android.AndroidMessagesTabPageObject;
import lib.ui.ios.iOSMessagesTabPageObject;

public class MessagesTabPageObjectFactory {

    public static MessagesTabPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMessagesTabPageObject(driver);
        } else {
            return new iOSMessagesTabPageObject(driver);
        }
    }
}
