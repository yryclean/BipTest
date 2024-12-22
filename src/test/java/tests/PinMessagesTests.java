package tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import lib.CoreTestCase;
import lib.ui.ChatScreenPageObject;
import lib.ui.MessagesTabPageObject;
import lib.ui.factories.ChatScreenPageObjectFactory;
import lib.ui.factories.MessagesTabPageObjectFactory;
import org.junit.Test;

public class PinMessagesTests extends CoreTestCase {

    private static final String
            chat_name = "Yury",
            chat_secret = "TurkeyProd",
            group_chat_name = "YuryGroup",
            sent_message = "Sent message Test",
            sent_text = "Test",
            sent_new_message = "Sent message Test2",
            sent_text_new = "Test2",
            sent_edited_message = "Sent message Edited message :)",
            edited_text = "Edited message :)";


    @Test
    @Description("Pin option available in the p2p/group chat")
    public void testPinAvailableInP2pChat(){
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.waitForSentMessageWithName(sent_message);
        ChatScreenPageObject.isPinAvailable(sent_message);
        this.closeApp();
    }

    @Test
    @Description("Pin option available in the p2p/group chat")
    public void testPinAvailableInGroupChat(){
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(group_chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.waitForSentMessageWithName(sent_message);
        ChatScreenPageObject.isPinAvailable(sent_message);
        this.closeApp();
    }

    @Test
    @Description("Message can be pinned")
    public void testPinMessageInP2pChat(){
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.clearChat();
        this.closeApp();
    }

    @Test
    @Description("Message can be pinned")
    public void testPinMessageInGroupChat(){
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(group_chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.clearChat();
        this.closeApp();
    }

    @Test
    @Description("Pinned message can be Unpinned by long tap on the message on the chat screen")
    public void testUnpinMessageInChat() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndUnPinSentMessage(sent_message, sent_text);
        this.closeApp();
    }

    @Test
    @Description("Pinned message can be Unpinned by long tap on Pin bar at the top of conversation")
    public void testUnpinMessageInPinBar() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressPinBarAndUnPinSentMessage();
        this.closeApp();
    }

    @Test
    @Description("Pinned message becomes Unpinned if the original message was deleted by Delete from me option")
    public void testMessageUnpinnedAfterDeleteFromMe() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
        ChatScreenPageObject.isMessageUnpinned();
        this.closeApp();
    }

    @Test
    @Description("Pinned message becomes Unpinned if the original message was deleted by Delete from everyone option")
    public void testMessageUnpinnedAfterDeleteEveryone() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromEveryone(sent_message);
        this.closeApp();
    }

    @Test
    @Description("Pin several messages (one by one)")
    public void testPinSeveralMessages() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text_new, sent_new_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_new_message, sent_text_new);
        ChatScreenPageObject.clearChat();
        this.closeApp();

    }

    @Test
    @Description("Switching between pinned messages")
    public void testSwitchBetweenPinnedMessages() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text_new, sent_new_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_new_message, sent_text_new);
        ChatScreenPageObject.tapOnPinnedMessageBarToShowPinnedMessage(sent_text, sent_text_new);
        ChatScreenPageObject.clearChat();
        this.closeApp();
    }

    @Test
    @Description("Edit pinned message")
    public void testEditPinnedMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.editPinnedMessage(sent_message, sent_edited_message, edited_text);
        ChatScreenPageObject.clearChat();
        this.closeApp();
    }

    @Test
    @Description("Star pinned message")
    public void testStarPinnedMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndAddStarToSentMessage(sent_message);
        ChatScreenPageObject.clearChat();
        this.closeApp();
    }

    @Test
    @Description("Pin option isn't available for secret messages")
    @Attachment
    public void testNoPinOptionForSecretMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_secret);
        ChatScreenPageObject.setSecretMessageTimer();
        ChatScreenPageObject.tapOnInputBarAndSendMessage(sent_text);
        ChatScreenPageObject.assertSecretMessageSent();
        ChatScreenPageObject.longPressAndPinSecretMessage(sent_message);
        this.closeApp();
    }
}
