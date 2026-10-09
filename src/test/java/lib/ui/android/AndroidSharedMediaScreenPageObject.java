package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SharedMediaScreenPageObject;

public class AndroidSharedMediaScreenPageObject extends SharedMediaScreenPageObject {

    /**
     * Every row of the viewer's three-dot menu is the same widget with the
     * same id — {@code tv_more_menu_popup_item} — so the text is the only
     * thing that tells Edit from Share from Delete.
     */
    private static String moreMenuRow(String title) {
        return "xpath://android.widget.TextView"
                + "[@resource-id=\"com.turkcell.bip:id/tv_more_menu_popup_item\" and @text=\"" + title + "\"]";
    }

    static {
        SHARED_MEDIA_SCREEN_OVERVIEW = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/v_shared_media_overlay_items\"]";
        SHARED_MEDIA_SCREEN_TOP_PANEL = "xpath://android.widget.LinearLayout[@resource-id=\"com.turkcell.bip:id/big_sharedmedia_top_panel\"]";
        EDIT_PHOTO_BUTTON = moreMenuRow("Edit");
        VIDEO_PLAYING = "xpath://android.view.View[@resource-id=\"com.turkcell.bip:id/sharedmedia_item_player\"]";
        GIF_PLAYING = "xpath://android.widget.FrameLayout[@resource-id=\"com.turkcell.bip:id/v_shared_media_overlay_items\"]";
        NEXT_MEDIA_LEFT_BUTTON = "xpath://android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/iv_shared_media_next\"]";
        NEXT_MEDIA_LEFT_BUTTON_ENABLED = "xpath://android.widget.ImageView[@resource-id='com.turkcell.bip:id/iv_shared_media_next'][@enabled='true']";
        NEXT_MEDIA_RIGHT_BUTTON = "xpath://android.widget.ImageView[@resource-id=\"com.turkcell.bip:id/iv_shared_media_prev\"]";
        NEXT_MEDIA_RIGHT_BUTTON_ENABLED = "xpath://android.widget.ImageView[@resource-id='com.turkcell.bip:id/iv_shared_media_prev'][@enabled='true']";
        DELETE_MEDIA_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_delete";
        OPEN_ALL_MEDIA_BUTTON = moreMenuRow("Show all media");
        BACK_TO_CHAT_BUTTON = "id:com.turkcell.bip:id/headerNavigationBackButton";
        VIDEO_PLAY_BUTTON = "id:com.turkcell.bip:id/iv_play_btn";
        THREE_DOT_BUTTON = "id:com.turkcell.bip:id/iv_shared_media_more";
        THREE_DOT_MENU = "id:com.turkcell.bip:id/rvMoreMenu";
        THREE_DOT_MENU_SAVE_GIF = "xpath://android.widget.TextView[@content-desc=\"Save Gif\"]";
        MEDIA_FROM_GROUP_OPEN_ITEM = "xpath://androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.turkcell.bip:id/rv_grouped_images\"]";



    }

    public AndroidSharedMediaScreenPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /** Edit is a row of the three-dot menu here, so the menu has to be open. */
    @Override
    protected void revealEditButton() {
        if (!isElementPresent(THREE_DOT_MENU)) {
            this.openMediaOverflowMenu();
        }
    }

    /**
     * Back dismisses the popup and leaves the viewer behind it untouched —
     * checked on the device, because a back press that went one screen too
     * far would silently turn every later step into a chat-screen step.
     */
    @Override
    protected void hideEditButton() {
        if (isElementPresent(THREE_DOT_MENU)) {
            driver.navigate().back();
            this.waitForElementNotPresent(
                    THREE_DOT_MENU,
                    "More menu is still displayed",
                    10
            );
        }
    }
}
