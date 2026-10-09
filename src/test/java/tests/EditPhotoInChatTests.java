package tests;

import lib.ChatTestCase;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;
import io.qameta.allure.Description;

public class EditPhotoInChatTests extends ChatTestCase {
    private static final String
            chat_name = "Yury Chistyakov",
            chat_name_channel_non_admin = "Not_admin_channel";

    private final String caption_text = messageText("Hello-hello!");

    @Test
    @Description("Edit button available for photo on full screen preview")
    public void testEditButtonAvailableOnSharedMediaScreen() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.assertEditButtonDisplayed();
    }

    @Test
    @Description("Sending photo opened in full screen preview from chat")
    public void testMediaEditScreenOpenedOnEditTap() {
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
    }

    @Test
    @Description("Edit button is not available for video/gif/doc/live photo/audio on full screen preview")
    public void testNoEditButtonIfNotAPhotoOpened() throws InterruptedException {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectVideoMessageIfNeeded();
        ChatScreenPageObject.openSentVideoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.tapOnVideo();
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
        SharedMediaScreenPageObject.tapOnEditPhotoButton();
//        this.backgroundApp(2);
    }

    @Test
    @Description("Edit button available for photo on full screen preview while switching between other photos using swipe")
    public void testEditButtonSwipeBetweenPhotosOnSharedScreen() {
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
        SharedMediaScreenPageObject.assertEditButtonDisplayed();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.assertEditButtonDisplayed();

    }

    @Test
    @Description("Edit button not available for video/gif/audio on full screen preview while switching between shared media using swipe")
    public void testEditButtonSwipeBetweenNonPhotosOnSharedScreen() {
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject.selectVideoMessageIfNeeded();
        ChatScreenPageObject.selectGifMessageIfNeeded();
        ChatScreenPageObject.recordAudioIfNeeded();
        ChatScreenPageObject.openSentGifInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
        SharedMediaScreenPageObject.switchRightOrLeftBetweenMedia();
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen preview from Starred Messages screen")
    public void testEditButtonNotDisplayedFromStarredMessages() {
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
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
        SharedMediaScreenPageObject.closeSharedMediaOpenChat();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen preview from All Shared Media screen(open from chat)")
    public void testEditButtonNotDisplayedFromAllSharedMediaScreen1() {
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.openAllSharedMediaScreen();
        AllSharedMediaScreePageObject AllSharedMediaScreePageObject = AllSharedMediaScreePageObjectFactory.get(driver);
        AllSharedMediaScreePageObject.openSharedPhoto();
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen preview from All Shared Media screen(open from chat info)")
    public void testEditButtonNotDisplayedFromAllSharedMediaScreen2() {
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
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button not available for photo opened on full screen from Storage Management screen")
    public void testEditButtonNotDisplayedFromStorageManagementScreen() {
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
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button not available for video opened on full screen from All Media screen")
    public void testEditButtonNotDisplayedFromAllMediaScreen() {
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
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button not available in the Channel chats for non admin users")
    //precondition: channel created, user no an admin in chat, photo received in chat
    public void testEditButtonNotDisplayedForNonAdminInChannel() {
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name_channel_non_admin);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.openReceivedPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button not available for grouped media")
    public void testEditButtonNotDisplayedForGroupedMedia(){
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        ChatScreenPageObject.openSentGroupOfMedia(chat_name);
        SharedMediaScreenPageObject.openMediaFromGroup();
        SharedMediaScreenPageObject.assertEditButtonNotDisplayed();
    }

    @Test
    @Description("Edit button Landscape mode compatibility")
    public void testEditButtonInLandscapeMode(){
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoMessageIfNeeded();
        ChatScreenPageObject.waitForSentPhoto();
        ChatScreenPageObject.openSentPhotoInFullScreen();
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        this.rotateScreenLandscape();
        SharedMediaScreenPageObject.assertEditButtonDisplayed();
        SharedMediaScreenPageObject.tapOnEditPhotoButton();
        MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get(driver);
        MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        ChatScreenPageObject.waitForSentPhoto();
    }

    @Test
    @Description("Editing photo with caption")
    public void testEditPhotoWithCaption() {
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.selectPhotoWithCaption(caption_text);
        ChatScreenPageObject.waitForSentPhotoWitCaption(caption_text);
        ChatScreenPageObject.openSentPhotoWithCaptionInFullScreen(caption_text);
        SharedMediaScreenPageObject SharedMediaScreenPageObject = SharedMediaPageObjectFactory.get(driver);
        SharedMediaScreenPageObject.assertEditButtonDisplayed();
        SharedMediaScreenPageObject.tapOnEditPhotoButton();
        MediaEditScreenPageObject MediaEditScreenPageObject = MediaEditScreenPageObjectFactory.get(driver);
        MediaEditScreenPageObject.tapSendButtonOnMediaEditScreen();
        ChatScreenPageObject.waitForSentPhoto();
    }

    //out of scope
    @Test
    @Description("Test clear chat")
    public void testClearChat() {
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.openChatWithName(chat_name);
        ChatScreenPageObject ChatScreenPageObject = ChatScreenPageObjectFactory.get(driver);
        ChatScreenPageObject.clearChat();
    }
    //out of scope
}
