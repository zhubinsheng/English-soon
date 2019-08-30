package gdyj.tydic.com.jinlingapp.Base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.zhy.changeskin.SkinManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.Utils.BlurUtil;
import gdyj.tydic.com.jinlingapp.Utils.SharedPreferencesUtils;
import gdyj.tydic.com.jinlingapp.baiduUtils.TTSUtils;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyMessageEvent;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyhFragment;
import gdyj.tydic.com.jinlingapp.ui.EnglishWord.EnglishFragment;
import gdyj.tydic.com.jinlingapp.ui.MainFragment;
import gdyj.tydic.com.jinlingapp.ui.UserSet.UserSetFragment;

/**
 * @author binshengzhu
 */
public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_ADDRESS = 100;
    TabLayout mTabLayout;
    MyCustomViewPager mViewPager;
    LinearLayout mainskin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        SkinManager.getInstance().register(this);
        String resultUristring  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(this,"resultUris",""));
        mTabLayout = (TabLayout) findViewById(R.id.testFragmentTablayout);
        mViewPager = (MyCustomViewPager) findViewById(R.id.testFragmentViewPager);
        mainskin = (LinearLayout) findViewById(R.id.mainskin);
        if (!resultUristring.isEmpty()){
            Uri resultUris = Uri.parse(resultUristring);
            Bitmap bit = null;
            try {
                bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUris));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setBlurBackground(bit,10);
        }
        // 创建Fragment集合
        List<Fragment> fragments = new ArrayList<>();
        // 将Fragment添加到集合
        fragments.add(new ClassifyhFragment());
        fragments.add(new EnglishFragment());
        fragments.add(new MainFragment());
        fragments.add(new UserSetFragment());

        // 初始化适配器
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter);
        // 设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);

        new Thread(){
            @Override
            public void run() {
                //需要在子线程中处理的逻辑
                initPermission();
            }
        }.start();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SkinManager.getInstance().unregister(this);
    }
    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                //Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), REQUEST_CODE_ADDRESS);
        }

    }

    @SuppressLint("CheckResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
        switch (requestCode) {
            case REQUEST_CODE_ADDRESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] ==PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 授予权限
                    //处理授权之后逻辑
                    TTSUtils.getInstance().init();
                } else {
                    // Permission Denied 权限被拒绝
                    Toasty.warning(this,"权限被禁止,无法播放声音").show();
                }
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(ClassifyMessageEvent message) {
        if (message.getRecode().equals("resultUris")){
            Bitmap bit = null;
            try {
                bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(message.getClassify())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setBlurBackground(bit,10);
        }
    }

    /**
     * 设置毛玻璃背景
     * 背景图片 Bitmap
     */
    private void setBlurBackground(Bitmap bmp , int i)
    {
        //0-25，表示模糊值 Radius out of range (0 < r <= 25).
        final Bitmap blurBmp = BlurUtil.fastblur(this, bmp, i);
        BitmapDrawable drawable = new BitmapDrawable(blurBmp);
        mainskin.setBackgroundDrawable(drawable);
    }
}


