package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.LocksDevice;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.SupportsRotation;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
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

    @Override
    protected void tearDown() throws Exception {
            driver.quit();
            super.tearDown();
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
