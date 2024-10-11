package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import lib.Platform;

abstract public class RegistrationUIPageObject extends MainPageObject {
    protected static String
            NOTIFICATION_PERMISSION_POP_UP,
            ALLOW_NOTIFICATIONS, DENY_NOTIFICATIONS,
            DENY_NOTIFICATIONS_AGAIN,
            CALL_NOTIFICATIONS_PERMISSION_POP_UP,
            ALLOW_CALL_NOTIFICATIONS,
            DENY_CALL_NOTIFICATIONS,
            NOT_NOW_NOTIFICATION_ALLOW_CONFIRMATION_POP_UP,
            OPEN_SETTINGS_NOTIFICATION_ALLOW_CONFIRMATION_POP_UP,
            TERMS_OF_USE_LINK,
            CONTACT_US_TERMS_OF_USE_READ,
            CLOSE_TERMS_OF_USE,
            PHONE_NUMBER_ENTER,
            COUNTRY_CODE,
            COUNTRY_CODE_SEARCH_INIT,
            COUNTRY_CODE_SEARCH_INPUT,
            COUNTRY_CODE_SEARCH_CLEAR_BUTTON,
            COUNTRY_CODE_SELECTION,
            SELECTED_COUNTRY_CODE,
            REGISTER_BUTTON,
            POP_UP_NUMBER_CONFIRMATION,
            CONFIRM_NUMBER_POP_UP,
            EDIT_NUMBER_POP_UP,
            OTP_VERIFICATION_EDIT_NUMBER_BUTTON,
            OTP_VERIFICATION_NUMBERS,
            OTP_RESEND_BUTTON,
            OTP_RESEND_COUNTER,
            ACCOUNT_INFO_TITLE,
            ACCOUNT_INFO_ADD_PHOTO_BUTTON,
            ACCOUNT_INFO_TAKE_PHOTO_BUTTON,
            ACCOUNT_INFO_CHOOSE_PHOTO_BUTTON,
            ACCOUNT_INFO_TOUCH_OUTSIDE_TO_CLOSE_ADDING_PHOTO,
            ACCOUNT_INFO_PROFILE_NAME,
            ACCOUNT_INFO_WHATS_ON_YOUR_MIND,
            ACCOUNT_INFO_COMPLETE_REGISTRATION;

    public RegistrationUIPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForNotificationPermissionPopUp() {
        this.waitForElementPresent(
                NOTIFICATION_PERMISSION_POP_UP,
                "Can't find notification permission pop-up",
                10
        );
    }

    public void allowNotificationsPermission() {
        this.waitForElementAndClick(
                ALLOW_NOTIFICATIONS,
                "Can't tap on allow notifications button",
                10
        );
    }

    public void denyNotificationsPermission() {
        this.waitForElementAndClick(
                DENY_NOTIFICATIONS,
                "Can't tap on deny notifications button",
                10
        );
    }
    public void denyNotificationsPermissionAgain() {
        this.waitForElementAndClick(
                DENY_NOTIFICATIONS_AGAIN,
                "Can't tap on deny notifications button",
                10
        );
    }


    public void waitForCallsPermissionPopUp() {
        this.waitForElementPresent(
                CALL_NOTIFICATIONS_PERMISSION_POP_UP,
                "Can't find notification permission pop-up",
                10
        );
    }

    public void allowCallsPermission() {
        this.waitForElementAndClick(
                ALLOW_CALL_NOTIFICATIONS,
                "Can't tap on allow calls button",
                10
        );
    }

    public void denyCallsPermission() {
        this.waitForElementAndClick(
                DENY_CALL_NOTIFICATIONS,
                "Can't tap on deny calls button",
                10
        );
    }

    public void openTermsOfUse() {
        this.waitForElementAndClick(
                TERMS_OF_USE_LINK,
                "Can't find and tap terms of use",
                10
        );
    }

    public void scrollTermsOfUse() {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).index(0)).scrollIntoView(new UiSelector().text(\"Contact us If you would like to send us your feedback on BiP, You can contact us through the “More > Settings > Help > BiP Support“ option.\"))"));
        //What's On This Page?
    }
    public void tapCountryCode() {
        this.waitForElementAndClick(
                COUNTRY_CODE,
                "Can't find and tap on country code",
                10
        );
    }
    public void closeTermsOfUse() {
        if (Platform.getInstance().isAndroid()) {
            driver.navigate().back();      }
    }

    public void searchCountryCode() {
        this.waitForElementAndClick(
                COUNTRY_CODE_SEARCH_INIT,
                "Can't tap on the search button",
                10
        );
        this.waitForElementAndSendKeys(
                COUNTRY_CODE_SEARCH_INPUT,
                "Turkey",
                10
        );
        this.waitForElementAndClick(
                COUNTRY_CODE_SELECTION,
                "Can't find and tap on searched country",
                10
        );
    }
    public void enterPhoneNumberAndConfirm(String msisdn) {
        this.waitForElementAndSendKeys(
                PHONE_NUMBER_ENTER,
                msisdn,
                10

        );
        this.waitForElementAndClick(
                REGISTER_BUTTON,
                "Can't find and tap register button",
                10
        );
        this.waitForElementAndClick(
                CONFIRM_NUMBER_POP_UP,
                "Can't confirm pop-up",
                10
        );
    }
    public void enterOTPCode(String otp) {
        this.waitForElementAndSendKeys(
                OTP_VERIFICATION_NUMBERS,
                otp,
                10
        );
    }
    public void enterUserNameInfoCompleteRegistration(String name, String info) {
        this.waitForElementAndSendKeys(
                ACCOUNT_INFO_PROFILE_NAME,
                name,
                10
        );
        this.waitForElementAndSendKeys(
                ACCOUNT_INFO_WHATS_ON_YOUR_MIND,
                info,
                10
        );
        this.waitForElementAndClick(
                ACCOUNT_INFO_COMPLETE_REGISTRATION,
                "Can't complete registration",
                10
        );
    }
    public void waitAndSkipNotificationConfirmationPOPup() {
        this.waitForElementAndClick(
                NOT_NOW_NOTIFICATION_ALLOW_CONFIRMATION_POP_UP,
                "Can't find and tap on the Not now button",
                10
        );
    }


}