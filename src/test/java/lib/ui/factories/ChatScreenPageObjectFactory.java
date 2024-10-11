package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ChatScreenPageObject;
import lib.ui.android.AndroidChatScreenPageObject;
import lib.ui.ios.iOSChatScreenPageObject;

public class ChatScreenPageObjectFactory {
    public static ChatScreenPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidChatScreenPageObject(driver);
        } else {
            return new iOSChatScreenPageObject(driver);
        }
    }
}
