package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.LocksDevice;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.SupportsRotation;
import io.qameta.allure.Allure;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

/**
 * Deliberately a plain JUnit 4 class. Extending junit.framework.TestCase would
 * hand the suite to JUnit38ClassRunner, which silently drops every Allure
 * annotation and ignores @Rule entirely.
 */
public class CoreTestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;

    /**
     * Collects failure diagnostics and owns the driver teardown. Quitting here
     * rather than in an @After matters: rules wrap @After, so a driver closed
     * there would already be gone by the time failed() runs.
     */
    @Rule
    public TestWatcher diagnostics = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            attachDiagnostics(description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    };

    @Before
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        if (Platform.getInstance().isAndroid()) {
            ((LocksDevice) driver).unlockDevice();
            this.rotateScreenPortrait();
        } else {
            this.rotateScreenPortrait();
        }
    }

    /** Best effort: a failing test must not be masked by a failing screenshot. */
    private void attachDiagnostics(String testName) {
        if (driver == null) {
            return;
        }
        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(testName, "image/png", new ByteArrayInputStream(png), ".png");
        } catch (Exception e) {
            System.err.println("Could not attach a screenshot: " + e.getMessage());
        }
        try {
            Allure.addAttachment(testName + " page source", "text/xml",
                    new ByteArrayInputStream(driver.getPageSource().getBytes(StandardCharsets.UTF_8)), ".xml");
        } catch (Exception e) {
            System.err.println("Could not attach the page source: " + e.getMessage());
        }
    }

    protected void rotateScreenPortrait() {
            ((SupportsRotation) driver).rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
            ((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
            ((InteractsWithApps) driver).runAppInBackground(Duration.ofSeconds(seconds));
    }

    public void closeApp() {
            ((InteractsWithApps) driver).terminateApp(Platform.getInstance().getAppId());
    }
    public void openApp() {
            ((InteractsWithApps) driver).activateApp(Platform.getInstance().getAppId());
    }

    public void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties properties = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            properties.setProperty("Environment", Platform.getInstance().getPlatformVar());
            properties.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem writing allure properties file");
            e.printStackTrace();
        }
    }
    public void enableAirplaneMode() {
        ((AndroidDriver) driver).toggleAirplaneMode();

    }
    public void enableAllInternetConnection() {
            try {
                ((AndroidDriver) driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
                System.out.println("Switching On the connection: " + ((AndroidDriver) driver).getConnection());
            } catch (Exception e) {
                System.out.println("Connection could not be switch ON");
            }
    }
    //one more solution to turn off internet connection
    public void setAllConnectionToOFF() {
        try {
            ((AndroidDriver) driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            System.out.println("Switching OFF the connection : " + ((AndroidDriver) driver).getConnection());
        } catch (Exception e) {
            System.out.println("Connection could not be switch OFF");
        }
    }
    public void clickBackButton(){
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }
}
