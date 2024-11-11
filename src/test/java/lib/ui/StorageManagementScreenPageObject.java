package lib.ui;

import io.appium.java_client.AppiumDriver;

public class StorageManagementScreenPageObject extends MainPageObject {
    protected static String
            CHAT_WITH_NAME_TPL,
            PHOTO_ITEM_IN_CHAT,
            SHOW_IN_CHAT_BUTTON;


    public StorageManagementScreenPageObject(AppiumDriver driver) {super(driver);}

    private static String getChatNameByXpathName(String chat_name) {
        return CHAT_WITH_NAME_TPL.replace("{CHAT_NAME}", chat_name);
    }

    public void openChatWithName(String chat_name) {
        String chat_xpath = getChatNameByXpathName(chat_name);
        this.waitForElementAndClick(
                (chat_xpath),
                "Cannot open chat " + chat_name,
                15
        );
    }

    public void openPhotoItemInFullScreen() {
        this.waitForElementAndClick(
                PHOTO_ITEM_IN_CHAT,
                "Can't open photo item in full screen",
                20
        );
    }

    public void showPhotoInChat(){
        this.waitForElementAndClick(
                SHOW_IN_CHAT_BUTTON,
                "Can't show selected photo in chat",
                20
        );
    }
}
