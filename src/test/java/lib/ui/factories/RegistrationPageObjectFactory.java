package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.RegistrationUIPageObject;
import lib.ui.android.AndroidRegistrationPageObject;
import lib.ui.ios.iOSRegistrationPageObject;

public class RegistrationPageObjectFactory {
    public static RegistrationUIPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidRegistrationPageObject(driver);
        } else {
            return new iOSRegistrationPageObject(driver);
        }
    }
}
