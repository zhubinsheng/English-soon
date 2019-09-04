package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import android.annotation.SuppressLint;

import gdyj.tydic.com.jinlingapp.bean.EnglishCodeVo;


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
