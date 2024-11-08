package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MessagesTabPageObject;

public class AndroidMessagesTabPageObject extends MessagesTabPageObject {
    static {
                MESSAGES_TAB_SCREEN = "xpath://android.view.ViewGroup[@content-desc='Messages ']";
                MORE_TAB = "xpath://android.view.ViewGroup[@content-desc=\"More \"]";
                BIP_CONTACTS_ACCESS_PERMISSION_POP_UP = "id:com.android.permissioncontroller:id/grant_dialog";
                ALLOW_CONTACTS_ACCESS_PERMISSION = "id:com.android.permissioncontroller:id/permission_allow_button";
                DENY_CONTACTS_ACCESS_PERMISSION = "id:com.android.permissioncontroller:id/permission_deny_button";
                TURN_ON_NOTIFICATION_POP_UP = "xpath://android.widget.TextView[@text=\"Turn on notifications for BiP\"]";
                CLOSE_NOTIFICATION_POP_UP_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Close\"]";
                GO_TO_SETTINGS_NOTIFICATION_POP_UP = "xpath://android.widget.TextView[@text=\"Go to Settings\"]";
                ALLOW_SETTINGS_NOTIFICATIONS = "id:com.android.settings:id/switch_text";
                ALLOW_SETTINGS_NOTIFICATIONS_ON_OFF = "id:com.android.settings:id/switch_widget";
                CLOSE_SETTINGS_NOTIFICATIONS = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                BATTERY_OPTIMIZATION_CONTINUE = "id:com.turkcell.bip:id/continue_button";
                BATTERY_OPTIMIZATION_POP_UP = "id:android:id/alertTitle";
                ALLOW_BATTERY_OPTIMIZATION = "id:android:id/button1";
                DENY_BATTERY_OPTIMIZATION = "id:android:id/button2";
                CHAT_CELL_WITH_NAME = "xpath://android.widget.TextView[@content-desc='GiRLzzx']";
    }
    public AndroidMessagesTabPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
