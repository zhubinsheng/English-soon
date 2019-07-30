package gdyj.tydic.com.jinlingapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;
import com.zhy.changeskin.SkinManager;
import gdyj.tydic.com.jinlingapp.bean.MyObjectBox;
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
    private Boolean hasjwt = false;
    private BoxStore boxStore;

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
        MobSDK.init(this);
        SkinManager.getInstance().init(this);
        initObjectBox();
        //Stetho.initializeWithDefaults(this);

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
}

