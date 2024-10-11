package tests;

import lib.CoreTestCase;
import lib.ui.ChatScreenPageObject;
import lib.ui.factories.ChatScreenPageObjectFactory;
import org.junit.Test;
import org.springframework.context.annotation.Description;

public class UndoDeleteTests extends CoreTestCase {
    private static final String
            chat_name = "Yuriy Chistyakov",
            sent_message = "Sent message Test1",
            send_message = "Test1",
            received_message = "Received message Test1";

    @Test
    @Description("Undo pop-up appears on chat screen for sent messages")
    public void testUndoPopUpIsDisplayedForSentMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.assertUndoPopUpIsNotDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Undo pop-up appears on chat screen for received messages")
    //precondition: message is received in chat before test "received_message"
    public void testUndoPopUpIsDisplayedForReceivedMessage() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.tapOnInputBarAndOpenAttachMenu();
        ChatScreenPageObject.closeAttachMenuBar();
        ChatScreenPageObject.isUndoPopUpDisplayed();
        this.closeApp();
    }
    @Test
    @Description("Undo pop-up disappears when navigate to another screen")
    public void testUndoPopUpDisappearsOpenOtherScreen() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.tapOnInputBarAndSendMessage(send_message);
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
        ChatScreenPageObject.openChatWithName(chat_name);
        this.enableAirplaneMode();//android only
        ChatScreenPageObject.confirmWifiPopUp();
        ChatScreenPageObject.sendMessageIfNeeded(send_message);
        ChatScreenPageObject.longPressAndDeleteSentMessage(sent_message);
        ChatScreenPageObject.isUndoPopUpDisplayed();
        ChatScreenPageObject.undoRestoreDeletedMessage(sent_message);
        this.enableAllInternetConnection();
        this.closeApp();
    }
}
