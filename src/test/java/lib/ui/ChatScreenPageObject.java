package lib.ui;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import lib.ui.factories.MediaEditScreenPageObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public abstract class ChatScreenPageObject extends MainPageObject {
    protected static String
            CHAT_WITH_NAME_TPL,
            CHAT_WITH_NAME,
            INPUT_BAR_FIELD,
            SEND_MESSAGE_BUTTON,
            SENT_MESSAGE_BUBBLE_TPL,
            RECEIVED_MESSAGE_TPL,
            SENT_MESSAGE_PHOTO,
            SENT_MESSAGE_CLOCK_ICON,
            SENT_MESSAGE_VIDEO,
            SENT_MESSAGE_VIDEO_PLAY_ICON,
            SENT_MESSAGE_AUDIO,
            SENT_MESSAGE_GIF,
            SENT_MESSAGE_GIF_PLAY_ICON,
            SENT_MESSAGE_DELIVERY_INFO,
            ACTION_BAR_MENU,
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
            CLEAR_CHAT_BUTTON,
            CLEAR_CHAT_POP_UP_OK_BUTTON,
            CLEAR_CHAT_POP_UP_CANCEL_BUTTON;


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
    public void longPressAndDeleteSentMessage(String sent_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.deleteMessage();
    }

    public void longPressAndDeleteReceivedMessage(String received_message) {
        this.waitForSentMessageWithName(received_message);
        String received_message_xpath = getReceivedMessageByXpathName(received_message);
        this.longPressAction(received_message_xpath);
        this.deleteMessage();
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

    public void deleteMessage() {
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
    public void undoRestoreDeletedMessage(String sent_message) {
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
    public void undoRestoreDeletedMessages(String sent_message, String received_message) {
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

    public void deleteSeveralMessages(String sent_message, String received_message) {
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
        this.deleteMessage();
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
}
