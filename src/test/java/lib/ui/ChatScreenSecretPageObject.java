package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

/**
 * Disappearing ("secret") messages: the timer behind the overflow menu and the
 * counter it puts on a bubble.
 *
 * @see ChatScreenPageObject for the full layering.
 */
public abstract class ChatScreenSecretPageObject extends ChatScreenMediaPageObject {

    protected static String
            SECRET_MESSAGE_BUTTON,
            SECRET_TIME_PICKER,
            SECRET_TIMER_APPLY_BUTTON,
            SECRET_MESSAGE_DISABLED_INFO,
            SECRET_MESSAGE_COUNTER,
            // Captured, no test uses them yet.
            SECRET_TIMER_SET_TIME,
            SECRET_MESSAGE_DISABLE;

    public ChatScreenSecretPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void setSecretMessageTimer() {
        this.waitForElementAndClick(THREE_DOTS_BUTTON,
                "Can't tap on 3-dots button",
                25);
        this.waitForElementAndClick(SECRET_MESSAGE_BUTTON,
                "Can't find and tap Secret message button",
                25);
        this.waitForElementAndClick(SECRET_TIME_PICKER, "Can't set timer", 25);
        this.waitForElementAndClick(SECRET_TIMER_APPLY_BUTTON,
                "Can't apply secret message timer",
                25);
    }

    public void disableSecretMessage() {
        this.waitForElementAndClick(THREE_DOTS_BUTTON,
                "Can't tap on 3-dots button",
                25);
        this.waitForElementAndClick(SECRET_MESSAGE_BUTTON,
                "Can't find and tap Secret message button",
                25);
        Assert.assertTrue(isElementPresent(SECRET_MESSAGE_DISABLED_INFO));
    }

    public void assertSecretMessageSent() {
        Assert.assertTrue(isElementPresent(SECRET_MESSAGE_COUNTER));
    }
}
