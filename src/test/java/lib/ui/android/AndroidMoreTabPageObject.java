package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MoreTabPageObject;

public class AndroidMoreTabPageObject extends MoreTabPageObject {
    static {
        SETTINGS_OPTION_MORE = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/tv_title' and @text='Settings ']";
        STARRED_MESSAGES_OPTION = "xpath://android.widget.TextView[@resource-id=\"com.turkcell.bip:id/tv_title\" and @text=\"Starred Messages \"]";

    }

    public AndroidMoreTabPageObject(AppiumDriver driver) {
        super(driver);
    }
}
