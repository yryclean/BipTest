package lib.ui;
import io.appium.java_client.AppiumDriver;

abstract public class BackupRestorePageObject extends MainPageObject {
    protected static String
    RESTORE_BACKUP_BUTTON,
    BACKUP_INFO_SIZE,
    SKIP_RESTORE_BACKUP_BUTTON;


    public BackupRestorePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void restoreBackup() {
        this.waitForElementAndClick(
                RESTORE_BACKUP_BUTTON,
                "Can't find and tap restore backup button",
                10
        );
    }

}
