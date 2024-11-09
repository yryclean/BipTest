package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ChatInfoScreenPageObject;
import lib.ui.android.AndroidChatInfoScreenPageObject;
import lib.ui.ios.iOSChatInfoScreenPageObject;

public class ChatInfoScreenPageObjectFactory {
    public static ChatInfoScreenPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new AndroidChatInfoScreenPageObject(driver);
        } else {
            return new iOSChatInfoScreenPageObject(driver);
        }
    }
}
