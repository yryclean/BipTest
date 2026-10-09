package tests;

import lib.ChatTestCase;
import lib.ui.ChatScreenPageObject;
import lib.ui.MessagesTabPageObject;
import lib.ui.factories.ChatScreenPageObjectFactory;
import lib.ui.factories.MessagesTabPageObjectFactory;
import org.junit.Test;
import io.qameta.allure.Description;

public class UndoDeleteFromMeTests extends ChatTestCase {
    // received_message is a fixture the other party has to produce, so it
    // cannot be tagged; everything this suite types itself is.
    private static final String
            chat_name = "Yury Chistyakov",
            received_message = "Test1";

    private final String
            message = messageText("Test1"),
            message_new = messageText("Test2");

    @Test
    @Description("Undo pop-up appears on chat screen for sent messages")
    public void testUndoPopUpIsDisplayedForSentMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.assertUndoPopUpNotDisplayed();
    }

    @Test
    @Description("Undo pop-up appears on chat screen for received messages")
    //precondition: message is received in chat before step "received_message"
    public void testUndoPopUpIsDisplayedForReceivedMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.longPressAndDeleteReceivedMessageMe(received_message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.assertUndoPopUpNotDisplayed();
    }

    @Test
    @Description("Message is restored when tap Undo button")
    public void testUndoRestoreDeletedMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessageFromMe(message);
    }
    @Test
    @Description("Undo pop-up appears for multiple messages")
    public void testUndoPopUpisDisplayedForMultipleMessages() {
        //precondition: message is received in chat before test "received_message"
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.deleteSeveralMessagesFromMe(message, received_message);
        ChatScreenPageObject.assertUndoPopUpDisplayedForSeveralMessages();
    }
    @Test
    @Description("Multiple messages are restored when tap Undo button")
    public void testUndoRestoreDeletedMultipleMessages() {
        //precondition: message is received in chat before test "received_message"
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.deleteSeveralMessagesFromMe(message, received_message);
        ChatScreenPageObject.assertUndoPopUpDisplayedForSeveralMessages();
        ChatScreenPageObject.undoRestoreDeletedMessagesFromMe(message, received_message);
    }
    @Test
    @Description("Undo pop-up disappears when timer is finished")
    public void testUndoPopUpDissapearsAfterTimerFinished() {
        //precondition: message is received in chat before test "received_message"
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.deleteSeveralMessagesFromMe(message, received_message);
        ChatScreenPageObject.assertUndoPopUpDisplayedForSeveralMessages();
        ChatScreenPageObject.assertUndoPopUpNotDisplayed();
    }
    @Test
    @Description("Undo pop-up remains visible if the user touches different parts of the screen/open keyboard")
    public void testUndoPopUpRemainsAfterSendingMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.tapOnInputBarAndSendMessage(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
    }
    @Test
    @Description("Undo pop-up remains visible if the user touches different parts of the screen/open keyboard")
    public void testUndoPopUpRemainsAfterOpenAttachMenu() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.tapOnInputBarAndOpenAttachMenu();
        ChatScreenPageObject.closeAttachMenuBar();
        ChatScreenPageObject.assertUndoPopUpDisplayed();
    }
    @Test
    @Description("Undo pop-up disappears when navigate to another screen_1")
    public void testUndoPopUpDisappearsOpenOtherScreen() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.openInfoScreen();
        ChatScreenPageObject.closeInfoScreen();
        ChatScreenPageObject.assertUndoPopUpNotDisplayed();

    }
    @Test
    @Description("When there is no Internet connection message is restored when tap Undo button")
    public void testUndoRestoreMessageWhenNoInternetConnection() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        this.enableAirplaneMode();//android only
        ChatScreenPageObject.confirmWifiPopUp();
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessageFromMe(message);
        this.enableAllInternetConnection();
    }

    @Test
    @Description("Undo action for starred messages and messages with reactions")
    public void testUndoDeleteStarredMessage() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndAddStarToSentMessage(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessageFromMe(message);
        ChatScreenPageObject.assertStarIconIsDisplayedOnMessage();
    }

    @Test
    @Description("iOS_Undo pop-up after background")
    public void testUndoPopUpIsDisplayedAfterBackgroundiOS() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        this.backgroundApp(2);
        ChatScreenPageObject.undoRestoreDeletedMessageFromMe(message);
    }
    @Test
    @Description("Android_Undo pop-up after background")
    public void testUndoPopUpIsDisplayedAfterBackgroundAndroid() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        this.backgroundApp(2);
        ChatScreenPageObject.assertUndoPopUpNotDisplayed();
    }
    @Test
    @Description("Delete from me action is used repeatedly")
    public void testUndoPopUpIsDisplayedForMessagesDeletedOneByOne() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(message);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message);
        ChatScreenPageObject.assertUndoPopUpDisplayed();
        ChatScreenPageObject.sendMessageIfNeeded(message_new);
        ChatScreenPageObject.longPressAndDeleteSentMessageFromMe(message_new);
        ChatScreenPageObject.undoRestoreDeletedMessageFromMe(message_new);
    }
}
