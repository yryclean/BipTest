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
        // The grid is Compose, so there is not a single resource-id on it and
        // the old sharedMediaImage could never match. What distinguishes the
        // cells is their badge: a video gets a duration, a gif a file size, a
        // photo nothing at all. So a photo is the clickable cell with no text
        // under it — and no content-desc either, which is what keeps the Back
        // button (also a bare clickable View) out of the match.
        SHARED_PHOTO = "xpath://android.view.View[@clickable=\"true\""
                + " and not(.//android.widget.TextView)"
                + " and not(.//*[@content-desc!=\"\"])]";
    }

    public AndroidAllSharedMediaScreePageObject(AppiumDriver driver) {super(driver);}

}
