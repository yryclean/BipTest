package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ChatScreenPageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

/**
 * iOS counterpart of {@link lib.ui.android.AndroidChatScreenPageObject}.
 *
 * <p>Two conventions run through every locator here and are worth stating once
 * instead of repeating in each line:
 *
 * <ol>
 *   <li><b>{@code @visible="true"} on everything that comes out of a menu.</b>
 *       BiP's accessibility tree carries a second, invisible copy of each
 *       context-menu row. A find returns the invisible one first, and tapping
 *       it does nothing at all — no error, no effect, just a test that fails
 *       three steps later on a screen that never changed.</li>
 *   <li><b>Message bubbles are matched by their {@code label} prefix.</b> iOS
 *       folds the whole bubble into one string —
 *       {@code "Outgoing Messages,<text>,,,at 3:43 PM,Waiting,Double tap and hold…"}
 *       — whose tail depends on the delivery state and the clock, so only the
 *       head of it can be matched.</li>
 * </ol>
 *
 * <p>Fields the Android object sets and this one does not are listed at the
 * bottom: those screens have not been walked on the device yet, and a guessed
 * locator fails later and less clearly than a missing one.
 */
public class iOSChatScreenPageObject extends ChatScreenPageObject {

    /**
     * iOS has no Delete dialog. Delete puts the chat into a selection mode with
     * a toolbar, and the action sheet only appears after its trash is tapped.
     */
    private static final String SELECTION_MODE_TRASH_BUTTON = "id:toolbarTrash";

    /**
     * How far below the top of the screen to tap to dismiss a context menu.
     * The menu is dismissed by its backdrop, and the backdrop is everywhere
     * except the menu itself — but tapping the very top lands in the status
     * bar, which swallows it. This clears the safe area and still sits above
     * any menu BiP puts on screen.
     */
    private static final int BACKDROP_TAP_Y = 70;

    static {
            // ---- chat list -> chat -------------------------------------------------
            // Scoped to the visible cell: the list keeps recycled rows for the same
            // chat off-screen, and without this the tap goes to one of those.
            CHAT_WITH_NAME_TPL = "xpath://XCUIElementTypeCell[@name=\"chat_id\" and @visible=\"true\"]//XCUIElementTypeStaticText[@name=\"{CHAT_NAME}\"]";
            CHAT_SCREEN_BACK_TO_CHAT_LIST_BUTTON = "id:BackButton";

            // ---- message bubbles ---------------------------------------------------
            SENT_MESSAGE_BUBBLE_TPL = "xpath://XCUIElementTypeOther[@name=\"MessageTextLabel\" and starts-with(@label,\"Outgoing Messages,{SENT_MESSAGE},\")]";
            RECEIVED_MESSAGE_TPL = "xpath://XCUIElementTypeOther[@name=\"MessageTextLabel\" and starts-with(@label,\"Received Messages,{RECEIVED_MESSAGE},\")]";
            MESSAGE_BUBBLE_ON_SCREEN = "xpath://XCUIElementTypeCell[@name=\"CollectionCellContainer\"]";
            SENT_MESSAGE_DELIVERY_INFO = "id:DeliverDateTimeLabel";

            // ---- input bar ---------------------------------------------------------
            INPUT_BAR_FIELD = "id:chatInputTextView";
            // Only exists once there is something to send — the same spot holds the
            // microphone button while the field is empty.
            SEND_MESSAGE_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Send Message\"]";
            EDIT_MESSAGE_INPUT_BAR = "id:chatInputTextView";

            // ---- long-press context menu -------------------------------------------
            // Android's action bar is a toolbar; iOS puts up a UIContextMenuInteraction.
            // Its rows are plain StaticText, and the reaction strip at the top is the
            // only part present in both the main menu and the submenu — which makes it
            // the one honest "a menu is open" marker.
            ACTION_BAR_MENU = "xpath://XCUIElementTypeOther[@name=\"Add custom reaction\"]";
            ACTION_BAR_MENU_MORE_OPTIONS = "xpath://XCUIElementTypeStaticText[@name=\"Other actions\" and @visible=\"true\"]";
            // Back appears only in the submenu, so it marks that the overflow — and not
            // just the main menu — has finished animating in.
            ACTION_BAR_MENU_ITEM = "xpath://XCUIElementTypeStaticText[@name=\"Back\" and @visible=\"true\"]";
            EDIT_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Edit\" and @visible=\"true\"]";
            ADD_STAR_TO_MESSAGE = "xpath://XCUIElementTypeStaticText[@name=\"Add star\" and @visible=\"true\"]";

            // ---- delete ------------------------------------------------------------
            DELETE_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Delete\" and @visible=\"true\"]";
            CONFIRM_DELETE_POP_UP = "xpath://XCUIElementTypeStaticText[@name=\"Delete message?\" and @visible=\"true\"]";
            DELETE_FROM_ME = "xpath://XCUIElementTypeStaticText[@name=\"Delete for me\" and @visible=\"true\"]";
            DELETE_FROM_EVERYONE = "xpath://XCUIElementTypeStaticText[@name=\"Delete for everyone\" and @visible=\"true\"]";
            CANCEL_DELETE = "xpath://XCUIElementTypeButton[@name=\"Cancel\" and @visible=\"true\"]";
            // OK_DELETE_FROM_ME stays unset on purpose: the sheet row commits the
            // delete itself. See applyDeleteChoice below.

            // ---- undo bar ----------------------------------------------------------
            // The bar has no container of its own in the tree, so the Undo button
            // stands in for it. That is still a real check — the button is what the
            // caller has to reach, and it disappears with the bar.
            UNDO_DELETE_FROM_ME_BAR = "xpath://XCUIElementTypeButton[@name=\"Undo\"]";
            UNDO_DELETE_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Undo\"]";
            UNDO_DELETE_1_MESSAGE_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"1 message deleted from me\"]";
            // Plural of the string above; the two-message delete has not been run on
            // the device yet, so this one is the only locator here taken on trust.
            UNDO_DELETE_2_MESSAGES_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"2 messages deleted from me\"]";

            // ---- pin ---------------------------------------------------------------
            PIN_MESSAGE_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Pin\" and @visible=\"true\"]";
            UNPIN_MESSAGE_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Unpin\" and @visible=\"true\"]";
            PIN_ICON_ON_SENT_MESSAGE = "xpath://XCUIElementTypeImage[@name=\"PinImageView\"]";
            PINNED_MESSAGE_BAR = "xpath://XCUIElementTypeButton[@name=\"Pinned message\"]";
            PINNED_MESSAGE_IN_PIN_BAR = "xpath://XCUIElementTypeButton[@name=\"Pinned message\"]//XCUIElementTypeStaticText";
            PINNED_MESSAGE_IN_PIN_BAR_TPL = "xpath://XCUIElementTypeButton[@name=\"Pinned message\"]//XCUIElementTypeStaticText[@name=\"{TEXT}\"]";
            YOU_PINNED_INFO_MESSAGE = "xpath://XCUIElementTypeOther[@name=\"ChatPageMessageCellInfo\" and @label=\"You pinned a message\"]";

            // Not captured yet, and deliberately left null rather than guessed:
            // the attach menu and gallery, secret messages, clear chat, the contact
            // info screen, the Wi-Fi pop-up, the pin limit warning, unpinning from
            // the pin bar, and the star icon on a bubble. Everything above was read
            // off the device; these screens have not been walked there.
    }

    public iOSChatScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    /** Delete only opens the selection mode; the toolbar's trash opens the sheet. */
    @Override
    protected void openDeleteConfirmation() {
        this.waitForElementAndClick(
                SELECTION_MODE_TRASH_BUTTON,
                "Can't tap the trash button in selection mode",
                15
        );
    }

    /**
     * Nothing to confirm. Picking the scope in the action sheet is the delete.
     * Android's OK button has no counterpart here, which is the whole reason
     * this is a hook and not a shared step.
     */
    @Override
    protected void applyDeleteChoice() {
    }

    /**
     * There is no selection left to clear — the context menu dims the chat
     * rather than selecting a row — so this only has to dismiss the menu. The
     * ways that look right do not work: the submenu's Back returns to the main
     * menu instead of closing, and tapping the message preview is ignored from
     * both levels. Tapping the dimmed backdrop is what closes either one.
     */
    @Override
    protected void closeActionBarOverflowAndDeselect(String sent_message_xpath) {
        Dimension screen = driver.manage().window().getSize();
        ((JavascriptExecutor) driver).executeScript("mobile: tap",
                Map.of("x", screen.getWidth() / 2,
                        "y", BACKDROP_TAP_Y));
        this.waitForElementNotPresent(
                ACTION_BAR_MENU,
                "The context menu is still open",
                15
        );
    }
}
