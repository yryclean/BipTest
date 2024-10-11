package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.RegistrationUIPageObject;

public class AndroidRegistrationPageObject extends RegistrationUIPageObject {
    static {
        NOTIFICATION_PERMISSION_POP_UP = "id:com.android.permissioncontroller:id/grant_dialog";
        ALLOW_NOTIFICATIONS = "id:com.android.permissioncontroller:id/permission_allow_button";
        DENY_NOTIFICATIONS = "xpath://android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_deny_button']";
        DENY_NOTIFICATIONS_AGAIN = "id:com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button";
        CALL_NOTIFICATIONS_PERMISSION_POP_UP = "id:com.android.permissioncontroller:id/grant_dialog";
        ALLOW_CALL_NOTIFICATIONS = "id:com.android.permissioncontroller:id/permission_allow_button";
        DENY_CALL_NOTIFICATIONS = "id:com.android.permissioncontroller:id/permission_deny_button";
        NOT_NOW_NOTIFICATION_ALLOW_CONFIRMATION_POP_UP = "id:com.turkcell.bip:id/btnNotNow";
        OPEN_SETTINGS_NOTIFICATION_ALLOW_CONFIRMATION_POP_UP = "id:com.turkcell.bip:id/btnSettings";
        TERMS_OF_USE_LINK = "id:com.turkcell.bip:id/tv_terms";
        CONTACT_US_TERMS_OF_USE_READ = "xpath://android.widget.TextView[@text='Contact us If you would like to send us your feedback on BiP, You can contact us through the “More > Settings > Help > BiP Support“ option.']";
        CLOSE_TERMS_OF_USE = "id:com.turkcell.bip:id/touch_outside";
        PHONE_NUMBER_ENTER = "id:com.turkcell.bip:id/et_phone_number";
        COUNTRY_CODE = "id:com.turkcell.bip:id/tv_country_code";
        COUNTRY_CODE_SEARCH_INIT = "id:com.turkcell.bip:id/btn_toolbar_search";
        COUNTRY_CODE_SEARCH_INPUT = "id:com.turkcell.bip:id/et_country_search";
        COUNTRY_CODE_SEARCH_CLEAR_BUTTON = "id:com.turkcell.bip:id/btn_search_clear";
        COUNTRY_CODE_SELECTION = "xpath://android.widget.TextView[@resource-id='com.turkcell.bip:id/tvTitle' and @text='Turkey']";
        SELECTED_COUNTRY_CODE = "xpath://android.widget.TextView[@resource-id=com.turkcell.bip:id/tv_country_code' and @text='+90']";
        REGISTER_BUTTON = "id:com.turkcell.bip:id/btn_register_send";
        POP_UP_NUMBER_CONFIRMATION = "id:com.turkcell.bip:id/popup_container";
        CONFIRM_NUMBER_POP_UP = "id:com.turkcell.bip:id/btnPrimary";
        EDIT_NUMBER_POP_UP = "id:com.turkcell.bip:id/btnSecondary";
        OTP_VERIFICATION_EDIT_NUMBER_BUTTON = "id:com.turkcell.bip:id/tv_phone_number_edit";
        OTP_VERIFICATION_NUMBERS = "id:com.turkcell.bip:id/pass_edittext";
        OTP_RESEND_BUTTON = "id:com.turkcell.bip:id/re_send_text";
        OTP_RESEND_COUNTER = "xpath://android.widget.TextView[@resource-id=com.turkcell.bip:id/counter_text]";
        ACCOUNT_INFO_TITLE = "id:com.turkcell.bip:id/tv_profile_title";
        ACCOUNT_INFO_ADD_PHOTO_BUTTON = "id:com.turkcell.bip:id/tv_add_or_update_photo";
        ACCOUNT_INFO_TAKE_PHOTO_BUTTON = "id:com.turkcell.bip:id/tv_avatar_operation_name";
        ACCOUNT_INFO_CHOOSE_PHOTO_BUTTON = "id:com.turkcell.bip:id/tv_avatar_operation_name";
        ACCOUNT_INFO_TOUCH_OUTSIDE_TO_CLOSE_ADDING_PHOTO = "id:com.turkcell.bip:id/touch_outside";
        ACCOUNT_INFO_PROFILE_NAME = "id:com.turkcell.bip:id/et_username";
        ACCOUNT_INFO_WHATS_ON_YOUR_MIND = "id:com.turkcell.bip:id/et_status";
        ACCOUNT_INFO_COMPLETE_REGISTRATION = "id:com.turkcell.bip:id/btn_complete";
    }

    public AndroidRegistrationPageObject(AppiumDriver driver) {
        super(driver);
    }


}
