package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.annotation.SuppressLint;

import java.util.List;

import JvSi.ShanJi.com.English.bean.ClassifyBean;


/**
 * Created by zhao
 */
public interface EnglishContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess(List<ClassifyBean> result);
        void onGetMoreSuccess(List<ClassifyBean> result);
        void onLoginFail(String errorTip);
    }

    interface Presenter{
        void getClassify(String classify,int pageSize,int pageNo);

        @SuppressLint("CheckResult")
        void sousuoWord(String classify, int pageSize, int pageNo);
    }
}
