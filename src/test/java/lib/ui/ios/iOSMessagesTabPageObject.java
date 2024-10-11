package lib.ui.ios;
import io.appium.java_client.AppiumDriver;
import lib.ui.MessagesTabPageObject;

public class iOSMessagesTabPageObject extends MessagesTabPageObject {
    static {
        BIP_CONTACTS_ACCESS_PERMISSION_POP_UP = "id:com.android.permissioncontroller:id/grant_dialog";
                ALLOW_CONTACTS_ACCESS_PERMISSION = "id:com.android.permissioncontroller:id/permission_allow_button";
                DENY_CONTACTS_ACCESS_PERMISSION = "id:com.android.permissioncontroller:id/permission_deny_button";
    }
    public iOSMessagesTabPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
