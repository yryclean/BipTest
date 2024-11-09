package tests;

import lib.CoreTestCase;
import lib.ui.ChatScreenPageObject;
import lib.ui.MessagesTabPageObject;
import lib.ui.factories.ChatScreenPageObjectFactory;
import lib.ui.factories.MessagesTabPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.Description;

public class UndoDeleteTests extends CoreTestCase {
    private static final String
            chat_name = "Yuriy Chistyakov",
            sent_message = "Sent message Test1",
            sent_message_new = "Sent message Test2",
            send_message = "Test1",
            send_message_new = "Test2",
            received_message = "Received message Test1";

    @Test
    @Description("Undo pop-up appears on chat screen for sent messages")
    public void testUndoPopUpIsDisplayedForSentMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.assertUndoPopUpIsNotDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Undo pop-up appears on chat screen for received messages")
    //precondition: message is received in chat before step "received_message"
    public void testUndoPopUpIsDisplayedForReceivedMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.longPressAndDeleteReceivedMessage(received_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.assertUndoPopUpIsNotDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Message is restored when tap Undo button")
    public void testUndoRestoreDeletedMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessage(sent_message);
        this.closeApp();
    }
    @Test
    @Description("Undo pop-up appears for multiple messages")
    public void testUndoPopUpisDisplayedForMultipleMessages() {
        //precondition: message is received in chat before test "received_message"
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.deleteSeveralMessages(sent_message, received_message);
        ChatScreenPageObject.isUndoPopUpDisplayedForSeveralMassages();
        this.closeApp();
    }
    @Test
    @Description("Multiple messages are restored when tap Undo button")
    public void testUndoRestoreDeletedMultipleMessages() {
        //precondition: message is received in chat before test "received_message"
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.deleteSeveralMessages(sent_message, received_message);
        ChatScreenPageObject.isUndoPopUpDisplayedForSeveralMassages();
        ChatScreenPageObject.undoRestoreDeletedMessages(sent_message, received_message);
        this.closeApp();
    }
    @Test
    @Description("Undo pop-up disappears when timer is finished")
    public void testUndoPopUpDissapearsAfterTimerFinished() {
        //precondition: message is received in chat before test "received_message"
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.deleteSeveralMessages(sent_message, received_message);
        ChatScreenPageObject.isUndoPopUpDisplayedForSeveralMassages();
        ChatScreenPageObject.assertUndoPopUpIsNotDisplayed();
        this.closeApp();
    }
    @Test
    @Description("Undo pop-up remains visible if the user touches different parts of the screen/open keyboard")
    public void testUndoPopUpRemainsAfterSendingMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.tapOnInputBarAndSendMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        this.closeApp();
    }
    @Test
    @Description("Undo pop-up remains visible if the user touches different parts of the screen/open keyboard")
    public void testUndoPopUpRemainsAfterOpenAttachMenu() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.tapOnInputBarAndOpenAttachMenu();
        ChatScreenPageObject.closeAttachMenuBar();
        ChatScreenPageObject.isUndoPopUpDisplayed();
        this.closeApp();
    }
    @Test
    @Description("Undo pop-up disappears when navigate to another screen_1")
    public void testUndoPopUpDisappearsOpenOtherScreen() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.openInfoScreen();
        ChatScreenPageObject.closeInfoScreen();
        ChatScreenPageObject.assertUndoPopUpIsNotDisplayed();
        this.closeApp();

    }
    @Test
    @Description("When there is no Internet connection message is restored when tap Undo button")
    public void testUndoRestoreMessageWhenNoInternetConnection() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        this.enableAirplaneMode();//android only
        ChatScreenPageObject.confirmWifiPopUp();
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessage(sent_message);
        this.enableAllInternetConnection();
        this.closeApp();
    }

    @Test
    @Description("Undo action for starred messages and messages with reactions")
    public void testUndoDeleteStarredMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndAddStarToSentMessage(sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessage(sent_message);
        ChatScreenPageObject.assertStarIconIsDisplayedOnMessage();
        this.closeApp();
    }

    @Test
    @Description("iOS_Undo pop-up after background")
    public void testUndoPopUpIsDisplayedAfterBackgroundiOS() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        this.backgroundApp(2);
        ChatScreenPageObject.undoRestoreDeletedMessage(sent_message);
        this.closeApp();
    }
    @Test
    @Description("Android_Undo pop-up after background")
    public void testUndoPopUpIsDisplayedAfterBackgroundAndroid() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        this.backgroundApp(2);
        ChatScreenPageObject.assertUndoPopUpIsNotDisplayed();
        this.closeApp();
    }
    @Test
    @Description("Delete from me action is used repeatedly")
    public void testUndoPopUpIsDisplayedForMessagesDeletedOneByOne() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.sendMessageIfNeeded(send_message,sent_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.sendMessageIfNeeded(send_message_new, sent_message_new);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message_new);
        ChatScreenPageObject.undoRestoreDeletedMessage(sent_message_new);
        this.closeApp();
    }
}
