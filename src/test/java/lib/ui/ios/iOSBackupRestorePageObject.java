package lib.ui.ios;
import io.appium.java_client.AppiumDriver;
import lib.ui.BackupRestorePageObject;

public class iOSBackupRestorePageObject extends BackupRestorePageObject {
    static {
        RESTORE_BACKUP_BUTTON = "id:com.turkcell.bip:id/btnDownload";
        BACKUP_INFO_SIZE = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/tvRestoreSizeInfo and contains(@text, 'KB')]";
        SKIP_RESTORE_BACKUP_BUTTON = "id:com.turkcell.bip:id/tvSkip";
    }
    public iOSBackupRestorePageObject(AppiumDriver driver) {
        super(driver);
    }
}
