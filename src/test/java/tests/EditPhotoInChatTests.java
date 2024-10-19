package tests;

import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.ui.ChatScreenPageObject;
import lib.ui.MediaEditScreenPageObject;
import lib.ui.SharedMediaScreenPageObject;
import lib.ui.factories.ChatScreenPageObjectFactory;
import lib.ui.factories.MediaEditScreenPageObjectFactory;
import lib.ui.factories.SharedMediaPageObjectFactory;
import org.junit.Test;
import org.springframework.context.annotation.Description;

public class EditPhotoInChatTests extends CoreTestCase {
    private static final String
            chat_name = "Yuriy Chistyakov",
            sent_message = "Sent message Test1",
            sent_message_new = "Sent message Test2",
            send_message = "Test1",
            send_message_new = "Test2",
            received_message = "Received message Test1";

    @Test
    @Description("Edit button available for photo on full screen preview")
    public void testEditButtonAvailableOnSharedMediaScreen() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.isEditButtonDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Media edit screen is opened after tap on the Edit button")
    public void testMediaEditScreenOpenedOnEditTap() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.tapOnEditPhotoButton();
        MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get(driver);
        MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        ChatScreenPageObject.waitForSentPhoto();
        this.closeApp();
    }
}
