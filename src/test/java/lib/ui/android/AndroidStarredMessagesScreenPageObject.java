package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.StarredMessagesScreenPageObject;

public class AndroidStarredMessagesScreenPageObject extends StarredMessagesScreenPageObject {
        static {
            STARRED_MESSAGES_SCREEN_NAME = "xpath://android.widget.TextView[@text=\"Starred Messages\"]";
            STARRED_SCREEN_BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Back\"]";
            ANY_STARRED_PHOTO = "xpath://android.widget.ImageView[@content-desc=\"Photo\"]";

        }
    public AndroidStarredMessagesScreenPageObject(AppiumDriver driver) {super(driver);}


}
