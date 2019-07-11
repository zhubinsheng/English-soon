package gdyj.tydic.com.jinlingapp.ui.Classify;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import gdyj.tydic.com.jinlingapp.MyApplication;
import gdyj.tydic.com.jinlingapp.bean.BaseInfo;
import gdyj.tydic.com.jinlingapp.bean.ClassifyL;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;
import gdyj.tydic.com.jinlingapp.bean.LoginResult;
import gdyj.tydic.com.jinlingapp.bean.Result;
import gdyj.tydic.com.jinlingapp.bean.ResultBean;
import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;
import gdyj.tydic.com.jinlingapp.bean.SysUser;
import gdyj.tydic.com.jinlingapp.net.RetrofitManager;
import gdyj.tydic.com.jinlingapp.present.LoginApi;
import gdyj.tydic.com.jinlingapp.present.PhoneLoginContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

import static gdyj.tydic.com.jinlingapp.ui.MainFragment.TAG;

/**
 * Created by
 */
public class ClassifyPresenter implements ClassifyContract.Presenter {

    private ClassifyContract.View mView;
    private final ClassifyApi classifyApi;

    public ClassifyPresenter(ClassifyContract.View view){
        this.mView = view;
        classifyApi = RetrofitManager.create(ClassifyApi.class);
    }


    public void onDetach(){
        this.mView = null;
    }

    @Override
    public void getClassify(String token) {
        Observable<ClassifyL> observable = classifyApi.GetClassify(token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ClassifyL>() {
                    @Override
                    public void accept(ClassifyL loginResult) throws Exception {
                        Log.e(TAG,"4getApplication is "+loginResult);
                        if(mView!=null){
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                            //    mView.onLoginFail(MyApplication.getAppContext().getString(R.string.login_fail_tip));
                        }
                    }
                });
    }
}
