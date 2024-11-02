package tests;

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
    @Description("Sending photo opened in full screen preview from chat")
    public void testMediaEditScreenOpenedOnEditTap() {
        this.openApp();
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

    @Test
    @Description("Edit button is not available for video/gif/doc/live photo/audio on full screen preview")
    public void testNoEditButtonIfNotAPhotoOpened() throws InterruptedException {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectVideoMessageIfNeeded();
        ChatScreenPageObject.openSentVideoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.tapOnVideo();
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        SharedMediaScreenPageObject.tapOnEditPhotoButton();
//        this.backgroundApp(2);
        this.closeApp();
    }

    @Test
    @Description("Edit button available for photo on full screen preview while switching between other photos using swipe")
    public void testEditButtonSwipeBetweenPhotosOnSharedScreen() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.waitForSentPhoto();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.tapOnEditPhotoButton();
        MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get(driver);
        MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.isEditButtonDisplayed();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.isEditButtonDisplayed();
        this.closeApp();

    }

    @Test
    @Description("Edit button not available for video/gif/audio on full screen preview while switching between shared media using swipe")
    public void testEditButtonSwipeBetweenNonPhotosOnSharedScreen() {
        this.openApp();
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectVideoMessageIfNeeded();
        ChatScreenPageObject.selectGifMessageIfNeeded();
        ChatScreenPageObject.recordAudioIfNeeded();
        ChatScreenPageObject.openSentGifInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        this.closeApp();
    }
}
