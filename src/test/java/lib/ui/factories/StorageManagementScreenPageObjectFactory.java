package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.StorageManagementScreenPageObject;
import lib.ui.android.AndroidStorageManagementScreenPageObject;
import lib.ui.ios.iOSStorageManagementScreenPageObject;

public class StorageManagementScreenPageObjectFactory {
    public static StorageManagementScreenPageObject get (AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidStorageManagementScreenPageObject(driver);
        } else {
            return new iOSStorageManagementScreenPageObject(driver);
        }
    }
}
