package gdyj.tydic.com.jinlingapp.ui.UserSet;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;

import gdyj.tydic.com.jinlingapp.Base.MyApplication;
import gdyj.tydic.com.jinlingapp.Base.MyRetrofitManager;
import gdyj.tydic.com.jinlingapp.bean.BaseInfo;
import gdyj.tydic.com.jinlingapp.bean.BaseResult;
import gdyj.tydic.com.jinlingapp.bean.LoginResilt;
import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;
import gdyj.tydic.com.jinlingapp.bean.SysUser;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

import static gdyj.tydic.com.jinlingapp.ui.MainFragment.TAG;

/**
 * Created by
 */
public class PhoneLoginPresenter implements PhoneLoginContract.Presenter {

    private PhoneLoginContract.View mView;
    private final LoginApi mPhoneLoginApi;

    public PhoneLoginPresenter(PhoneLoginContract.View view){
        this.mView = view;
        mPhoneLoginApi = MyRetrofitManager.create(LoginApi.class);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getValideCode(String phoneNumber) {
        Observable<BaseInfo> observable = mPhoneLoginApi.getValidCode(phoneNumber);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseInfo>() {
                    @Override
                    public void accept(BaseInfo baseInfo) throws Exception {
                        if(mView!=null){
                            mView.onValidCodeSend();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                            mView.onLoginFail("验证码发送失败，请稍后重试");
                        }
                    }
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void PhoneLogin(SysLoginModel sysLoginModel) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(sysLoginModel);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),requestBody);
        Observable<LoginResilt> observable = mPhoneLoginApi.loginByValidCode(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResilt>() {
                    @Override
                    public void accept(LoginResilt loginResult) throws Exception {
                        if(loginResult!=null && loginResult.getResult()!=null){
                                String id = loginResult.getResult().getUserInfo().getId();
                                //LoginUtil.getInstance().setLoginStatus(true);
                                //LoginUtil.getInstance().setUserId(String.valueOf(id));
                                MyApplication.getInstance().setHasjwt(true);
                                MyApplication.getInstance().setJwt(loginResult.getResult().getToken());
                                if(mView!=null){
                                    mView.onLoginSuccess();
                                }
                                return;

                        }
                        if(mView!=null){
                            if(loginResult!=null && loginResult.getMessage()!=null){
                                mView.onLoginFail(loginResult.getMessage());
                            }else{
                                //mView.onLoginFail(MyApplication.getAppContext().getString(R.string.login_fail_tip));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                            //mView.onLoginFail(MyApplication.getAppContext().getString(R.string.login_fail_tip));
                        }
                    }
                });
    }

    @Override
    public void PhoneRegister(SysLoginModel sysLoginModel) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(sysLoginModel);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),requestBody);
        Observable<BaseResult<SysUser>> observable = mPhoneLoginApi.PhoneRegister(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResult<SysUser>>() {
                    @Override
                    public void accept(BaseResult<SysUser> loginResult) throws Exception {
                        Log.e(TAG,"4getApplication is "+loginResult);
                        if(mView!=null){
                            mView.onRegisterSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                                mView.onRegisterFail("注册失败，网络问题");
                        }
                    }
                });
    }

    public void onDetach(){
        this.mView = null;
    }
}
