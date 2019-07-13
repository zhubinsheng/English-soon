package gdyj.tydic.com.jinlingapp.ui.Classify;

import gdyj.tydic.com.jinlingapp.bean.ClassifyL;
import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;

/**
 * Created by zhao
 */
public interface ClassifyContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess(ClassifyL loginResult);
        void onLoginFail(String errorTip);
    }

    interface Presenter{
        void getClassify(String token);
    }
}
