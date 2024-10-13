package lib.ui.android;
import io.appium.java_client.AppiumDriver;
import lib.ui.ChatScreenPageObject;

public class AndroidChatScreenPageObject extends ChatScreenPageObject {
    static {
            CHAT_WITH_NAME_TPL = "xpath://android.widget.TextView[@content-desc='{CHAT_NAME}']";
            CHAT_WITH_NAME = "xpath://android.widget.TextView[@content-desc='Yuriy Chistyakov']";
            INPUT_BAR_FIELD = "id:com.turkcell.bip:id/chatEditText";
            SEND_MESSAGE_BUTTON = "id:com.turkcell.bip:id/iv_chat_send"; //android.widget.ImageButton[@content-desc="Send"]
            SENT_MESSAGE_BUBBLE_TPL = "xpath://android.widget.TextView[@content-desc='{SENT_MESSAGE}']";
            RECEIVED_MESSAGE_TPL = "xpath://android.widget.TextView[@content-desc='{RECEIVED_MESSAGE}']";
            ACTION_BAR_MENU = "id:com.turkcell.bip:id/action_mode_bar";
            DELETE_BUTTON = "id:com.turkcell.bip:id/item_delete_action"; //android.widget.Button[@content-desc="Delete"]
            CONFIRM_DELETE_POP_UP = "id:com.turkcell.bip:id/popup_container"; //android.widget.LinearLayout[@resource-id="com.turkcell.bip:id/popup_container"]
            DELETE_FROM_ME = "xpath://android.widget.RadioButton[@text=\"Delete from me\"]";
            DELETE_FROM_EVERYONE = "xpath://android.widget.RadioButton[@text=\"Delete from everyone\"]";
            OK_DELETE_FROM_ME = "xpath://android.widget.Button[@resource-id=\"com.turkcell.bip:id/btnPrimary\"]";
            CANCEL_DELETE = "xpath://android.widget.Button[@resource-id=\"com.turkcell.bip:id/btnSecondary\"]";
            UNDO_DELETE_FROM_ME_BAR = "xpath://androidx.compose.ui.platform.ComposeView[@resource-id=\"com.turkcell.bip:id/bottomComposeView\"]/android.view.View/android.view.View";
            UNDO_DELETE_COUNTER = "xpath://android.widget.TextView[@text='3']";
            UNDO_DELETE_1_MESSAGE_TEXT = "xpath://android.widget.TextView[@text='1 message deleted from me']";
            UNDO_DELETE_2_MESSAGES_TEXT = "xpath://android.widget.TextView[@text='2 messages deleted from me']";
            UNDO_DELETE_BUTTON = "xpath://android.widget.TextView[@text=\"Undo\"]";
            UNDO_DELETE_BUTTON_TAP = "xpath://androidx.compose.ui.platform.ComposeView[@resource-id=\"com.turkcell.bip:id/bottomComposeView\"]/android.view.View/android.view.View/android.view.View/android.widget.Button";
            CONTACT_INFO_PLACE_HOLDER = "id:com.turkcell.bip:id/headerChatTextHolder";
            CONTACT_INFO_SCREEN_ACTIVITY = "id:com.turkcell.bip:id/cl_activity_contact_info_root"; //android.widget.ScrollView[@resource-id="com.turkcell.bip:id/cl_activity_contact_info_root"]
            CONTACT_INFO_SCREEN_BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Back Button']";
            CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON = "id:Navigate up"; //android.widget.ImageButton[@content-desc="Navigate up"]
            ATTACHMENT_MENU_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Share\"]";
            ATTACHMENT_MENU_BAR = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/design_bottom_sheet\"]/android.view.ViewGroup";
            ATTACHMENT_MENU_BAR_TOUCH_OUTSIDE = "xpath://android.view.View[@resource-id=\"com.turkcell.bip:id/touch_outside\"]";
            WIFI_DISABLED_CONNECTION_POP_UP = "id:com.turkcell.bip:id/popup_container";//android.widget.LinearLayout[@resource-id="com.turkcell.bip:id/popup_container"]
            WIFI_POP_UP_OK_BUTTON = "xpath://android.widget.Button[@resource-id=\"com.turkcell.bip:id/btnPrimary\"]";
            ADD_STAR_TO_MESSAGE = "xpath://android.widget.Button[@content-desc=\"Add to favorite messages\"]";
            STAR_ON_MESSAGE_BUBBLE = "xpath://android.widget.ImageView[@content-desc=\"Add to favorite messages\"]";






    }
    public AndroidChatScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }

}
