package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SharedMediaScreenPageObject;

public class AndroidSharedMediaScreenPageObject extends SharedMediaScreenPageObject {
    static {
        SHARED_MEDIA_SCREEN_OVERVIEW = "id:com.turkcell.bip:id/v_shared_media_overlay_items";
        EDIT_PHOTO_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_edit";
        NEXT_MEDIA_LEFT_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_prev";
        NEXT_MEDIA_RIGHT_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_prev";
        DELETE_MEDIA_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_delete";
        OPEN_ALL_MEDIA_BUTTON = "id:com.turkcell.bip:id/headerNavigationAllMedia";
        BACK_TO_CHAT_BUTTON = "id:com.turkcell.bip:id/headerNavigationBackButton";
        VIDEO_PLAY_BUTTON = "id:com.turkcell.bip:id/iv_play_btn";


    }

    public AndroidSharedMediaScreenPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
