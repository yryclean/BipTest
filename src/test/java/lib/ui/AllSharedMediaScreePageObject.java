package lib.ui;

import io.appium.java_client.AppiumDriver;

public class AllSharedMediaScreePageObject extends MainPageObject {
    protected static String
            ALL_SHARED_MEDIA_SCREEN_OVERVIEW,
            BACK_TO_CHAT_BUTTON,
            MEDIA_TAB ,
            DOCUMENTS_TAB,
            LINKS_TAB,
            NAME_OF_CHAT_TPL,
            SHARED_VIDEO,
            SHARED_PHOTO;

    public AllSharedMediaScreePageObject(AppiumDriver driver) {super(driver);}

    private static String getChatNameByXpathName(String chat_name) {
        return NAME_OF_CHAT_TPL.replace("{CHAT_NAME}", chat_name);
    }
    public void openSharedPhoto() {
        this.waitForElementAndClick(
                SHARED_PHOTO,
                "Can't open shared photo",
                20
        );
    }

}
