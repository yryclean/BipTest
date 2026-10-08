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
            SENT_MESSAGE_MEDIA_GROUPED = "xpath://android.widget.LinearLayout[@content-desc=\"Sent message Grouped Media\"]";
            RECEIVED_MESSAGE_MEDIA_GROUPED = "xpath://android.widget.LinearLayout[@content-desc=\"Received message Grouped Media\"]";
            RECEIVED_MESSAGE_TPL = "xpath://android.widget.TextView[@content-desc='{RECEIVED_MESSAGE}']";
            RECEIVED_MESSAGE_PHOTO = "xpath://android.widget.LinearLayout[@content-desc=\"Received message Photo\"]";
            RECEIVED_MESSAGE_DOWNLOAD_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Download\"]";
            SENT_MESSAGE_PHOTO = "xpath://android.widget.LinearLayout[@content-desc=\"Sent message Photo\"]";
            SENT_MESSAGE_PHOTO_CAPTION_TPL = "xpath://android.widget.LinearLayout[@content-desc='{CAPTION}']";
            SENT_MESSAGE_CLOCK_ICON = "xpath://android.widget.ImageView[@content-desc=\"Waiting\"]";
            SENT_MESSAGE_VIDEO = "xpath://android.widget.LinearLayout[@content-desc=\"Sent message Video\"]";
            SENT_MESSAGE_VIDEO_PLAY_ICON = "id:com.turkcell.bip:id/chatItemImagePlay";
            SENT_MESSAGE_AUDIO = "xpath://android.widget.LinearLayout[@content-desc=\"Sent message Ses Kaydı\"]";
            SENT_MESSAGE_GIF = "xpath://android.widget.LinearLayout[@content-desc=\"Sent message Gif\"]";
            SENT_MESSAGE_GIF_PLAY_ICON = "xpath://android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/gifPlayButton\"]";
            SENT_MESSAGE_DELIVERY_INFO = "xpath://android.widget.LinearLayout[@resource-id=\"com.turkcell.bip:id/v_chat_item_delivery_info\"]";
            ACTION_BAR_MENU = "id:com.turkcell.bip:id/action_mode_bar";
            EDIT_BUTTON = "xpath://android.widget.Button[@content-desc=\"Edit\"]";
            EDIT_PREVIEW_ABOVE_INPUT_BAR = "xpath://android.view.ViewGroup[@resource-id=\"com.turkcell.bip:id/edit_message_preview\"]";
            EDIT_MESSAGE_INPUT_BAR = "xpath://android.widget.EditText[@resource-id=\"com.turkcell.bip:id/chatEditText\"]";
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
            CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
            ATTACHMENT_MENU_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Share\"]";
            ATTACHMENT_MENU_BAR = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/design_bottom_sheet\"]/android.view.ViewGroup";
            ATTACHMENT_MENU_BAR_TOUCH_OUTSIDE = "xpath://android.view.View[@resource-id=\"com.turkcell.bip:id/touch_outside\"]";
            ATTACHMENT_MENU_GALLERY = "xpath://android.widget.GridView[@resource-id=\"com.turkcell.bip:id/rv_chat_menu\"]/android.view.ViewGroup[2]";
            ATTACHMENT_MENU_GALLERY_VIDEOS = "xpath://android.widget.LinearLayout[contains(@content-desc, ideos)]";
            ATTACHMENT_MENU_GALLERY_PHOTOS = "xpath://android.widget.LinearLayout[contains(@content-desc, hotos)]";//android.widget.LinearLayout[@content-desc="photos"]
            ATTACHMENT_MENU_GALLERY_SELECT_VIDEO = "xpath://android.widget.ImageView[@content-desc=\"1000003969\"]";
            ATTACHMENT_MENU_GALLERY_SELECT_PHOTO = "xpath://androidx.recyclerview.widget.RecyclerView[@resource-id='com.turkcell.bip:id/rv_gallery']/android.view.ViewGroup[13]";
            ATTACHMENT_MENU_GALLERY_NEXT_BUTTON = "id:com.turkcell.bip:id/fab_send";
            WIFI_DISABLED_CONNECTION_POP_UP = "id:com.turkcell.bip:id/popup_container";//android.widget.LinearLayout[@resource-id="com.turkcell.bip:id/popup_container"]
            WIFI_POP_UP_OK_BUTTON = "xpath://android.widget.Button[@resource-id=\"com.turkcell.bip:id/btnPrimary\"]";
            ADD_STAR_TO_MESSAGE = "xpath://android.widget.Button[@content-desc=\"Add to favorite messages\"]";
            STAR_ON_MESSAGE_BUBBLE = "xpath://android.widget.ImageView[@content-desc=\"Add to favorite messages\"]";
            RECORD_AUDIO_BUTTON = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/iv_chat_panel_mic\"]";
            THREE_DOTS_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"More options\"]";
            SECRET_MESSAGE_BUTTON = "xpath://android.widget.TextView[contains(@text, 'Secret Message')]";
            SECRET_TIME_PICKER = "xpath://android.widget.SeekBar[@resource-id=\"com.turkcell.bip:id/seek_bar\"]";
            SECRET_TIMER_SET_TIME = "xpath:///android.widget.TextView[@text='60 sec']";//need fixes
            SECRET_MESSAGE_DISABLE = "xpath://android.widget.SeekBar[@resource-id=\"com.turkcell.bip:id/seek_bar\"].android.widget.TextView[contains(@text, 'Off')]";
            SECRET_MESSAGE_DISABLED_INFO = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/chatItemTitleTextForGroup\" and @text=\"You disabled disappearing messages. Tap to change.\"]";
            SECRET_TIMER_APPLY_BUTTON = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/apply_button\"]";
            SECRET_MESSAGE_COUNTER = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/txtSecretChatCounter\"]";
            CLEAR_CHAT_BUTTON = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/title\" and @text=\"Clear chat\"]";
            CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE = "xpath://android.widget.CheckBox[@resource-id='com.turkcell.bip:id/checkBoxOption' and @text='Delete starred messages.']";
            CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE_DELETE_BUTTON = "xpath://android.widget.Button[@resource-id='com.turkcell.bip:id/btnPrimary' and @text='Delete']";
            CLEAR_CHAT_POP_UP_OK_BUTTON = "id:com.turkcell.bip:id/btnPrimary";
            CLEAR_CHAT_POP_UP_CANCEL_BUTTON = "id:com.turkcell.bip:id/btnSecondary";
            EMPTY_CHAT_SCREEN_POINT = "xpath://android.widget.LinearLayout[@resource-id=\"com.turkcell.bip:id/messageRow\"]";
            MESSAGE_BUBBLE_ON_SCREEN = "xpath://android.view.ViewGroup[contains(@resource-id, 'com.turkcell.bip:id/chatItemContentBox')]";
            PIN_MESSAGE_BUTTON = "xpath://android.widget.Button[@content-desc=\"Pin\"]";
            UNPIN_MESSAGE_BUTTON = "xpath://android.widget.Button[@content-desc=\"Unpin\"]";
            UNPIN_MESSAGE_PIN_BAR = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/title\" and @text=\"Unpin\"]";
            UNPIN_ALL_MESSAGES_PIN_BAR = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/title\" and @text=\"Unpin All\"]";
            PIN_ICON_ON_SENT_MESSAGE = "xpath://android.widget.ImageView[@content-desc='Pin']";
            PINNED_MESSAGE_IN_PIN_BAR = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/tv_pinned_message_preview_content\"]";
            PINNED_MESSAGE_BAR = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/pinnedMessagesContainerView\"]";
            YOU_PINNED_INFO_MESSAGE = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/chatItemTitleTextForGroup\" and @text=\"You pinned a message\"]";
            PIN_LIMIT_WARNING = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/tvDialogTitle\" and @text='Replace oldest pin?']";
            PIN_LIMIT_WARNING_OK = "xpath://android.widget.Button[@resource-id=\"com.turkcell.bip:id/btnPrimary\" and @text='Continue']";
            PIN_LIMIT_WARNING_CANCEL = "xpath://android.widget.Button[@resource-id=\"com.turkcell.bip:id/btnSecondary\" and @text='Cancel']";
            ENCRYPTED_CHAT_INFO_MESSAGE = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/chatItemTitleTextForGroup\" and contains(@text, 'Messages and calls are end-to-end encrypted.')]";










    }
    public AndroidChatScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }

}
