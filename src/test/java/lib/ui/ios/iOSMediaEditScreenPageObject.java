package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MediaEditScreenPageObject;

/**
 * iOS counterpart of {@link lib.ui.android.AndroidMediaEditScreenPageObject}.
 *
 * <p>Where Android folded its media editor into the gallery picker, iOS still
 * has both. Picking an item puts up a send bar — a caption field, a Send
 * button, a thumbnail — and tapping that thumbnail opens the editor proper,
 * with crop, HD, trim and GIF. Captioning and sending work from the send bar,
 * so most of the chain never goes a level deeper; {@link #openMediaEditor()}
 * is there for the steps that have to.
 */
public class iOSMediaEditScreenPageObject extends MediaEditScreenPageObject {

    /** The send bar's thumbnail, and the only way into the editor. */
    private static final String SEND_BAR_MEDIA_PREVIEW = "id:SelectedMediaSendBarMediaPreview";

    static {
            // Both screens can send, and they name their button differently, so
            // this has to match either one — the callers do not know, and should
            // not have to know, which of the two they are standing on.
            SEND_BUTTON = "xpath://*[@name=\"SelectedMediaSendBarSendButton\" or @name=\"Send Message\"]";
            // One field under two placeholders: "Type your message" on the send
            // bar, "Add a caption.." in the editor. It is already editable when
            // found, so there is nothing for the tap in addCaption() to open —
            // both halves point at it and the first tap is simply harmless.
            INPUT_BAR = "id:chatInputTextView";
            OPENED_INPUT_BAR = "id:chatInputTextView";

            // ---- editor-only controls ----------------------------------------------
            BACK_BUTTON = "id:backBarButtonItem";
            QUALITY_BUTTON = "id:hdButtonItem";
            GALLERY_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Gallery\"]";
            VIDEO_TRIM = "id:videoEditTrimView";
            GIF_BUTTON = "id:videoEditConvertGifButton";
            // Converting to GIF warns about the five-second limit first.
            GIF_POP_UP = "xpath://XCUIElementTypeAlert[@name=\"Warning\"]";
            GIF_POP_UP_OK_BUTTON = "xpath://XCUIElementTypeAlert[@name=\"Warning\"]//XCUIElementTypeButton[@name=\"OK\"]";
    }

    public iOSMediaEditScreenPageObject (AppiumDriver driver) {
        super(driver);
    }

    /** Opens the editor behind the send bar. See the class note. */
    @Override
    protected void openMediaEditor() {
        if (isElementPresent(SEND_BAR_MEDIA_PREVIEW)) {
            this.waitForElementAndClick(
                    SEND_BAR_MEDIA_PREVIEW,
                    "Can't open the media editor from the send bar",
                    15
            );
        } else {
            System.out.println("Media editor is already open");
        }
    }
}
