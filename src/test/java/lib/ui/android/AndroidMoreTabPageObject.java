package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MoreTabPageObject;

public class AndroidMoreTabPageObject extends MoreTabPageObject {
    static {
        MORE_TAB = "xpath://android.view.ViewGroup[@content-desc='More ']";
        SETTINGS_OPTION_MORE = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/tv_title' and @text='Settings ']";

    }

    public AndroidMoreTabPageObject(AppiumDriver driver) {
        super(driver);
    }
}
