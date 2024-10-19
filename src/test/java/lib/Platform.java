package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class Platform
{
    private static Platform instance;
    private Platform() {}
    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Can't detect platform driver. Platform value " + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "14.0");
        capabilities.setCapability("appium:udid", "R5CWA0Q2GGB");
        capabilities.setCapability("appium:appPackage", "com.turkcell.bip");
        capabilities.setCapability("appium:deviceName", "SamsungA54");
        capabilities.setCapability("appium:appActivity", "com.turkcell.bip.ui.main.BipActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/lifetech/Downloads/apks/bip-3.100.5.dev.prp.apk");
        capabilities.setCapability("appium:noReset", "true");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:platformVersion", "17.4");
        capabilities.setCapability("appium:deviceName", "iPhone 14 Pro");
//        capabilities.setCapability("appium:udid", "auto");
//        capabilities.setCapability("appium:bundleId", "");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);

    }

    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
