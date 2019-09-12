package JvSi.ShanJi.com.English.ui.UserSet;

import JvSi.ShanJi.com.English.bean.SysLoginModel;
import JvSi.ShanJi.com.English.bean.SysRegisterInfoModel;

/**
 * Created by zhao
 */
public interface PhoneLoginContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess();
        void onLoginFail(String errorTip);
        void onRegisterSuccess();
        void onRegisterFail(String errorTip);
    }

    interface Presenter{
        void getValideCode(String phoneNumber);
        void PhoneLogin(SysLoginModel sysLoginModel);
        void PhoneRegister(SysLoginModel sysLoginModel);
        void registerInfo(SysRegisterInfoModel sysLoginModel);
    }
}