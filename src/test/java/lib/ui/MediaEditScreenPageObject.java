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
            GIF_POP_UP,
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
    public void tapGifButton() {
        this.waitForElementAndClick(
                GIF_BUTTON,
                "Can't find and tap Gif button",
                15
        );
        if(isElementPresent(GIF_POP_UP_OK_BUTTON)) {
            this.waitForElementAndClick(
                    GIF_POP_UP_OK_BUTTON,
                    "Can't tap Ok button on Gif pop-up",
                    20
            );
        } else {
            System.out.println("Gif pop-up not displayed");
        }
    }
}
