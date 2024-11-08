package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.StarredMessagesScreenPageObject;
import lib.ui.android.AndroidStarredMessagesScreenPageObject;
import lib.ui.ios.iOSStarredMessagesScreenPageObject;

public class StarredMessagesScreenPageObjectFactory {
    public static StarredMessagesScreenPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidStarredMessagesScreenPageObject(driver);
        } else {
            return new iOSStarredMessagesScreenPageObject(driver);
        }
    }
}
