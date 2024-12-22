package lib.ui;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.ui.factories.MediaEditScreenPageObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;

public abstract class ChatScreenPageObject extends MainPageObject {
    protected static String
            CHAT_WITH_NAME_TPL,
            CHAT_WITH_NAME,
            INPUT_BAR_FIELD,
            SEND_MESSAGE_BUTTON,
            SENT_MESSAGE_BUBBLE_TPL,
            SENT_MESSAGE_MEDIA_GROUPED,
            RECEIVED_MESSAGE_MEDIA_GROUPED,
            RECEIVED_MESSAGE_TPL,
            RECEIVED_MESSAGE_PHOTO,
            RECEIVED_MESSAGE_DOWNLOAD_BUTTON,
            SENT_MESSAGE_PHOTO,
            SENT_MESSAGE_PHOTO_CAPTION_TPL,
            SENT_MESSAGE_CLOCK_ICON,
            SENT_MESSAGE_VIDEO,
            SENT_MESSAGE_VIDEO_PLAY_ICON,
            SENT_MESSAGE_AUDIO,
            SENT_MESSAGE_GIF,
            SENT_MESSAGE_GIF_PLAY_ICON,
            SENT_MESSAGE_DELIVERY_INFO,
            ACTION_BAR_MENU,
            EDIT_BUTTON,
            EDIT_PREVIEW_ABOVE_INPUT_BAR,
            EDIT_MESSAGE_INPUT_BAR,
            DELETE_BUTTON,
            CONFIRM_DELETE_POP_UP,
            DELETE_FROM_ME,
            DELETE_FROM_EVERYONE,
            OK_DELETE_FROM_ME,
            CANCEL_DELETE,
            UNDO_DELETE_FROM_ME_BAR,
            UNDO_DELETE_COUNTER,
            UNDO_DELETE_1_MESSAGE_TEXT,
            UNDO_DELETE_2_MESSAGES_TEXT,
            UNDO_DELETE_BUTTON,
            UNDO_DELETE_BUTTON_TAP,
            CONTACT_INFO_PLACE_HOLDER,
            CONTACT_INFO_SCREEN_ACTIVITY,
            CONTACT_INFO_SCREEN_BACK_BUTTON,
            CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON,
            ATTACHMENT_MENU_BUTTON,
            ATTACHMENT_MENU_BAR,
            ATTACHMENT_MENU_BAR_TOUCH_OUTSIDE,
            ATTACHMENT_MENU_GALLERY,
            ATTACHMENT_MENU_GALLERY_VIDEOS,
            ATTACHMENT_MENU_GALLERY_PHOTOS,
            ATTACHMENT_MENU_GALLERY_SELECT_VIDEO,
            ATTACHMENT_MENU_GALLERY_SELECT_PHOTO,
            ATTACHMENT_MENU_GALLERY_NEXT_BUTTON,
            WIFI_DISABLED_CONNECTION_POP_UP,
            WIFI_POP_UP_OK_BUTTON,
            ADD_STAR_TO_MESSAGE,
            STAR_ON_MESSAGE_BUBBLE,
            RECORD_AUDIO_BUTTON,
            THREE_DOTS_BUTTON,
            SECRET_MESSAGE_BUTTON,
            SECRET_TIME_PICKER,
            SECRET_TIMER_SET_TIME,
            SECRET_MESSAGE_DISABLE,
            SECRET_MESSAGE_DISABLED_INFO,
            SECRET_TIMER_APPLY_BUTTON,
            SECRET_MESSAGE_COUNTER,
            CLEAR_CHAT_BUTTON,
            CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE,
            CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE_DELETE_BUTTON,
            CLEAR_CHAT_POP_UP_OK_BUTTON,
            CLEAR_CHAT_POP_UP_CANCEL_BUTTON,
            EMPTY_CHAT_SCREEN_POINT,
            MESSAGE_BUBBLE_ON_SCREEN,
            PIN_MESSAGE_BUTTON,
            UNPIN_MESSAGE_BUTTON,
            UNPIN_MESSAGE_PIN_BAR,
            UNPIN_ALL_MESSAGES_PIN_BAR,
            PIN_ICON_ON_SENT_MESSAGE,
            PINNED_MESSAGE_IN_PIN_BAR,
            PINNED_MESSAGE_BAR,
            YOU_PINNED_INFO_MESSAGE,
            ENCRYPTED_CHAT_INFO_MESSAGE;

    public ChatScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    private static String getChatNameByXpathName(String chat_name) {
        return CHAT_WITH_NAME_TPL.replace("{CHAT_NAME}", chat_name);
    }
    private static String getSentMessageByXpathName(String sent_message_name) {
        return SENT_MESSAGE_BUBBLE_TPL.replace("{SENT_MESSAGE}", sent_message_name);
    }
    private static String getReceivedMessageByXpathName(String received_message_name) {
        return RECEIVED_MESSAGE_TPL.replace("{RECEIVED_MESSAGE}", received_message_name);
    }
    private static String getSentPhotoCaptionMessageByXpathName(String sent_photo_caption) {
        return SENT_MESSAGE_PHOTO_CAPTION_TPL.replace("{CAPTION}", sent_photo_caption);
    }

    public void clearChat() {
        this.waitForElementAndClick(
                THREE_DOTS_BUTTON,
                "Can't tap and open menu",
                20);
        this.waitForElementAndClick(
                CLEAR_CHAT_BUTTON,
                "Can't tap on Clear button",
                20);
        if(isElementPresent(CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE)){
            this.waitForElementAndClick(CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE,
                    "Can't find and tap on Clear Starred messages pop-up",
                    25);
            this.waitForElementAndClick(CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE_DELETE_BUTTON,
                    "Can't tap on Delete button",
                    25);
            this.waitForElementNotPresent(
                    MESSAGE_BUBBLE_ON_SCREEN,
                    "Chat is not cleared",
                    25);
            Assert.assertFalse(isElementPresent(MESSAGE_BUBBLE_ON_SCREEN));
            this.waitForElementPresent(
                    ENCRYPTED_CHAT_INFO_MESSAGE,
                    "Not e2e info message in chat",
                    25);
        } else {
            this.waitForElementAndClick(
                    CLEAR_CHAT_POP_UP_OK_BUTTON,
                    "Can't tap OK button",
                    20);
            this.waitForElementNotPresent(
                    MESSAGE_BUBBLE_ON_SCREEN,
                    "Chat is not cleared",
                    25);
            Assert.assertFalse(isElementPresent(MESSAGE_BUBBLE_ON_SCREEN));
            this.waitForElementPresent(
                    ENCRYPTED_CHAT_INFO_MESSAGE,
                    "Not e2e info message in chat",
                    25);
        }
    }
    public void setSecretMessageTimer() {
        this.waitForElementAndClick(THREE_DOTS_BUTTON,
                "Can't tap on 3-dots button",
                25);
        this.waitForElementAndClick(SECRET_MESSAGE_BUTTON,
                "Can't find and tap Secret message button",
                25);
        this.waitForElementAndClick(SECRET_TIME_PICKER, "Can't set timer", 25);
        this.waitForElementAndClick(SECRET_TIMER_APPLY_BUTTON,
                "Can't apply secret message timer",
                25);
    }

    public void disableSecretMessage() {
        this.waitForElementAndClick(THREE_DOTS_BUTTON,
                "Can't tap on 3-dots button",
                25);
        this.waitForElementAndClick(SECRET_MESSAGE_BUTTON,
                "Can't find and tap Secret message button",
                25);
        Assert.assertTrue(isElementPresent(SECRET_MESSAGE_DISABLED_INFO));
    }

    public void assertSecretMessageSent(){
        Assert.assertTrue(isElementPresent(SECRET_MESSAGE_COUNTER));
    }

        public void waitForChatName(String chat_name) {
            String chat_xpath = getChatNameByXpathName(chat_name);
            this.waitForElementPresent(
                    (chat_xpath),
                    "Cannot find chat " + chat_name,
                    15
            );
        }
    public void waitForSentMessageWithName(String sent_message_name) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message_name);
        this.waitForElementPresent(
                (sent_message_xpath),
                "Cannot find message " + sent_message_name,
                15
        );
    }
    public void waitForSentPhotoWitCaption(String sent_photo_caption) {
        String sent_photo_xpath = getSentPhotoCaptionMessageByXpathName(sent_photo_caption);
        this.waitForElementPresent(
                (sent_photo_xpath),
                "Cannot find message " + sent_photo_caption,
                15
        );
    }

    public void waitForSentMessageWithNameIsNotDisplayed(String sent_message_name) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message_name);
        this.waitForElementNotPresent(
                (sent_message_xpath),
                "Cannot find message " + sent_message_name,
                15
        );
    }


    public void openChatWithName(String chat_name) {
        String chat_xpath = getChatNameByXpathName(chat_name);
        this.waitForElementAndClick(
                (chat_xpath),
                "Cannot open chat " + chat_name,
                15
        );
    }
    public void longPressAndDeleteSentMessageFromMe(String sent_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.deleteMessageFromMe();
    }

    public void longPressAndDeleteSentMessageFromEveryone(String sent_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.deleteMessageFromEveryOne();
    }

    public void longPressAndDeleteReceivedMessageMe(String received_message) {
        this.waitForSentMessageWithName(received_message);
        String received_message_xpath = getReceivedMessageByXpathName(received_message);
        this.longPressAction(received_message_xpath);
        this.deleteMessageFromMe();
    }
    public void longPressAndAddStarToSentMessage(String sent_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getReceivedMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.addStarToMessage();
        Assert.assertTrue(isElementPresent(STAR_ON_MESSAGE_BUBBLE));
    }
    public void assertStarIconIsDisplayedOnMessage() {
        Assert.assertTrue(isElementPresent(STAR_ON_MESSAGE_BUBBLE));

    }

    public void deleteMessageFromMe() {
        this.waitForElementPresent(
                ACTION_BAR_MENU,
                "Action menu is not displayed",
                15
        );
        this.waitForElementAndClick(
                DELETE_BUTTON,
                "Can't tap on Delete button",
                15
        );
        this.waitForElementPresent(
                CONFIRM_DELETE_POP_UP,
                "Can't find confirmation pop-up",
                15
        );
        this.waitForElementAndClick(
                DELETE_FROM_ME,
                "Can't select Delete from me",
                15
        );
        this.waitForElementAndClick(
                OK_DELETE_FROM_ME,
                "Can't tap on OK button",
                15
        );
        Assert.assertFalse(isElementPresent(CONFIRM_DELETE_POP_UP));
    }

    public void deleteMessageFromEveryOne() {
        this.waitForElementPresent(
                ACTION_BAR_MENU,
                "Action menu is not displayed",
                15
        );
        this.waitForElementAndClick(
                DELETE_BUTTON,
                "Can't tap on Delete button",
                15
        );
        this.waitForElementPresent(
                CONFIRM_DELETE_POP_UP,
                "Can't find confirmation pop-up",
                15
        );
        this.waitForElementAndClick(
                DELETE_FROM_EVERYONE,
                "Can't select Delete from me",
                15
        );
        this.waitForElementAndClick(
                OK_DELETE_FROM_ME,
                "Can't tap on OK button",
                15
        );
        Assert.assertFalse(isElementPresent(CONFIRM_DELETE_POP_UP));
    }

    public void isUndoPopUpDisplayed() {
        screenshot(this.takeScreenshot("undo_bar"));
        this.waitForElementPresent(
                UNDO_DELETE_FROM_ME_BAR,
                "Undo pop-up is missing",
                15
        );
//        this.waitForElementPresent(
//                UNDO_DELETE_COUNTER,
//                "Undo counter is missing",
//                15
//        );
        this.waitForElementPresent(
                UNDO_DELETE_1_MESSAGE_TEXT,
                "Undo text is missing",
                15
        );
        this.waitForElementPresent(
                UNDO_DELETE_BUTTON,
                "Undo button is missing",
                15
        );
    }
    public void assertUndoPopUpIsNotDisplayed() {
        this.waitForElementNotPresent(
                UNDO_DELETE_FROM_ME_BAR,
                "Undo pop-up is still displayed",
                15
        );
        screenshot(this.takeScreenshot("undo_is_hidden"));
    }
    public void undoRestoreDeletedMessageFromMe(String sent_message) {
        String deleted_message = getSentMessageByXpathName(sent_message);
        screenshot(this.takeScreenshot("message_deleted"));
        this.waitForElementNotPresent(
                deleted_message,
                "Message is not deleted",
                15
        );
        this.waitForElementAndClick(
                UNDO_DELETE_BUTTON,
                "Can't tap on the Undo button",
                15
        );
        screenshot(this.takeScreenshot("message_restored"));
        this.waitForElementPresent(
                deleted_message,
                "Message is not restored",
                15
        );
    }
    public void undoRestoreDeletedMessagesFromMe(String sent_message, String received_message) {
        String deleted_message1 = getSentMessageByXpathName(sent_message);
        String deleted_message2 = getReceivedMessageByXpathName(received_message);
        screenshot(this.takeScreenshot("messages_deleted"));
        this.waitForElementNotPresent(
                deleted_message1,
                "Message is not deleted",
                15
        );
        this.waitForElementNotPresent(
                deleted_message2,
                "Message is not deleted",
                15
        );
        this.waitForElementAndClick(
                UNDO_DELETE_BUTTON,
                "Can't tap on the Undo button",
                15
        );
        screenshot(this.takeScreenshot("messages_restored"));
        this.waitForElementPresent(
                deleted_message1,
                "Message is not restored",
                15
        );
        this.waitForElementPresent(
                deleted_message2,
                "Message is not restored",
                15
        );
    }

    public void deleteSeveralMessagesFromMe(String sent_message, String received_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.waitForSentMessageWithName(received_message);
        String received_message_xpath = getReceivedMessageByXpathName(received_message);
        this.longPressAction(sent_message_xpath);
        this.waitForElementAndClick(
                received_message_xpath,
                "Can't find and select received message",
                15
        );
        this.deleteMessageFromMe();
        Assert.assertFalse(isElementPresent(sent_message_xpath));
        Assert.assertFalse(isElementPresent(received_message_xpath));
    }
    public void isUndoPopUpDisplayedForSeveralMassages() {
        screenshot(this.takeScreenshot("undo_bar"));
        this.waitForElementPresent(
                UNDO_DELETE_FROM_ME_BAR,
                "Undo pop-up is missing",
                15
        );
        this.waitForElementPresent(
                UNDO_DELETE_2_MESSAGES_TEXT,
                "Undo text is missing",
                15
        );
        this.waitForElementPresent(
                UNDO_DELETE_BUTTON,
                "Undo button is missing",
                15
        );
    }
    public void tapOnInputBarAndSendMessage(String sent_message) {
        this.waitForElementAndClick(
                INPUT_BAR_FIELD,
                "Can't tap on input bar",
                15
        );
        this.waitForElementAndSendKeys(
                INPUT_BAR_FIELD,
                sent_message,
                15
        );
        this.waitForElementAndClick(
                SEND_MESSAGE_BUTTON,
                "Can't tap on Send button",
                15
        );
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
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).index(0)).scrollIntoView(new UiSelector().description(\"Photo\").instance(12))"));
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
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).index(0)).scrollIntoView(new UiSelector().description(\"1000003969\"))"));
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
    public void openInfoScreen() {
        this.waitForElementAndClick(
                CONTACT_INFO_PLACE_HOLDER,
                "Can't find and tap on info area",
                15
        );
        this.waitForElementPresent(
                CONTACT_INFO_SCREEN_ACTIVITY,
                "Info screen is not opened",
                15
        );
    }
    public void closeInfoScreen() {
        this.waitForElementAndClick(
                CONTACT_INFO_SCREEN_BACK_BUTTON,
                "Can't find and tap on the Back button",
                15
        );
        this.waitForElementPresent(
                CONTACT_INFO_PLACE_HOLDER,
                "Chat screen is not opened",
                15
        );
    }
    public void confirmWifiPopUp() {
        if (isElementPresent(WIFI_DISABLED_CONNECTION_POP_UP)) {
            this.waitForElementPresent(
                    WIFI_DISABLED_CONNECTION_POP_UP,
                    "Pop up is missing",
                    15
            );
            this.waitForElementAndClick(
                    WIFI_POP_UP_OK_BUTTON,
                    "Can't tap on OK button",
                    15
            );
        } else {
            System.out.println("Pop up is not displayed");
        }
    }
    public void sendMessageIfNeeded(String send_message, String sent_message) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        if (isElementPresent(sent_message_xpath)) {
            System.out.println("Message already sent and present in chat");
        } else {
            this.tapOnInputBarAndSendMessage(send_message);
        }
    }

    public void selectPhotoMessageIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_PHOTO)) {
            System.out.println("Photo already sent and present in chat");
        } else {
            this.openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get((AppiumDriver) driver);
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        }
    }

    public void selectPhotoWithCaption(String sent_photo_caption, String caption_text) {
        String sent_photo_xpath = getSentPhotoCaptionMessageByXpathName(sent_photo_caption);
        if (isElementPresent(sent_photo_xpath)) {
            System.out.println("Photo with caption already displayed in chat");
        } else {
            this.openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get((AppiumDriver) driver);
            MediaEditScreenPageObject.addCaption(caption_text);
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        }
    }

    public void selectVideoMessageIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_VIDEO)) {
            System.out.println("Video already sent and present in chat");
        } else {
            this.openAttachMenuAndSelectVideo();
            MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get((AppiumDriver) driver);
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        }
    }
    public void selectGifMessageIfNeeded() {
        if (isElementPresent(SENT_MESSAGE_GIF)) {
            System.out.println("Gif already sent and present in chat");
        } else {
            this.openAttachMenuAndSelectVideo();
            MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get((AppiumDriver) driver);
            MediaEditScreenPageObject.tapGifButton();
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        }
    }

    public void recordAudioIfNeeded() {
        if(isElementPresent(SENT_MESSAGE_AUDIO)) {
            System.out.println("Audio message already sent and present in chat");
        } else {
            this.recordAudioMessage();
        }
    }

        public void addStarToMessage () {
            this.waitForElementPresent(
                    ACTION_BAR_MENU,
                    "Action menu is not displayed",
                    15
            );
            this.waitForElementAndClick(
                    ADD_STAR_TO_MESSAGE,
                    "Can't tap on Delete button",
                    15
            );
            this.waitForElementPresent(
                    STAR_ON_MESSAGE_BUBBLE,
                    "Can't find star on message bubble",
                    15
            );
        }
        public void waitForSentPhoto() {
            this.waitForElementPresent(
                    SENT_MESSAGE_PHOTO,
                    "Can't find sent photo",
                    15
            );
        }

        public void waitForSentVideo () {
            this.waitForElementPresent(
                    SENT_MESSAGE_VIDEO,
                    "Can't find sent video",
                    15
            );
        }
        public void openSentPhotoInFullScreen () {
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

    public void openSentPhotoWithCaptionInFullScreen (String sent_photo_caption) {
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
        public void openSentVideoInFullScreen () {
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
    public void openSentGroupOfMedia(String chat_name) {
        if(isElementPresent(SENT_MESSAGE_MEDIA_GROUPED)) {
            this.waitForElementAndClick(
                    SENT_MESSAGE_MEDIA_GROUPED,
                    "Can't open group of media",
                    25
            );
        } else {
            openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get((AppiumDriver) driver);
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
            openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
            openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
            openAttachMenuAndSelectPhoto();
            MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
            tapBackButtonOpenChatList();
            openChatWithName(chat_name);
            this.waitForElementAndClick(
                    SENT_MESSAGE_MEDIA_GROUPED,
                    "Can't open group of media",
                    25
            );
        }
    }

    public void openSentGifInFullScreen () {
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
    public void recordAudioMessage() {
        WebElement element = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.turkcell.bip:id/iv_chat_panel_mic']"));
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(), "duration", 3000));
    }

    public void tapBackButtonOpenChatList() {
        this.waitForElementAndClick(
        CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON,
                "Can't open chat list",
                20
        );
    }
    public void longPressAndAddStarSentPhoto() {
        if(isElementPresent(STAR_ON_MESSAGE_BUBBLE)) {
            System.out.println("Message is starred already");
        } else {
            this.longPressAction(SENT_MESSAGE_PHOTO);
            this.addStarToMessage();
            Assert.assertTrue(isElementPresent(STAR_ON_MESSAGE_BUBBLE));
        }
    }

    public void longPressAndPinSentMessage(String sent_message, String sent_text) {
            String sent_message_xpath = getSentMessageByXpathName(sent_message);
            this.longPressAction(sent_message_xpath);
            this.selectPinButton();
            String pinned_message_text_in_bar = this.waitForElementAndGetText(
                    PINNED_MESSAGE_IN_PIN_BAR,
                    "Can't find text in pin bar",
                    20
            );
            Assert.assertEquals(pinned_message_text_in_bar, sent_text);
            Assert.assertTrue(isElementPresent(YOU_PINNED_INFO_MESSAGE));
    }
    public void longPressAndPinSecretMessage(String sent_message) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        screenshot(this.takeScreenshot("no_pin_for_secret_message"));
        Assert.assertFalse(isElementPresent(PIN_MESSAGE_BUTTON));
        this.waitForElementAndClick(sent_message_xpath, "Can't find and tap on sent message", 25);
    }

    public void isPinAvailable(String sent_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        Assert.assertTrue(isElementPresent(PIN_MESSAGE_BUTTON));

    }
    public void isMessageUnpinned() {
        this.waitForElementNotPresent(PINNED_MESSAGE_IN_PIN_BAR,
                "Pinned message is still displayed",
                25
        );
        Assert.assertFalse(isElementPresent(PINNED_MESSAGE_IN_PIN_BAR));

    }

    public void selectPinButton() {
        this.waitForElementPresent(
                ACTION_BAR_MENU,
                "Action menu is not displayed",
                15
        );
        this.waitForElementAndClick(
                PIN_MESSAGE_BUTTON,
                "Can't tap on Pin button",
                15
        );
    }

    public void longPressAndUnPinSentMessage(String sent_message, String sent_text) {
            String sent_message_xpath = getSentMessageByXpathName(sent_message);
            this.longPressAction(sent_message_xpath);
            this.selectUnPinMessage();
            this.waitForElementNotPresent(
                PINNED_MESSAGE_IN_PIN_BAR,
                "Can't find text in pin bar",
                20
        );
        Assert.assertFalse(isElementPresent(PINNED_MESSAGE_IN_PIN_BAR));
    }

    public void selectUnPinMessage () {
        this.waitForElementPresent(
                ACTION_BAR_MENU,
                "Action menu is not displayed",
                15
        );
        this.waitForElementAndClick(
                UNPIN_MESSAGE_BUTTON,
                "Can't tap on Delete button",
                15
        );
        this.waitForElementNotPresent(
                PIN_ICON_ON_SENT_MESSAGE,
                "Pin is displayed on message bubble",
                15
        );
    }
    public void longPressPinBarAndUnPinSentMessage() {
        this.waitForElementPresent(
                PINNED_MESSAGE_IN_PIN_BAR,
                "Pinned message is displayed in pin bar",
                25
        );
        this.longPressAction(PINNED_MESSAGE_IN_PIN_BAR);
        this.waitForElementAndClick(
                UNPIN_MESSAGE_PIN_BAR,
                "Can't tap on Unpin button",
                25
                );
        Assert.assertFalse(isElementPresent(PINNED_MESSAGE_IN_PIN_BAR));
        Assert.assertFalse(isElementPresent(PIN_ICON_ON_SENT_MESSAGE));
    }

    public void tapOnPinnedMessageBarToShowPinnedMessage(String sent_text, String sent_text_new){
        String pinned1 = this.waitForElementAndGetText(PINNED_MESSAGE_IN_PIN_BAR,
                "Can't get text for the pinned message bar",
                20
        );
        this.waitForElementAndClick(PINNED_MESSAGE_IN_PIN_BAR,
                "Can't tap on the pinned message bar",
                20
        );
        Assert.assertEquals(pinned1, sent_text_new);
        String pinned = this.waitForElementAndGetText(PINNED_MESSAGE_IN_PIN_BAR,
                "Can't get text for the pinned message bar",
                20
        );
        this.waitForElementAndClick(PINNED_MESSAGE_IN_PIN_BAR,
                "Can't tap on the pinned message bar",
                20
        );
        Assert.assertEquals(pinned, sent_text);
    }

    public void editPinnedMessage(String sent_message, String edited_message, String edited_text) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.waitForElementPresent(sent_message_xpath,
                "Can't find sent message",
                25
                );
        this.longPressAction(sent_message_xpath);
        this.waitForElementPresent(ACTION_BAR_MENU,
                "Action bar is missing",
                25);
        this.waitForElementAndClick(EDIT_BUTTON,
                "Can't tap on the Edit button",
                25);
        this.waitForElementPresent(EDIT_PREVIEW_ABOVE_INPUT_BAR,
                "Can't find edit preview",
                20);
        String before_edit = this.waitForElementAndGetText(EDIT_MESSAGE_INPUT_BAR,
                "Can't get text from input bar for edited message",
                20);
        this.waitForElementAndSendKeys(EDIT_MESSAGE_INPUT_BAR,
                edited_text,
                25);
        this.waitForElementAndClick(SEND_MESSAGE_BUTTON,
                "Can't tap on the Send button",
                25);

        this.waitForSentMessageWithName(edited_message);
        String edited_message_in_pin_bar = this.waitForElementAndGetText(PINNED_MESSAGE_IN_PIN_BAR,
                "Can't get text from pinned message bar",
                25);
        Assert.assertEquals(edited_text, edited_message_in_pin_bar);
    }
}
