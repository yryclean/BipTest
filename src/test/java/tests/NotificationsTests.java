package tests;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;
import org.springframework.context.annotation.Description;

//we need to start tests from app installation, though changes needed in capabilities!!!
//also need to disable auto-accepting alerts in capabilities!!!
public class NotificationsTests extends CoreTestCase {
    private static final String
            msisdn = "5319830384",
            otp = "12345",
            name = "Turkey84",
            info = "Hey Yo man what's up?";

    @Test
    @Description("Notifications action sheet shown on app launch")
    public void testNotificationsPopUpShownOnAppLaunch()
    {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
    }

    @Test
    @Description("Open and change notifications settings")
    public void testNotificationsPopUpAppOpenSettings() {

        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
//        MessagesTabPageObject.batteryOptimizationContinue();
//        MessagesTabPageObject.batteryOptimizationPopUP();
//        MessagesTabPageObject.batteryOptimizationAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
        MessagesTabPageObject.openSettingsFromNotificationPopUp();
        MessagesTabPageObject.isNotificationsSettingsOpened();
        MessagesTabPageObject.tapNotificationsSettingsButton();
        MessagesTabPageObject.closeNotificationsSettings();
        MessagesTabPageObject.isMessagesTabScreenOpened();
    }

    @Test
    @Description("Notifications warning is displayed on the Notifications screen")
    public void testNotificationsWarningOnNotificationScreen()
    {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
        MessagesTabPageObject.closeNotificationsPopUp();
        MoreTabPageObject MoreTabPageObject = MoreTabPageObjectFactory.get(driver);
        MoreTabPageObject.tapMoreTab();
        MoreTabPageObject.scrollToSettings();
        MoreTabPageObject.openSettingsScreen();
        SettingsScreenPageObject SettingsScreenPageObject = SettingsScreenPageObjectFactory.get(driver);
        SettingsScreenPageObject.openNotificationsScreen();
        NotificationsScreenPageObject NotificationsScreenPageObject = NotificationsScreenPageObjectFactory.get(driver);
        NotificationsScreenPageObject.isNotificationsWarningDisplayed();
    }

    @Test
    @Description("Open and change notifications settings from app settings")
    public void testNotificationsWarningOpenSettings() {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
        MessagesTabPageObject.closeNotificationsPopUp();
        MoreTabPageObject MoreTabPageObject = MoreTabPageObjectFactory.get(driver);
        MoreTabPageObject.tapMoreTab();
        MoreTabPageObject.scrollToSettings();
        MoreTabPageObject.openSettingsScreen();
        SettingsScreenPageObject SettingsScreenPageObject = SettingsScreenPageObjectFactory.get(driver);
        SettingsScreenPageObject.openNotificationsScreen();
        NotificationsScreenPageObject NotificationsScreenPageObject = NotificationsScreenPageObjectFactory.get(driver);
        NotificationsScreenPageObject.openNotificationsSettingsFromWarning();
        MessagesTabPageObject.isNotificationsSettingsOpened();
        MessagesTabPageObject.tapNotificationsSettingsButton();
        MessagesTabPageObject.closeNotificationsSettings();
        NotificationsScreenPageObject.isNotificationsWarningNotDisplayed();
    }
    @Test
    @Description("Notifications action sheet is not shown when app was killed and launched")
    public void testNotificationNotShownAppKilled() {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
        this.closeApp();
        this.openApp();
        MessagesTabPageObject.isNotificationsPopUpNotDisplayed();
    }
    @Test
    @Description("Notifications action sheet in landscape mode")
    public void testNotificationsPopUpLandscape() {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
        if (Platform.getInstance().isIOS()) {
            this.rotateScreenLandscape();
        } else {
            return;
        }
        MessagesTabPageObject.waitForNotificationsPopUp();
    }

    @Test
    @Description("Notifications action sheet after background")
    public void testNotificationsPopUpBackground() {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.denyNotificationsPermission();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.denyNotificationsPermissionAgain();
        RegistrationPageObject.waitAndSkipNotificationConfirmationPOPup();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.waitForNotificationsPopUp();
        this.backgroundApp(2);
        MessagesTabPageObject.waitForNotificationsPopUp();
    }
}
