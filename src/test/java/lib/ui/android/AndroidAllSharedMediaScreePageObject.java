package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.AllSharedMediaScreePageObject;

public class AndroidAllSharedMediaScreePageObject extends AllSharedMediaScreePageObject {
    static {
        ALL_SHARED_MEDIA_SCREEN_OVERVIEW = "id:com.turkcell.bip:id/shared_media_activity_container";
        BACK_TO_CHAT_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
        MEDIA_TAB = "xpath://android.widget.LinearLayout[@content-desc=\"MEDIA\"]";
        DOCUMENTS_TAB = "xpath://android.widget.LinearLayout[@content-desc=\"DOCUMENTS\"]";
        LINKS_TAB = "xpath://android.widget.LinearLayout[@content-desc=\"LINKS\"]";
        NAME_OF_CHAT_TPL = "xpath://android.widget.TextView[@text='{CHAT_NAME}']";
        SHARED_VIDEO = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/sharedMediaTime\"][contains(@text, '00:')]";
        SHARED_PHOTO = "xpath:(//android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/sharedMediaImage\"])";
    }

    public AndroidAllSharedMediaScreePageObject(AppiumDriver driver) {super(driver);}

}
