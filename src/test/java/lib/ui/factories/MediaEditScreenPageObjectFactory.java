package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MediaEditScreenPageObject;
import lib.ui.android.AndroidMediaEditScreenPageObject;
import lib.ui.ios.iOSMediaEditScreenPageObject;

public class MediaEditScreenPageObjectFactory {
    public static MediaEditScreenPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMediaEditScreenPageObject(driver);
        } else {
            return new iOSMediaEditScreenPageObject(driver);
        }
    }
}
