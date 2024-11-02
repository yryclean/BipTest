package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SharedMediaScreenPageObject;

public class AndroidSharedMediaScreenPageObject extends SharedMediaScreenPageObject {
    static {
        SHARED_MEDIA_SCREEN_OVERVIEW = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/v_shared_media_overlay_items\"]";
        SHARED_MEDIA_SCREEN_TOP_PANEL = "xpath://android.widget.LinearLayout[@resource-id=\"com.turkcell.bip:id/big_sharedmedia_top_panel\"]";
        EDIT_PHOTO_BUTTON = "xpath://android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/iv_shared_media_edit\"]";
        VIDEO_PLAYING = "xpath://android.view.View[@resource-id=\"com.turkcell.bip:id/sharedmedia_item_player\"]";
        GIF_PLAYING = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/v_shared_media_overlay_items\"]";
        NEXT_MEDIA_LEFT_BUTTON = "xpath://android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/iv_shared_media_next\"]";
        NEXT_MEDIA_LEFT_BUTTON_ENABLED = "xpath://android.widget.ImageView[@resource-id='com.turkcell.bip:id/iv_shared_media_next'][@enabled='true']";
        NEXT_MEDIA_RIGHT_BUTTON = "xpath://android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/iv_shared_media_prev\"]";
        NEXT_MEDIA_RIGHT_BUTTON_ENABLED = "xpath://android.widget.ImageView[@resource-id='com.turkcell.bip:id/iv_shared_media_prev'][@enabled='true']";
        DELETE_MEDIA_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_delete";
        OPEN_ALL_MEDIA_BUTTON = "id:com.turkcell.bip:id/headerNavigationAllMedia";
        BACK_TO_CHAT_BUTTON = "id:com.turkcell.bip:id/headerNavigationBackButton";
        VIDEO_PLAY_BUTTON = "id:com.turkcell.bip:id/iv_play_btn";
        THREE_DOT_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_more";
        THREE_DOT_MENU = "id:com.turkcell.bip:id/rvMoreMenu";
        THREE_DOT_MENU_SAVE_GIF = "xpath://android.widget.TextView[@content-desc=\"Save Gif\"]";



    }

    public AndroidSharedMediaScreenPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
