package JvSi.ShanJi.com.English.ui.Classify;

import android.annotation.SuppressLint;

import java.util.List;
import java.util.Map;

import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.Library;

/**
 * Created by zhao
 */
public interface ClassifyContract {
    interface View{
        void onValidCodeSend();
        void onLoginSuccess(List<ClassifyBean> loginResult);
        void onLoginFail(String errorTip);

        void onGetLibrarySuccess(Map<String, List<Library>> result);
    }

    interface Presenter{
        void getClassify();

        @SuppressLint("CheckResult")
        void getLibrary();
    }
}
