package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.annotation.SuppressLint;

import java.util.List;

import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.LearningSit;


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
//"classifyId": "string",
//  "count": "string",
//  "userid": "string",
        void addLearningSit(LearningSit learningSit);

        @SuppressLint("CheckResult")
        void sousuoWord(String classify, int pageSize, int pageNo);
    }
}
