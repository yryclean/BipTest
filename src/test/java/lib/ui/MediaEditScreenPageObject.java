package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MediaEditScreenPageObject extends MainPageObject {
    protected static String
            SEND_BUTTON,
            INPUT_BAR,
            OPENED_INPUT_BAR,
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
        this.openMediaEditor();
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
    /**
     * iOS keeps two screens where Android now keeps one: the picker's send bar
     * carries the caption and Send, but trimming, HD and GIF live a level
     * deeper, behind the send bar's preview. Captioning does not need that
     * level, so only the steps that do go through here.
     */
    protected void openMediaEditor() {
    }

    public void addCaption(String caption_text) {
        this.waitForElementAndClick(
                INPUT_BAR,
                "Can't open input bar",
                25
        );
        this.waitForElementAndSendKeys(
                OPENED_INPUT_BAR,
                caption_text,
                25
        );
    }
}
