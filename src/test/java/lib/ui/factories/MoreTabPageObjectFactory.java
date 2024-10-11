package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MoreTabPageObject;
import lib.ui.android.AndroidMoreTabPageObject;
import lib.ui.ios.iOSMoreTabPageObject;

public class MoreTabPageObjectFactory {
    public static MoreTabPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMoreTabPageObject(driver);
        } else {
            return new iOSMoreTabPageObject(driver);
        }
    }
}