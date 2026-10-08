package tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import lib.ChatTestCase;
import lib.ui.ChatScreenPageObject;
import lib.ui.MessagesTabPageObject;
import lib.ui.factories.ChatScreenPageObjectFactory;
import lib.ui.factories.MessagesTabPageObjectFactory;
import org.junit.Test;

public class PinMessagesTests extends ChatTestCase {

    // Chat and contact names are real data on the device, so they stay literal.
    // Everything this suite types is tagged per test — see ChatTestCase.
    //
    // These tests used to end in clearChat(). That wiped the whole conversation,
    // including the incoming message UndoDeleteFromMeTests needs and cannot send
    // to itself, so the two suites could not survive the same run. A test that
    // pins now deletes its own messages instead, which unpins them and leaves
    // the rest of the history — and the other suites' fixtures — untouched.
    private static final String
            chat_name = "Yury Chistyakov",
            chat_secret = "TurkeyProd",
            group_chat_name = "YuryGroup",
            chat_name1 = "Turkey53";

    private final String
            sent_text = messageText("Test"),
            sent_message = sentBubble("Test"),
            sent_text_new = messageText("Test2"),
            sent_new_message = sentBubble("Test2"),
            edited_text = messageText("Edited message :)"),
            sent_edited_message = sentBubble("Edited message :)"),
            sent_text1 = messageText("Test1"),
            sent_message1 = sentBubble("Test1"),
            sent_text2 = messageText("Test2"),
            sent_message2 = sentBubble("Test2"),
            sent_text3 = messageText("Test3"),
            sent_message3 = sentBubble("Test3"),
            sent_text4 = messageText("Test4"),
            sent_message4 = sentBubble("Test4"),
            sent_text5 = messageText("Test5"),
            sent_message5 = sentBubble("Test5");


    @Test
    @Description("Pin option available in the p2p/group chat")
    public void testPinAvailableInP2pChat(){
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.waitForSentMessageWithName(sent_message);
        ChatScreenPageObject.isPinAvailable(sent_message);
    }

    @Test
    @Description("Pin option available in the p2p/group chat")
    public void testPinAvailableInGroupChat(){
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(group_chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.waitForSentMessageWithName(sent_message);
        ChatScreenPageObject.isPinAvailable(sent_message);
    }

    @Test
    @Description("Message can be pinned")
    public void testPinMessageInP2pChat(){
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
    }

    @Test
    @Description("Message can be pinned")
    public void testPinMessageInGroupChat(){
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(group_chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
    }

    @Test
    @Description("Pinned message can be Unpinned by long tap on the message on the chat screen")
    public void testUnpinMessageInChat() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndUnPinSentMessage(sent_message, sent_text);
    }

    @Test
    @Description("Pinned message can be Unpinned by long tap on Pin bar at the top of conversation")
    public void testUnpinMessageInPinBar() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressPinBarAndUnPinSentMessage();
    }

    @Test
    @Description("Pinned message becomes Unpinned if the original message was deleted by Delete from me option")
    public void testMessageUnpinnedAfterDeleteFromMe() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
        ChatScreenPageObject.isMessageUnpinned(sent_text);
    }

    @Test
    @Description("Pinned message becomes Unpinned if the original message was deleted by Delete from everyone option")
    public void testMessageUnpinnedAfterDeleteEveryone() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromEveryone(sent_message);
    }

    @Test
    @Description("Pin several messages (one by one)")
    public void testPinSeveralMessages() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text_new, sent_new_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_new_message, sent_text_new);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_new_message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
    }

    @Test
    @Description("Switching between pinned messages")
    public void testSwitchBetweenPinnedMessages() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text_new, sent_new_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_new_message, sent_text_new);
        ChatScreenPageObject.tapOnPinnedMessageBarToShowPinnedMessage(sent_text, sent_text_new);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_new_message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
    }

    @Test
    @Description("Edit pinned message")
    public void testEditPinnedMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.editPinnedMessage(sent_message, sent_edited_message, edited_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_edited_message);
    }

    @Test
    @Description("Star pinned message")
    public void testStarPinnedMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndAddStarToSentMessage(sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
    }

    @Test
    @Description("Pin option isn't available for secret messages")
    @Attachment
    public void testNoPinOptionForSecretMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_secret);
        ChatScreenPageObject.setSecretMessageTimer();
        ChatScreenPageObject.tapOnInputBarAndSendMessage(sent_text);
        ChatScreenPageObject.assertSecretMessageSent();
        ChatScreenPageObject.longPressAndPinSecretMessage(sent_message);
    }

    @Test
    @Description("Undo deleted pinned message")//undo timer must be set to 10-15 seconds at least
    public void testUndoDeletedPinnedMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessageFromMe(sent_message);
        ChatScreenPageObject.isMessageUnpinned(sent_text);
    }

    @Test
    @Description("Pin message with no Internet connection")
    public void testPinMessageNoInternet() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        this.enableAirplaneMode();//android only
        ChatScreenPageObject.confirmWifiPopUp();
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        this.enableAllInternetConnection();
    }

    @Test
    @Description("Pin message limit warning")
    public void testPinMessageLimitWarning(){
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text, sent_message);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message, sent_text);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text1, sent_message1);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message1, sent_text1);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text2, sent_message2);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message2, sent_text2);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text3, sent_message3);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message3, sent_text3);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text4, sent_message4);
        ChatScreenPageObject.longPressAndPinSentMessage(sent_message4, sent_text4);
        ChatScreenPageObject.sendMessageIfNeeded(sent_text5, sent_message5);
        ChatScreenPageObject.longPressAndPinSentMessageWhenLimitReached(sent_message5, sent_text5);
    }
}
