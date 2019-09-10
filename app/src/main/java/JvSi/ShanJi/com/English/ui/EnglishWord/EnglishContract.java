package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.annotation.SuppressLint;

import JvSi.ShanJi.com.English.bean.EnglishCodeVo;


/**
 * Created by zhao
 */
public interface EnglishContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess(EnglishCodeVo.ResultBean resultBean);
        void onGetMoreSuccess(EnglishCodeVo.ResultBean resultBean);
        void onLoginFail(String errorTip);
    }

    interface Presenter{
        void getClassify(String classify,int pageSize,int pageNo);

        @SuppressLint("CheckResult")
        void sousuoWord(String classify, int pageSize, int pageNo);
    }
}
