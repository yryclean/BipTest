package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Base for every page object. The naming convention below holds throughout the
 * hierarchy, because a reader of a test has no other way to tell which calls
 * can fail it:
 *
 * <ul>
 *   <li>{@code is*} / {@code get*} answer a question and return the answer.
 *       They never assert, so a test is free to branch on them.</li>
 *   <li>{@code assert*} fails the test when the expectation does not hold, and
 *       returns nothing.</li>
 *   <li>{@code wait*} blocks until the condition holds and throws on timeout.
 *       Used as a precondition, not as the point of a test.</li>
 *   <li>everything else drives the app. Such a method may verify its own
 *       postcondition — {@code longPressAndPinSentMessage} checks the message
 *       really got pinned — but that is the step confirming it worked, not the
 *       assertion the test was written for.</li>
 * </ul>
 *
 * The methods that used to read {@code isEditButtonDisplayed()} and then fail
 * the test are the reason this is written down: a question-shaped name on a
 * {@code void} method hides the assertion from whoever reads the test.
 */
public class MainPageObject {

    /** How long a long press holds. BiP's action bar needs well over a second. */
    private static final int LONG_PRESS_MS = 2000;

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }


    public void tryClickElementWithAttempts(String locator, String error_message, int amount_of_attempts) {
        RuntimeException last_error = null;
        for (int attempt = 0; attempt <= amount_of_attempts; attempt++) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                return;
            } catch (RuntimeException e) {
                last_error = e;
            }
        }
        throw last_error;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, long timeoutInSeconds) {
        // The message must not echo `value`: callers pass phone numbers and OTP codes.
        WebElement element = waitForElementPresent(
                locator, "Cannot find the input field to type into", timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).isEmpty()) {
            if (already_swiped > max_swipes) {
                // Fails with a descriptive TimeoutException instead of a bare assertion.
                waitForElementPresent(locator, "Cannot find element by swipe. \n" + error_message, 0);
                return;
            }
            scrollPageDown();
            ++already_swiped;
        }
    }

    public void scrollTillElementAppears(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            scrollPageDown();
            ++already_swiped;
        }

    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        // Native coordinates are already viewport-relative, no scroll offset to add.
        int element_location_by_y = this.waitForElementPresent(locator, "Can't find element by locator", 15).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }


    public void testSwipe(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = this.waitForElementPresent(locator, "Can't find element", 10);
            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = size.getHeight() / 2;
            int endX = (int) (size.getWidth() * 0.25);
            int endY = startY;
            int offset_x = (-1 * element.getSize().getWidth());
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, ofMillis(200)))
                    .addAction(finger1.createPointerMove(ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));
        } else {
            System.out.println("Method testSwipe() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void simpleSwipe(String locator) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresent(locator, "Can't find element to swipe", 10);

            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = size.getHeight() / 2;
            int endX = (int) (size.getWidth() * 0.25);
            int endY = startY;
            int offset_x = (-1 * element.getSize().getWidth());
            int offset_y = (-1 * element.getSize().getHeight());

            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, ofMillis(2000)))
                    .addAction(finger1.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endY, offset_x))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));

        } else {
            System.out.println("Method simpleSwipe() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollPageDown() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = size.getHeight() / 2;
            int endX = startX;
            int endY = (int) (size.getHeight() * 0.1);
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, ofMillis(5)))
                    .addAction(finger1.createPointerMove(ofMillis(20), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));
        } else {
            System.out.println("Method scrollPageDown() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void assertElementNotFound(String locator, String error_message, long timeoutInSeconds) {
        try {
            waitForElementNotPresent(locator, error_message, timeoutInSeconds);
        } catch (TimeoutException e) {
            // Still there after the timeout — report it as an assertion, not as a wait failure.
        }
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "' not supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetText(String locator, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(locator, error_message, timeOut);
        return element.getText();
    }

    public By getLocatorByString(String locator_with_type) {
        if (locator_with_type == null) {
            // Page object fields are plain statics, so one the current platform
            // never filled in is simply null, and the split below would blame a
            // NullPointerException on this line instead of on the screen that
            // has no such control. Say what actually happened.
            throw new IllegalStateException(
                    "This locator is not set for platform " + Platform.getInstance().getPlatformVar()
                            + " — the control it names has not been found on this platform's screen. "
                            + "See the page object for which fields were left unset and why.");
        }
        String[] explode_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explode_locator[0];
        String locator = explode_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("name")) {
            return By.name(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator Locator: " + locator_with_type);
        }
    }
        public void swipeToTheLeft(String locator, String swipe_button) {
        WebElement element = this.waitForElementPresent(locator, "Can't find element", 10);

            JavascriptExecutor js = driver;

            Map<String, Object> params = new HashMap<>();
            params.put("direction", "left");
            params.put("element", ((RemoteWebElement) element).getId());
            js.executeScript("mobile: swipe", params);
            this.waitForElementPresent(swipe_button,"Can't find Delete button");
    }

    public String takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name +"_screenshot.png";
        try {
            Files.copy(source.toPath(), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("The screenshot was taken: " + path);
        }catch (Exception e) {
            System.out.println("Can't take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    /**
     * Long press, which the two drivers spell differently: UiAutomator2 has
     * {@code mobile: longClickGesture} and takes milliseconds, XCUITest has
     * {@code mobile: touchAndHold} and takes seconds. Neither script exists on
     * the other platform, so this has to branch — it cannot be pushed down into
     * the page objects, which differ only in their locators.
     */
    public void longPressAction(String locator) {
        this.longPressAction(locator, LONG_PRESS_MS);
    }

    /**
     * The same press held for a caller-chosen time. Audio recording needs a
     * longer hold than a context menu does, and that is the only reason the
     * duration is a parameter — the platform branching stays here either way.
     */
    public void longPressAction(String locator, int duration_ms) {
        WebElement message_element = this.waitForElementPresent(locator, "Can't find message", 15);
        String element_id = ((RemoteWebElement) message_element).getId();
        if (Platform.getInstance().isAndroid()) {
            ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                    Map.of("elementId", element_id,
                            "duration", duration_ms));
        } else {
            ((JavascriptExecutor) driver).executeScript("mobile: touchAndHold",
                    Map.of("elementId", element_id,
                            "duration", duration_ms / 1000.0));
        }
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (Exception e) {
            System.out.println("Can't get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }

}

