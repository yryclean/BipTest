package lib.ui;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lib.ui.factories.MediaEditScreenPageObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Map;

/**
 * Attachments and media bubbles: picking a photo/video/gif from the gallery,
 * recording audio, and opening what was sent in full screen.
 *
 * @see ChatScreenPageObject for the full layering.
 */
public abstract class ChatScreenMediaPageObject extends ChatScreenDeletePageObject {

    protected static String
            ATTACHMENT_MENU_BUTTON,
            ATTACHMENT_MENU_BAR,
            ATTACHMENT_MENU_BAR_TOUCH_OUTSIDE,
            ATTACHMENT_MENU_GALLERY,
            ATTACHMENT_MENU_GALLERY_VIDEOS,
            ATTACHMENT_MENU_GALLERY_PHOTOS,
            ATTACHMENT_MENU_GALLERY_SELECT_VIDEO,
            ATTACHMENT_MENU_GALLERY_SELECT_PHOTO,
            ATTACHMENT_MENU_GALLERY_NEXT_BUTTON,
            RECORD_AUDIO_BUTTON,
            SENT_MESSAGE_PHOTO,
            SENT_MESSAGE_CLOCK_ICON,
            SENT_MESSAGE_VIDEO,
            SENT_MESSAGE_VIDEO_PLAY_ICON,
            SENT_MESSAGE_AUDIO,
            SENT_MESSAGE_GIF,
            SENT_MESSAGE_GIF_PLAY_ICON,
            SENT_MESSAGE_MEDIA_GROUPED,
            RECEIVED_MESSAGE_PHOTO,
            RECEIVED_MESSAGE_DOWNLOAD_BUTTON,
            // Captured, no test uses it yet.
            RECEIVED_MESSAGE_MEDIA_GROUPED;

    public ChatScreenMediaPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void tapOnInputBarAndOpenAttachMenu() {
        this.waitForElementAndClick(
                INPUT_BAR_FIELD,
                "Can't tap on input bar",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_BUTTON,
                "Can't tap on attach button",
                15
        );
        this.waitForElementPresent(
                ATTACHMENT_MENU_BAR,
                "Attach menu bar is not displayed",
                15
        );
    }

    public void openAttachMenuAndSelectPhoto() {
        this.waitForElementAndClick(
                INPUT_BAR_FIELD,
                "Can't tap on input bar",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_BUTTON,
                "Can't tap on attach button",
                15
        );
        this.waitForElementPresent(
                ATTACHMENT_MENU_BAR,
                "Attach menu bar is not displayed",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY,
                "Can't tap and open Gallery",
                15
        );
        this.waitForElementPresent(
                ATTACHMENT_MENU_GALLERY_PHOTOS,
                "Can't find Photos tab in Gallery",
                15
        );
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).index(0)).scrollIntoView(new UiSelector().description(\"Photo\").instance(12))"));
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY_SELECT_PHOTO,
                "Can't find photo to select",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY_NEXT_BUTTON,
                "Can't find and tap Next button",
                15
        );
    }

    public void openAttachMenuAndSelectVideo() {
        this.waitForElementAndClick(
                INPUT_BAR_FIELD,
                "Can't tap on input bar",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_BUTTON,
                "Can't tap on attach button",
                15
        );
        this.waitForElementPresent(
                ATTACHMENT_MENU_BAR,
                "Attach menu bar is not displayed",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY,
                "Can't tap and open Gallery",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY_VIDEOS,
                "Can't find Photos tab in Gallery",
                15
        );
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).index(0)).scrollIntoView(new UiSelector().description(\"1000003969\"))"));
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY_SELECT_VIDEO,
                "Can't find video to select",
                15
        );
        this.waitForElementAndClick(
                ATTACHMENT_MENU_GALLERY_NEXT_BUTTON,
                "Can't find and tap Next button",
                15
        );
    }

    public void closeAttachMenuBar() {
        this.waitForElementAndClick(
                ATTACHMENT_MENU_BAR_TOUCH_OUTSIDE,
                "Attach menu is not displayed",
                15
        );
    }

    public void selectPhotoMessageIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_PHOTO)) {
            System.out.println("Photo already sent and present in chat");
        } else {
            this.openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject media_edit_screen = MediaEditScreenPageObjectFactory.get(driver);
            media_edit_screen.tapSendButtonOnMediaEditScreen();
        }
    }

    public void selectPhotoWithCaption(String sent_photo_caption, String caption_text) {
        String sent_photo_xpath = getSentPhotoCaptionMessageByXpathName(sent_photo_caption);
        if (isElementPresent(sent_photo_xpath)) {
            System.out.println("Photo with caption already displayed in chat");
        } else {
            this.openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject media_edit_screen = MediaEditScreenPageObjectFactory.get(driver);
            media_edit_screen.addCaption(caption_text);
            media_edit_screen.tapSendButtonOnMediaEditScreen();
        }
    }

    public void selectVideoMessageIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_VIDEO)) {
            System.out.println("Video already sent and present in chat");
        } else {
            this.openAttachMenuAndSelectVideo();
            MediaEditScreenPageObject media_edit_screen = MediaEditScreenPageObjectFactory.get(driver);
            media_edit_screen.tapSendButtonOnMediaEditScreen();
        }
    }

    public void selectGifMessageIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_GIF)) {
            System.out.println("Gif already sent and present in chat");
        } else {
            this.openAttachMenuAndSelectVideo();
            MediaEditScreenPageObject media_edit_screen = MediaEditScreenPageObjectFactory.get(driver);
            media_edit_screen.tapGifButton();
            media_edit_screen.tapSendButtonOnMediaEditScreen();
        }
    }

    public void recordAudioIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_AUDIO)) {
            System.out.println("Audio message already sent and present in chat");
        } else {
            this.recordAudioMessage();
        }
    }

    /** Not longPressAction(): the mic needs a 3 s hold, that helper holds for 2 s. */
    public void recordAudioMessage() {
        WebElement element = this.waitForElementPresent(
                RECORD_AUDIO_BUTTON,
                "Can't find the record audio button",
                15
        );
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                Map.of("elementId", ((RemoteWebElement) element).getId(), "duration", 3000));
    }

    public void waitForSentPhoto() {
        this.waitForElementPresent(
                SENT_MESSAGE_PHOTO,
                "Can't find sent photo",
                15
        );
    }

    public void waitForSentVideo() {
        this.waitForElementPresent(
                SENT_MESSAGE_VIDEO,
                "Can't find sent video",
                15
        );
    }

    public void openSentPhotoInFullScreen() {
        if (isElementPresent(SENT_MESSAGE_CLOCK_ICON)) {
            this.waitForElementNotPresent(
                    SENT_MESSAGE_CLOCK_ICON,
                    "Clock icon is still displayed",
                    30
            );
            this.waitForElementAndClick(
                    SENT_MESSAGE_PHOTO,
                    "Can't open sent photo",
                    40
            );
        } else {
            this.waitForElementAndClick(
                    SENT_MESSAGE_PHOTO,
                    "Can't open sent photo",
                    40
            );
        }
    }

    public void openSentPhotoWithCaptionInFullScreen(String sent_photo_caption) {
        String sent_photo_xpath = getSentPhotoCaptionMessageByXpathName(sent_photo_caption);
        if (isElementPresent(SENT_MESSAGE_CLOCK_ICON)) {
            this.waitForElementNotPresent(
                    SENT_MESSAGE_CLOCK_ICON,
                    "Clock icon is still displayed",
                    30
            );
            this.waitForElementAndClick(
                    sent_photo_xpath,
                    "Can't open sent photo",
                    40
            );
        } else {
            this.waitForElementAndClick(
                    sent_photo_xpath,
                    "Can't open sent photo",
                    40
            );
        }
    }

    public void openReceivedPhotoInFullScreen() {
        if (isElementPresent(RECEIVED_MESSAGE_DOWNLOAD_BUTTON)) {
            this.waitForElementAndClick(
                    RECEIVED_MESSAGE_DOWNLOAD_BUTTON,
                    "Can't open sent photo",
                    40
            );
            this.waitForElementNotPresent(
                    RECEIVED_MESSAGE_DOWNLOAD_BUTTON,
                    "Download button is still displayed",
                    45
            );
            this.waitForElementAndClick(
                    RECEIVED_MESSAGE_PHOTO,
                    "Can't open received photo",
                    40
            );
        } else {
            this.waitForElementAndClick(
                    RECEIVED_MESSAGE_PHOTO,
                    "Can't open received photo",
                    40
            );
        }
    }

    public void openSentVideoInFullScreen() {
        this.waitForElementPresent(
                SENT_MESSAGE_VIDEO_PLAY_ICON,
                "Sent video is still waiting for upload",
                30
        );
        this.waitForElementAndClick(
                SENT_MESSAGE_VIDEO,
                "Can't open sent video",
                20
        );
    }

    public void openSentGifInFullScreen() {
        this.waitForElementPresent(
                SENT_MESSAGE_GIF_PLAY_ICON,
                "Sent gif is still waiting for upload",
                30
        );
        this.waitForElementAndClick(
                SENT_MESSAGE_GIF_PLAY_ICON,
                "Can't open sent gif",
                20
        );
        this.waitForElementAndClick(
                SENT_MESSAGE_GIF,
                "Can't open sent gif",
                20
        );
    }

    public void openSentGroupOfMedia(String chat_name) {
        if (isElementPresent(SENT_MESSAGE_MEDIA_GROUPED)) {
            this.waitForElementAndClick(
                    SENT_MESSAGE_MEDIA_GROUPED,
                    "Can't open group of media",
                    25
            );
        } else {
            // A grouped bubble only forms once four photos have been sent.
            MediaEditScreenPageObject media_edit_screen = MediaEditScreenPageObjectFactory.get(driver);
            for (int i = 0; i < 4; i++) {
                openAttachMenuAndSelectPhoto();
                media_edit_screen.tapSendButtonOnMediaEditScreen();
            }
            tapBackButtonOpenChatList();
            openChatWithName(chat_name);
            this.waitForElementAndClick(
                    SENT_MESSAGE_MEDIA_GROUPED,
                    "Can't open group of media",
                    25
            );
        }
    }

    public void longPressAndAddStarSentPhoto() {
        if (isElementPresent(STAR_ON_MESSAGE_BUBBLE)) {
            System.out.println("Message is starred already");
        } else {
            this.longPressAction(SENT_MESSAGE_PHOTO);
            this.addStarToMessage();
            Assert.assertTrue(isElementPresent(STAR_ON_MESSAGE_BUBBLE));
        }
    }
}
