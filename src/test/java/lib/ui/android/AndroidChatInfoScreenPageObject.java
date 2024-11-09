package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ChatInfoScreenPageObject;

public class AndroidChatInfoScreenPageObject extends ChatInfoScreenPageObject {
    static {
        SEE_ALL_MEDIA_BUTTON = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/tv_view_shared_media_panel' and @text='See All']";
        STARRED_MESSAGES = "xpath://android.widget.FrameLayout[@resource-id='com.turkcell.bip:id/fl_activity_contact_info_starred_messages' and @text='Starred Messages']";
    }
    public AndroidChatInfoScreenPageObject(AppiumDriver driver) {super(driver);}

}
