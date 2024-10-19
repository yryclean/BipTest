package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SharedMediaScreenPageObject;
import lib.ui.android.AndroidSharedMediaScreenPageObject;
import lib.ui.ios.iOSSharedMediaScreenPageObject;

public class SharedMediaPageObjectFactory {
    public static SharedMediaScreenPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidSharedMediaScreenPageObject(driver);
        } else {
            return new iOSSharedMediaScreenPageObject(driver);
        }
    }
}
