package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

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
    private static final String ANDROID_APP_PACKAGE = "com.turkcell.bip";
    private static final String IOS_BUNDLE_ID = "com.turkcell.bipent";

    /** Android appPackage or iOS bundleId — what activateApp/terminateApp expect. */
    public String getAppId() {
        return this.isAndroid() ? ANDROID_APP_PACKAGE : IOS_BUNDLE_ID;
    }

    public AppiumDriver getDriver() throws Exception {
        URL url = URI.create(APPIUM_URL).toURL();
        if (this.isAndroid()) {
            return new AndroidDriver(url, this.getAndroidOptions());
        } else if (this.isIOS()) {
            return new IOSDriver(url, this.getIOSOptions());
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

    private UiAutomator2Options getAndroidOptions()
    {
        return new UiAutomator2Options()
                .setPlatformVersion("14.0")
                .setUdid("R5CWA0Q2GGB")
                .setAppPackage(ANDROID_APP_PACKAGE)
                .setDeviceName("SamsungA54")
                .setAppActivity("com.turkcell.bip.ui.main.BipActivity")
                .setNoReset(true)
                .setAutoGrantPermissions(true);
    }

    private XCUITestOptions getIOSOptions()
    {
        return new XCUITestOptions()
                .setPlatformVersion("26.7.1")
                .setDeviceName("iPhone 14 Pro")
                .setUdid("00008120-001C28E41A78C01E")
                .setBundleId(IOS_BUNDLE_ID)
                // Real-device signing: Team ID, not the certificate's organisation name.
                .setXcodeCertificate(new XcodeCertificate("4YZRCKX375", "Apple Development"))
                // Avoids the unregistered com.facebook.WebDriverAgentRunner.xctrunner bundle ID.
                .setUpdatedWdaBundleId("WebDriverTesting")
                .setWdaLaunchTimeout(Duration.ofMinutes(4));
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
