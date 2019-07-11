package gdyj.tydic.com.jinlingapp.present;

import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;

/**
 * Created by zhao
 */
public interface PhoneLoginContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess();
        void onLoginFail(String errorTip);
    }

    interface Presenter{
        void getValideCode(String phoneNumber);
        void PhoneLogin(SysLoginModel sysLoginModel);
        void PhoneRegister(SysLoginModel sysLoginModel);
    }
}
