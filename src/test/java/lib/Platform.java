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
    private static final String ANDROID_APP_PACKAGE = "com.turkcell.bip";
    private static final String IOS_BUNDLE_ID = "com.turkcell.bipent";

    /*
     * Everything device- or machine-specific below can be overridden from the
     * command line, e.g.
     *     mvn test -Dandroid.udid=R5CW72KZ9JD -Dandroid.platformVersion=16
     * The defaults are the devices the suite was originally written against,
     * so a plain `mvn test` behaves exactly as before.
     */
    private static final String APPIUM_URL = opt("appium.url", "http://127.0.0.1:4723");

    private static String opt(String key, String fallback)
    {
        String value = System.getProperty(key);
        return (value == null || value.isBlank()) ? fallback : value;
    }

    /** Android appPackage or iOS bundleId — what activateApp/terminateApp expect. */
    public String getAppId() {
        return this.isAndroid() ? ANDROID_APP_PACKAGE : IOS_BUNDLE_ID;
    }

    public AppiumDriver getDriver() throws Exception {
        URL url = URI.create(APPIUM_URL).toURL();
        if (this.isAndroid()) {
            UiAutomator2Options options = this.getAndroidOptions();
            logTarget(url, options.getDeviceName().orElse("?"), options.getUdid().orElse("?"));
            return new AndroidDriver(url, options);
        } else if (this.isIOS()) {
            XCUITestOptions options = this.getIOSOptions();
            logTarget(url, options.getDeviceName().orElse("?"), options.getUdid().orElse("?"));
            return new IOSDriver(url, options);
        } else {
            throw new Exception("Can't detect platform driver. Platform value " + this.getPlatformVar());
        }
    }

    private void logTarget(URL url, String deviceName, String udid)
    {
        System.out.println("Appium " + url + " -> " + this.getPlatformVar()
                + " device '" + deviceName + "' (udid " + udid + ")");
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
                .setPlatformVersion(opt("android.platformVersion", "14.0"))
                .setUdid(opt("android.udid", "R5CWA0Q2GGB"))
                .setAppPackage(ANDROID_APP_PACKAGE)
                .setDeviceName(opt("android.deviceName", "SamsungA54"))
                .setAppActivity("com.turkcell.bip.ui.main.BipActivity")
                .setNoReset(true)
                .setAutoGrantPermissions(true);
    }

    private XCUITestOptions getIOSOptions()
    {
        return new XCUITestOptions()
                .setPlatformVersion(opt("ios.platformVersion", "26.7.1"))
                .setDeviceName(opt("ios.deviceName", "iPhone 14 Pro"))
                .setUdid(opt("ios.udid", "00008120-001C28E41A78C01E"))
                .setBundleId(IOS_BUNDLE_ID)
                // Real-device signing: Team ID, not the certificate's organisation name.
                // Tied to the developer account, so it changes per machine.
                .setXcodeCertificate(new XcodeCertificate(
                        opt("ios.xcodeOrgId", "4YZRCKX375"),
                        opt("ios.xcodeSigningId", "Apple Development")))
                // Avoids the unregistered com.facebook.WebDriverAgentRunner.xctrunner bundle ID.
                .setUpdatedWdaBundleId(opt("ios.wdaBundleId", "WebDriverTesting"))
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
