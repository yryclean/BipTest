package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;

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
            ((AndroidDriver<MobileElement>)driver).unlockDevice();
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
            AppiumDriver driver = this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
            AppiumDriver driver = this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
            AppiumDriver driver = this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    public void closeApp() {
            AppiumDriver driver = this.driver;
            driver.terminateApp("com.turkcell.bip");
    }
    public void openApp() {
            AppiumDriver driver = this.driver;
            driver.activateApp("com.turkcell.bip/com.turkcell.bip.ui.main.BipActivity");
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
        ((AndroidDriver)driver).toggleAirplaneMode();

    }
    public void enableAllInternetConnection() {
            try {
                ((AndroidDriver)driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
                System.out.println("Switching On the connection: " + ((AndroidDriver)driver).getConnection());
            } catch (Exception e) {
                System.out.println("Connection could not be switch ON");
            }
    }
    //one more solution to turn off internet connection
    public void setAllConnectionToOFF() {
        try {
            ((AndroidDriver)driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            System.out.println("Switching OFF the connection : " + ((AndroidDriver)driver).getConnection());
        } catch (Exception e) {
            System.out.println("Connection could not be switch OFF");
        }
    }
    public void clickBackButton(){
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
    }
}