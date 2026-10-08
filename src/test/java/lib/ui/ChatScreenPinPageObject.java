package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

/**
 * Pinned messages: pinning, unpinning from both the action bar and the pin bar,
 * the replace-oldest-pin warning, and editing whatever is currently pinned.
 *
 * @see ChatScreenPageObject for the full layering.
 */
public abstract class ChatScreenPinPageObject extends ChatScreenSecretPageObject {

    protected static String
            PIN_MESSAGE_BUTTON,
            UNPIN_MESSAGE_BUTTON,
            UNPIN_MESSAGE_PIN_BAR,
            PIN_ICON_ON_SENT_MESSAGE,
            PINNED_MESSAGE_IN_PIN_BAR,
            PINNED_MESSAGE_IN_PIN_BAR_TPL,
            YOU_PINNED_INFO_MESSAGE,
            PIN_LIMIT_WARNING,
            PIN_LIMIT_WARNING_OK,
            // Captured, no test uses them yet.
            UNPIN_ALL_MESSAGES_PIN_BAR,
            PINNED_MESSAGE_BAR,
            PIN_LIMIT_WARNING_CANCEL;

    public ChatScreenPinPageObject(AppiumDriver driver)
    {
        super(driver);
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

    public void longPressAndPinSentMessageWhenLimitReached(String sent_message, String sent_text) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.selectPinButton();
        this.removePreviousPinnedMessage();
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
        this.openActionBarOverflow();
        screenshot(this.takeScreenshot("no_pin_for_secret_message"));
        Assert.assertFalse(isElementPresent(PIN_MESSAGE_BUTTON));
        this.closeActionBarOverflowAndDeselect(sent_message_xpath);
    }

    public void assertPinAvailable(String sent_message) {
        this.waitForSentMessageWithName(sent_message);
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.openActionBarOverflow();
        Assert.assertTrue(isElementPresent(PIN_MESSAGE_BUTTON));
        this.closeActionBarOverflowAndDeselect(sent_message_xpath);
    }

    /**
     * A check that only looks is still a check that selected a message and
     * opened a menu. Left behind, the next long press extends that selection
     * instead of starting a new one — and BiP does not offer the overflow for
     * more than one selected message, so the following test fails nowhere near
     * the method that caused it.
     */
    protected void closeActionBarOverflowAndDeselect(String sent_message_xpath) {
        driver.navigate().back();
        this.waitForElementAndClick(
                sent_message_xpath,
                "Can't tap the message to deselect it",
                15
        );
        this.waitForElementNotPresent(
                ACTION_BAR_MENU,
                "The message is still selected",
                15
        );
    }

    /**
     * Asserts that this one message left the pin bar, not that the bar is empty.
     * The bar belongs to the whole conversation, so "nothing is pinned" made the
     * test depend on every pin any other test — or any earlier run — left behind.
     */
    public void assertMessageUnpinned(String sent_text) {
        String pinned_text = getPinnedMessageInBarByText(sent_text);
        this.waitForElementNotPresent(pinned_text,
                "Message is still pinned in the pin bar",
                25
        );
        Assert.assertFalse(isElementPresent(pinned_text));
    }

    protected static String getPinnedMessageInBarByText(String sent_text) {
        return PINNED_MESSAGE_IN_PIN_BAR_TPL.replace("{TEXT}", sent_text);
    }

    public void selectPinButton() {
        this.openActionBarOverflow();
        this.waitForElementAndClick(
                PIN_MESSAGE_BUTTON,
                "Can't tap on Pin button",
                15
        );
    }

    /**
     * Pin and Unpin moved out of the action bar itself and into its overflow.
     * Returns only once the popup has something in it: the callers go straight
     * from here to a presence check, and an unsettled menu makes those checks
     * lie in both directions — a missing Pin, or an absent-looking one that is
     * merely still animating in.
     */
    protected void openActionBarOverflow() {
        this.waitForElementPresent(
                ACTION_BAR_MENU,
                "Action menu is not displayed",
                15
        );
        this.waitForElementAndClick(
                ACTION_BAR_MENU_MORE_OPTIONS,
                "Can't open the action bar overflow",
                15
        );
        this.waitForElementPresent(
                ACTION_BAR_MENU_ITEM,
                "The action bar overflow did not open",
                15
        );
    }

    public void longPressAndUnPinSentMessage(String sent_message, String sent_text) {
        String sent_message_xpath = getSentMessageByXpathName(sent_message);
        this.longPressAction(sent_message_xpath);
        this.selectUnPinMessage();
        this.assertMessageUnpinned(sent_text);
    }

    public void selectUnPinMessage() {
        this.openActionBarOverflow();
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

    public void tapOnPinnedMessageBarToShowPinnedMessage(String sent_text, String sent_text_new) {
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

    public void removePreviousPinnedMessage() {
        this.waitForElementPresent(PIN_LIMIT_WARNING,
                "Pin limit warning is missing",
                20);
        screenshot(this.takeScreenshot("pin_limit_warning"));
        this.waitForElementAndClick(PIN_LIMIT_WARNING_OK,
                "Can't tap on Continue button",
                25);
        this.waitForElementNotPresent(PIN_LIMIT_WARNING,
                "Pin warning is not closed",
                20);
        screenshot(this.takeScreenshot("removed_previous_pin"));
    }
}
