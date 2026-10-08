package lib.ui;

import io.appium.java_client.AppiumDriver;

/**
 * The chat screen. One screen, one page object — but the implementation is
 * split across a chain of superclasses, one per feature of the screen, so that
 * each locator sits next to the methods that use it:
 *
 * <pre>
 * MainPageObject
 *  └ {@link ChatScreenNavigationPageObject}  templates, opening/leaving a chat, info screen
 *     └ {@link ChatScreenMessagePageObject}  input bar, message waits, stars, edit locators
 *        └ {@link ChatScreenDeletePageObject}  delete from me/everyone, undo bar, clear chat
 *           └ {@link ChatScreenMediaPageObject}  attach menu, photo/video/gif/audio, full screen
 *              └ {@link ChatScreenSecretPageObject}  disappearing messages
 *                 └ {@link ChatScreenPinPageObject}  pin, unpin, pin limit, edit pinned
 *                    └ ChatScreenPageObject
 * </pre>
 *
 * Tests and {@code ChatScreenPageObjectFactory} only ever see this type, and
 * the platform subclasses still assign every locator in one {@code static}
 * block — the fields are inherited through the chain.
 */
public abstract class ChatScreenPageObject extends ChatScreenPinPageObject {

    public ChatScreenPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
