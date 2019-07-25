package gdyj.tydic.com.jinlingapp.ui.Classify;

import java.util.List;

import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;

/**
 * Created by zhao
 */
public interface ClassifyContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess(List<ClassifyBean> loginResult);
        void onLoginFail(String errorTip);
    }

    interface Presenter{
        void getClassify();
    }
}
