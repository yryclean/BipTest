package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.AllSharedMediaScreePageObject;
import lib.ui.android.AndroidAllSharedMediaScreePageObject;
import lib.ui.ios.iOSAllSharedMediaScreePageObject;

public class AllSharedMediaScreePageObjectFactory {
    public static AllSharedMediaScreePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidAllSharedMediaScreePageObject(driver);
        } else {
            return new iOSAllSharedMediaScreePageObject(driver);
        }
    }
}
