package lib.ui.ios;
import io.appium.java_client.AppiumDriver;
import lib.ui.MoreTabPageObject;

public class iOSMoreTabPageObject extends MoreTabPageObject {

    static {
        MORE_TAB = "id:More ";


    }
    public iOSMoreTabPageObject(AppiumDriver driver) {
        super(driver);
    }
}
