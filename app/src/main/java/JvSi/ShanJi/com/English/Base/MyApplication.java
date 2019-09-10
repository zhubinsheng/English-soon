package JvSi.ShanJi.com.English.Base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.xuexiang.xui.XUI;
import com.zhy.changeskin.SkinManager;

import JvSi.ShanJi.com.English.bean.LoginResilt;
import JvSi.ShanJi.com.English.bean.MyObjectBox;
import io.objectbox.BoxStore;

/**
 * @author binshengzhu
 */

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static MyApplication mContext;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static MyApplication instance;
    private String jwt;
    private String uploadt;
    private Boolean hasjwt = false;
    private BoxStore boxStore;
    private LoginResilt.ResultBean.UserInfoBean userInfoBean;

    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
        context=getApplicationContext();

        MultiDex.install(this);
        //MobSDK.init(this);
        SkinManager.getInstance().init(this);
        initObjectBox();
        //Stetho.initializeWithDefaults(this);
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志


    }

    public static Application getAppContext() {
        return mContext;
    }
    public static Context getContext(){
        return context;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Boolean getHasjwt() {
        return hasjwt;
    }

    public void setHasjwt(Boolean hasjwt) {
        this.hasjwt = hasjwt;
    }

    private void initObjectBox() {
        //第一次没运行之前，MyObjectBox默认会有报错提示，可以忽略。创建实体类， make之后报错就会不提示
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public String getUploadt() {
        return uploadt;
    }

    public void setUploadt(String uploadt) {
        this.uploadt = uploadt;
    }

    public LoginResilt.ResultBean.UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(LoginResilt.ResultBean.UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
}

