package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MediaEditScreenPageObject;

public class AndroidMediaEditScreenPageObject extends MediaEditScreenPageObject {
    static {
        // There is no separate media edit screen any more: once an item is picked,
        // the gallery picker itself grows a caption field and a Send button. Both
        // are Compose nodes without resource-ids, so they go by content-desc.
        SEND_BUTTON = "xpath://android.view.View[@content-desc=\"Send\"]/parent::android.view.View";
        INPUT_BAR = "xpath://androidx.compose.ui.viewinterop.ViewFactoryHolder/android.widget.EditText";
        OPENED_INPUT_BAR = "xpath://androidx.compose.ui.viewinterop.ViewFactoryHolder/android.widget.EditText";
        BACK_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Back Button\"]";
        QUALITY_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Off\"]";
        // Gone from the redesigned picker — kept so the chain still initialises,
        // but tapGifButton() and the video trim flow cannot work until the app
        // shows where these moved.
        GALLERY_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Gallery\"]";
        VIDEO_TRIM = "id:com.turkcell.bip:id/trim_view";
        GIF_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"GIF\"]";
        GIF_POP_UP = "xpath://android.widget.LinearLayout[@resource-id=\"com.turkcell.bip:id/popup_container\"]";
        GIF_POP_UP_OK_BUTTON = "id:com.turkcell.bip:id/btnPrimary";
    }

    public AndroidMediaEditScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
}
