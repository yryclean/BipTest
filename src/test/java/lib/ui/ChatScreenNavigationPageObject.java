package lib.ui;

import io.appium.java_client.AppiumDriver;

/**
 * Bottom layer of the chat screen: locator templates, and getting into and out
 * of a chat. Everything above it builds on the template helpers declared here.
 *
 * @see ChatScreenPageObject for the full layering.
 */
public abstract class ChatScreenNavigationPageObject extends MainPageObject {

    protected static String
            // Templates — the {PLACEHOLDER} is filled in by the helpers below.
            CHAT_WITH_NAME_TPL,
            SENT_MESSAGE_BUBBLE_TPL,
            RECEIVED_MESSAGE_TPL,
            SENT_MESSAGE_PHOTO_CAPTION_TPL,
            CHAT_WITH_NAME,
            // Shared chrome: the long-press action bar and the overflow menu are
            // entry points for the delete, secret and pin layers alike.
            ACTION_BAR_MENU,
            ACTION_BAR_MENU_MORE_OPTIONS,
            ACTION_BAR_MENU_ITEM,
            THREE_DOTS_BUTTON,
            CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON,
            CONTACT_INFO_PLACE_HOLDER,
            CONTACT_INFO_SCREEN_ACTIVITY,
            CONTACT_INFO_SCREEN_BACK_BUTTON,
            WIFI_DISABLED_CONNECTION_POP_UP,
            WIFI_POP_UP_OK_BUTTON;

    public ChatScreenNavigationPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    protected static String getChatNameByXpathName(String chat_name) {
        return CHAT_WITH_NAME_TPL.replace("{CHAT_NAME}", chat_name);
    }

    protected static String getSentMessageByXpathName(String sent_message_name) {
        return SENT_MESSAGE_BUBBLE_TPL.replace("{SENT_MESSAGE}", sent_message_name);
    }

    protected static String getReceivedMessageByXpathName(String received_message_name) {
        return RECEIVED_MESSAGE_TPL.replace("{RECEIVED_MESSAGE}", received_message_name);
    }

    protected static String getSentPhotoCaptionMessageByXpathName(String sent_photo_caption) {
        return SENT_MESSAGE_PHOTO_CAPTION_TPL.replace("{CAPTION}", sent_photo_caption);
    }

    public void waitForChatName(String chat_name) {
        String chat_xpath = getChatNameByXpathName(chat_name);
        this.waitForElementPresent(
                (chat_xpath),
                "Cannot find chat " + chat_name,
                15
        );
    }

    public void openChatWithName(String chat_name) {
        String chat_xpath = getChatNameByXpathName(chat_name);
        this.waitForElementAndClick(
                (chat_xpath),
                "Cannot open chat " + chat_name,
                15
        );
    }

    public void tapBackButtonOpenChatList() {
        this.waitForElementAndClick(
                CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON,
                "Can't open chat list",
                20
        );
    }

    public void openInfoScreen() {
        this.waitForElementAndClick(
                CONTACT_INFO_PLACE_HOLDER,
                "Can't find and tap on info area",
                15
        );
        this.waitForElementPresent(
                CONTACT_INFO_SCREEN_ACTIVITY,
                "Info screen is not opened",
                15
        );
    }

    public void closeInfoScreen() {
        this.waitForElementAndClick(
                CONTACT_INFO_SCREEN_BACK_BUTTON,
                "Can't find and tap on the Back button",
                15
        );
        this.waitForElementPresent(
                CONTACT_INFO_PLACE_HOLDER,
                "Chat screen is not opened",
                15
        );
    }

    public void confirmWifiPopUp() {
        if (isElementPresent(WIFI_DISABLED_CONNECTION_POP_UP)) {
            this.waitForElementPresent(
                    WIFI_DISABLED_CONNECTION_POP_UP,
                    "Pop up is missing",
                    15
            );
            this.waitForElementAndClick(
                    WIFI_POP_UP_OK_BUTTON,
                    "Can't tap on OK button",
                    15
            );
        } else {
            System.out.println("Pop up is not displayed");
        }
    }
}
