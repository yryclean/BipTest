package tests;

import lib.CoreTestCase;
import lib.ui.BackupRestorePageObject;
import lib.ui.MessagesTabPageObject;
import lib.ui.RegistrationUIPageObject;
import lib.ui.factories.BackupRestorePageObjectFactory;
import lib.ui.factories.MessagesTabPageObjectFactory;
import lib.ui.factories.RegistrationPageObjectFactory;
import org.junit.Test;
//we need to start tests from app installation, though changes needed in capabilities!!!
//also need to disable auto-accepting alerts in capabilities!!!
public class RegistrationTests extends CoreTestCase {
    private static final String
            msisdn = "5319830382",
            otp = "12345",
            name = "Turkey82",
            info = "Hey Yo man what's up?";

    @Test
      public void testRegistrationPassQuick() {
        RegistrationUIPageObject RegistrationPageObject = RegistrationPageObjectFactory.get(driver);
        RegistrationPageObject.waitForNotificationPermissionPopUp();
        RegistrationPageObject.allowNotificationsPermission();
        RegistrationPageObject.waitForCallsPermissionPopUp();
        RegistrationPageObject.allowCallsPermission();
        RegistrationPageObject.openTermsOfUse();
        RegistrationPageObject.scrollTermsOfUse();
        RegistrationPageObject.closeTermsOfUse();
        RegistrationPageObject.tapCountryCode();
        RegistrationPageObject.searchCountryCode();
        RegistrationPageObject.enterPhoneNumberAndConfirm(msisdn);
        RegistrationPageObject.enterOTPCode(otp);
        RegistrationPageObject.enterUserNameInfoCompleteRegistration(name,info);
        BackupRestorePageObject BackupRestorePageObject = BackupRestorePageObjectFactory.get(driver);
        BackupRestorePageObject.restoreBackup();
        MessagesTabPageObject MessagesTabPageObject = MessagesTabPageObjectFactory.get(driver);
        MessagesTabPageObject.contactsAccessPermissionAllow();
        MessagesTabPageObject.openChatWithName();
    }
}