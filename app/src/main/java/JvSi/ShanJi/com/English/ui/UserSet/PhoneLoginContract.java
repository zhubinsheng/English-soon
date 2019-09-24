package JvSi.ShanJi.com.English.ui.UserSet;

import android.annotation.SuppressLint;

import JvSi.ShanJi.com.English.bean.ClassResult;
import JvSi.ShanJi.com.English.bean.ListClasssStudyResult;
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

        void onGetValideCode(ClassResult baseInfo);

        void onRegisterInfoSuccess();

        void onGetValideCodeStudyResult(ListClasssStudyResult baseInfo);
    }

    interface Presenter{
        @SuppressLint("CheckResult")
        void queryByClasss(String classs);

        @SuppressLint("CheckResult")
        void queryListClasss(String classs);

        void getValideCode(String phoneNumber);

        @SuppressLint("CheckResult")
        void CLASSadd(String classs, String userID);

        void PhoneLogin(SysLoginModel sysLoginModel);
        void PhoneRegister(SysLoginModel sysLoginModel);
        void registerInfo(SysRegisterInfoModel sysLoginModel);
    }
}
