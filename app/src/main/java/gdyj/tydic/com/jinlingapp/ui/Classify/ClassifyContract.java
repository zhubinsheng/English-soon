package gdyj.tydic.com.jinlingapp.ui.Classify;

import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;

/**
 * Created by zhao
 */
public interface ClassifyContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess();
        void onLoginFail(String errorTip);
    }

    interface Presenter{
        void getClassify(String token);
    }
}
