package lib;

import lib.ui.MessagesTabPageObject;
import lib.ui.factories.MessagesTabPageObjectFactory;
import org.junit.Before;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Base for the tests that work inside a chat. It gives them the two things
 * they need to stop interfering with one another:
 *
 * <ol>
 *   <li>a known starting screen — the chat list — no matter where the previous
 *       test left the app, and</li>
 *   <li>message text that is unique to the run, so a bubble left behind by an
 *       earlier run can never be mistaken for this run's own.</li>
 * </ol>
 *
 * The second point is what makes the {@code sendMessageIfNeeded} /
 * {@code selectPhotoMessageIfNeeded} guards honest: with plain literals they
 * found an old bubble and skipped the very step they were meant to verify.
 */
public class ChatTestCase extends CoreTestCase {

    /**
     * Per test, not per run, and deliberately so. JUnit builds a fresh instance
     * for every test method, so every method gets its own tag — which is what
     * stops two tests in the same class from reaching for the same bubble and
     * inheriting each other's pin, star or delete state.
     */
    private final String tag =
            Integer.toHexString(ThreadLocalRandom.current().nextInt(0x1000, 0x10000));

    /**
     * Lands every test on the chat list, wherever the previous one stopped.
     * Backing out is tried first because it is a second or two; re-launching is
     * the fallback for when the back presses walked out of BiP entirely, which
     * is exactly what happens after a test that failed deep in a sub-screen.
     */
    @Before
    public void openChatList() {
        MessagesTabPageObject messages_tab = MessagesTabPageObjectFactory.get(driver);
        for (int attempt = 0; attempt < 2 && !messages_tab.isChatListOpen(); attempt++) {
            messages_tab.pressBackTowardsChatList();
            if (!messages_tab.isChatListOpen()) {
                this.openApp();
            }
        }
        messages_tab.waitForChatList();
    }

    /**
     * Text to type into the input bar, tagged for this test. Instance methods,
     * so the subclasses have to hold their data in instance fields too — that
     * is the point: a {@code static final} literal is shared state.
     */
    public String messageText(String base) {
        return base + " " + tag;
    }

    /** The content-desc BiP puts on the bubble for {@link #messageText}. */
    public String sentBubble(String base) {
        return "Sent message " + messageText(base);
    }
}
