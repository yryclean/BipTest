package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.BackupRestorePageObject;
import lib.ui.android.AndroidBackupRestorePageObject;
import lib.ui.ios.iOSBackupRestorePageObject;

public class BackupRestorePageObjectFactory {
    public static BackupRestorePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidBackupRestorePageObject(driver);
        } else {
            return new iOSBackupRestorePageObject(driver);
        }
    }
}
