package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ChatInfoScreenPageObject;

/**
 * Both rows point at the clickable container, not at the label inside it:
 * the label is a non-clickable {@code TextView}, so a tap on it only works by
 * accident of hit-testing.
 *
 * <p>The row this used to call "See All" is now "Media, links and docs", and
 * the ids changed with it. The previous locators also put an {@code @text} on
 * a layout node, which never carries one — so they could not have matched
 * even before the screen was redesigned.
 */
public class AndroidChatInfoScreenPageObject extends ChatInfoScreenPageObject {
    static {
        SEE_ALL_MEDIA_BUTTON = "id:com.turkcell.bip:id/sharedMediaPanelHeader";
        STARRED_MESSAGES = "id:com.turkcell.bip:id/cl_contact_info_starred_messages";
    }
    public AndroidChatInfoScreenPageObject(AppiumDriver driver) {super(driver);}

}
