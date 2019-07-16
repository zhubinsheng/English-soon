package gdyj.tydic.com.jinlingapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;

/**
 * Created by z
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


        mContext = this;
        instance = this;
        context=getApplicationContext();

        MultiDex.install(this);
        MobSDK.init(this);
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
}

