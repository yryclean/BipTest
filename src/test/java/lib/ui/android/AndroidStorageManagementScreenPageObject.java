package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.StorageManagementScreenPageObject;

public class AndroidStorageManagementScreenPageObject extends StorageManagementScreenPageObject {
    static {
        CHAT_WITH_NAME_TPL = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/tv_chat_name' and @text='{CHAT_NAME}']";
        PHOTO_ITEM_IN_CHAT = "xpath:(//android.widget.ImageView[@resource-id='com.turkcell.bip:id/iv_media_image'])";
        SHOW_IN_CHAT_BUTTON = "xpath://android.widget.ImageView[@content-desc='Show in Chat']";
    }
    public AndroidStorageManagementScreenPageObject(AppiumDriver driver) {super(driver);}


}
