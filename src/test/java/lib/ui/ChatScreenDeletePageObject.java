package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Removing messages: delete from me / from everyone, the undo bar that follows,
 * and clearing the whole chat.
 *
 * @see ChatScreenPageObject for the full layering.
 */
public abstract class ChatScreenDeletePageObject extends ChatScreenMessagePageObject {

    protected static String
            DELETE_BUTTON,
            CONFIRM_DELETE_POP_UP,
            DELETE_FROM_ME,
            DELETE_FROM_EVERYONE,
            OK_DELETE_FROM_ME,
            UNDO_DELETE_FROM_ME_BAR,
            UNDO_DELETE_1_MESSAGE_TEXT,
            UNDO_DELETE_2_MESSAGES_TEXT,
            UNDO_DELETE_BUTTON,
            CLEAR_CHAT_BUTTON,
            CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE,
            CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE_DELETE_BUTTON,
            CLEAR_CHAT_POP_UP_OK_BUTTON,
            MESSAGE_BUBBLE_ON_SCREEN,
            ENCRYPTED_CHAT_INFO_MESSAGE,
            // Captured, no test uses them yet.
            CANCEL_DELETE,
            CLEAR_CHAT_POP_UP_CANCEL_BUTTON,
            UNDO_DELETE_COUNTER,
            UNDO_DELETE_BUTTON_TAP;

    public ChatScreenDeletePageObject(AppiumDriver driver)
    {
        super(driver);
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
        if (isElementPresent(CLEAR_CHAT_POP_UP_WITH_STARRED_MESSAGE)) {
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

    /**
     * Nothing here takes a screenshot on purpose. The undo bar lives about five
     * seconds and a screenshot costs one to two of them, so capturing the happy
     * path used to leave no time for the caller to reach the Undo button. The
     * failure case is still covered — CoreTestCase's rule shoots the screen and
     * the page source whenever a test fails.
     */
    public void assertUndoPopUpDisplayed() {
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

    public void assertUndoPopUpDisplayedForSeveralMessages() {
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

    public void assertUndoPopUpNotDisplayed() {
        this.waitForElementNotPresent(
                UNDO_DELETE_FROM_ME_BAR,
                "Undo pop-up is still displayed",
                15
        );
        screenshot(this.takeScreenshot("undo_is_hidden"));
    }

    /**
     * Undo comes first and the checks follow. The deletion itself is already
     * asserted by {@link #deleteMessageFromMe()}, so re-confirming it here only
     * burned a second of the bar's five, and the click is the one step that
     * cannot be retried once the bar is gone.
     */
    public void undoRestoreDeletedMessageFromMe(String sent_message) {
        String deleted_message = getSentMessageByXpathName(sent_message);
        this.clickUndo();
        this.waitForElementPresent(
                deleted_message,
                "Message is not restored",
                15
        );
        screenshot(this.takeScreenshot("message_restored"));
    }

    /**
     * The bar is both short lived and recycled while it counts down, so a
     * located Undo button can go stale between the find and the click. One
     * re-find covers that; a second miss means the bar really has expired.
     */
    private void clickUndo() {
        try {
            this.waitForElementAndClick(UNDO_DELETE_BUTTON, "Can't tap on the Undo button", 15);
        } catch (StaleElementReferenceException e) {
            this.waitForElementAndClick(UNDO_DELETE_BUTTON, "Can't tap on the Undo button", 5);
        }
    }

    public void undoRestoreDeletedMessagesFromMe(String sent_message, String received_message) {
        String deleted_message1 = getSentMessageByXpathName(sent_message);
        String deleted_message2 = getReceivedMessageByXpathName(received_message);
        this.clickUndo();
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
        screenshot(this.takeScreenshot("messages_restored"));
    }
}
