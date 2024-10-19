package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MediaEditScreenPageObject extends MainPageObject {
    protected static String
            SEND_BUTTON,
            INPUT_BAR,
            GALLERY_BUTTON,
            BACK_BUTTON,
            VIDEO_TRIM,
            QUALITY_BUTTON,
            GIF_BUTTON,
            GIF_POP_UP_OK_BUTTON;


    public MediaEditScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }


    public void tapSendButtonOnMediaEditScreen() {
        this.waitForElementAndClick(
                SEND_BUTTON,
                "Media edit screen is not opened",
                15
        );
    }

}
