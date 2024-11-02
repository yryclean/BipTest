package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MediaEditScreenPageObject;

public class AndroidMediaEditScreenPageObject extends MediaEditScreenPageObject {
    static {
        SEND_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Send\"]";
        INPUT_BAR = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/tv_selected_media_caption\"]";
        GALLERY_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Gallery\"]";
        BACK_BUTTON = "id:com.turkcell.bip:id/back_button_image";
        VIDEO_TRIM = "xpath:com.turkcell.bip:id/trim_view";
        QUALITY_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Quality Selection\"]";
        GIF_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"GIF\"]";
        GIF_POP_UP = "xpath://android.widget.LinearLayout[@resource-id=\"com.turkcell.bip:id/popup_container\"]";
        GIF_POP_UP_OK_BUTTON = "id:com.turkcell.bip:id/btnPrimary";



    }

    public AndroidMediaEditScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
}
