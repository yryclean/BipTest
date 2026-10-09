package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

/**
 * Text messages: the input bar, waiting for a bubble to appear, and starring.
 * The edit locators live here too — the pin layer reuses them to edit a pinned
 * message.
 *
 * @see ChatScreenPageObject for the full layering.
 */
public abstract class ChatScreenMessagePageObject extends ChatScreenNavigationPageObject {

    protected static String
            INPUT_BAR_FIELD,
            SEND_MESSAGE_BUTTON,
            EDIT_BUTTON,
            EDIT_PREVIEW_ABOVE_INPUT_BAR,
            EDIT_MESSAGE_INPUT_BAR,
            ADD_STAR_TO_MESSAGE,
            STAR_ON_MESSAGE_BUBBLE,
            // Captured, no test uses them yet.
            SENT_MESSAGE_DELIVERY_INFO,
            EMPTY_CHAT_SCREEN_POINT;

    public ChatScreenMessagePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForSentMessageWithName(String sent_message_name) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message_name);
        this.waitForElementPresent(
                (sent_message_xpath),
                "Cannot find message " + sent_message_name,
                15
        );
    }

    public void waitForSentPhotoWitCaption(String caption_text) {
        String sent_photo_xpath = getSentPhotoCaptionMessageByXpathName(caption_text);
        this.waitForElementPresent(
                (sent_photo_xpath),
                "Cannot find message " + caption_text,
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

    public void sendMessageIfNeeded(String message_text) {
        String sent_message_xpath = getSentMessageByXpathName(message_text);
        if (isElementPresent(sent_message_xpath)) {
            System.out.println("Message already sent and present in chat");
        } else {
            this.tapOnInputBarAndSendMessage(message_text);
        }
    }

    public void addStarToMessage() {
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
}
