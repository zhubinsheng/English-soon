package gdyj.tydic.com.jinlingapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.mob.MobSDK;

/**
 * Created by zhao
 */

public class MyApplication extends Application {

    private static MyApplication mContext;
    private static Context context;
    private static MyApplication instance;
    private String jwt;
    private Boolean hasjwt = false;

    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
        Stetho.initializeWithDefaults(this);
        mContext = this;
        instance = this;
        context=getApplicationContext();
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
}

