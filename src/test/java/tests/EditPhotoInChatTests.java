package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.*;
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
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
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
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
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
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
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
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
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
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
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

    @Test
    @Description("Edit button not available for photo opened on full screen preview from Starred Messages screen")
    public void testEditButtonNotDisplayedFromStarredMessages() {
        this.openApp();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.longPressAndAddStarSentPhoto();
        ChatScreenPageObject.tapBackButtonOpenChatList();
        MessagesTabPageObject MessagesTabPaObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPaObject.openMoreTab();
        MoreTabPageObject MoreTabPageobject = MoreTabPageObjectFactory.get(driver);
        MoreTabPageobject.openStarredMessagesScreen();
        StarredMessagesScreenPageObject StarredMessagesScreenPageObject = StarredMessagesScreenPageObjectFactory.get(driver);
        StarredMessagesScreenPageObject.tapOpenStarredPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        SharedMediaScreenPageObject.closeSharedMediaOpenChat();
        this.closeApp();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen preview from All Shared Media screen(open from chat)")
    public void testEditButtonNotDisplayedFromAllSharedMediaScreen1() {
        this.openApp();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.openAllSharedMediaScreen();
        AllSharedMediaScreePageObject AllSharedMediaScreePageObject = AllSharedMediaScreePageObjectFactory.get(driver);
        AllSharedMediaScreePageObject.openSharedPhoto();
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen preview from All Shared Media screen(open from chat info)")
    public void testEditButtonNotDisplayedFromAllSharedMediaScreen2() {
        this.openApp();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.openInfoScreen();
        ChatInfoScreenPageObject ChatInfoScreenPageObject = ChatInfoScreenPageObjectFactory.get(driver);
        ChatInfoScreenPageObject.openAllSharedMedia();
        AllSharedMediaScreePageObject AllSharedMediaScreePageObject = AllSharedMediaScreePageObjectFactory.get(driver);
        AllSharedMediaScreePageObject.openSharedPhoto();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen from Storage Management screen")
    public void testEditButtonNotDisplayedFromStorageManagementScreen() {
        this.openApp();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.tapBackButtonOpenChatList();
        MessagesTabPageObject MessagesTabPaObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPaObject.openMoreTab();
        MoreTabPageObject MoreTabPageobject = MoreTabPageObjectFactory.get(driver);
        MoreTabPageobject.openSettingsScreen();
        SettingsScreenPageObject SettingsScreenPageObject = SettingsScreenPageObjectFactory.get(driver);
        SettingsScreenPageObject.openStorageManagement();
        StorageManagementScreenPageObject StorageManagementScreenPageObject = StorageManagementScreenPageObjectFactory.get(driver);
        StorageManagementScreenPageObject.openChatWithName(chat_name);
        StorageManagementScreenPageObject.openPhotoItemInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        this.closeApp();
    }

    @Test
    @Description("Edit button not available for video opened on full screen from All Media screen")
    public void testEditButtonNotDisplayedFromAllMediaScreen() {
        this.openApp();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.waitForSentPhoto();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.openAllSharedMediaScreen();
        AllSharedMediaScreePageObject AllSharedMediaScreePageObject = AllSharedMediaScreePageObjectFactory.get(driver);
        AllSharedMediaScreePageObject.openSharedPhoto();
        SharedMediaScreenPageObject.isEditButtonNotDisplayed();
        this.closeApp();
    }
}
