package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SettingsScreenPageObject;
import lib.ui.android.AndroidSettingsScreenPageObject;
import lib.ui.ios.iOSSettingsScreenPageObject;

public class SettingsScreenPageObjectFactory {
    public static SettingsScreenPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSettingsScreenPageObject(driver);
        } else {
            return new iOSSettingsScreenPageObject(driver);
        }
    }
}
