package JvSi.ShanJi.com.English.Base;

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
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.zhy.changeskin.SkinManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.Utils.BlurUtil;
import JvSi.ShanJi.com.English.Utils.SharedPreferencesUtils;
import JvSi.ShanJi.com.English.baiduUtils.TTSUtils;
import JvSi.ShanJi.com.English.bean.SysLoginModel;
import JvSi.ShanJi.com.English.bean.SysRegisterInfoModel;
import JvSi.ShanJi.com.English.ui.Classify.ClassifyMessageEvent;
import JvSi.ShanJi.com.English.ui.EnglishWord.EnglishFragment;
import JvSi.ShanJi.com.English.ui.Fuxi.FuxiFragment;
import JvSi.ShanJi.com.English.ui.UserSet.PhoneLoginPresenter;
import JvSi.ShanJi.com.English.ui.UserSet.UserSetFragment;
import es.dmoral.toasty.Toasty;
import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * @author binshengzhu
 */
public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_ADDRESS = 100;
    private PhoneLoginPresenter mLoginPresenter;
    private boolean backc = false;
    TabLayout mTabLayout;
    MyCustomViewPager mViewPager;
    LinearLayout mainskin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TTSUtils.getInstance().init();
        /*//默认API 最低19
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_blue));
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = findViewById(android.R.id.content);
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
            statusBarView.setBackgroundColor(getResources().getColor(R.color.color_blue));
            systemContent.getChildAt(0).setFitsSystemWindows(true);
            systemContent.addView(statusBarView, 0, lp);
        }*/
        mLoginPresenter = new PhoneLoginPresenter();
        String mima  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(this,"mima",""));
        String zhanghao  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(this,"phone",""));
        if (!mima.isEmpty()&&!zhanghao.isEmpty()){
            SysLoginModel sysLoginModel = new SysLoginModel();
            sysLoginModel.setCaptcha("123");
            sysLoginModel.setUsername(zhanghao);
            sysLoginModel.setPassword(mima);
            mLoginPresenter.PhoneLogin(sysLoginModel);
        }
        EventBus.getDefault().register(this);
        SkinManager.getInstance().register(this);
        String resultUristring  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(this,"resultUris",""));
        //mTabLayout = (TabLayout) findViewById(R.id.testFragmentTablayout);
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
            setBlurBackground(bit,20);
        }
        // 创建Fragment集合
        List<Fragment> fragments = new ArrayList<>();
        // 将Fragment添加到集合
        //fragments.add(new ClassifyhFragment());
        //fragments.add(new EnglishFragment());
        fragments.add(new UserSetFragment());
        fragments.add(new EnglishFragment());
        fragments.add(new FuxiFragment());

        // 初始化适配器
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);
        // 设置ViewPager
        //mTabLayout.setupWithViewPager(mViewPager);
        new Thread(){
            @Override
            public void run() {
                //需要在子线程中处理的逻辑
                initPermission();

            }
        }.start();
       // BadgeUtil.setBadgeCount(getApplicationContext(), 2, R.mipmap.tubiao);

        int badgeCount = 1;
        ShortcutBadger.applyCount(this, badgeCount);
        //for 1.1.4+
        //ShortcutBadger.with(getApplicationContext()).count(badgeCount);
        //for 1.1.3
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SkinManager.getInstance().unregister(this);
    }
    @Override
    protected void onResume() {
        asd(1);
        super.onResume();
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
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR
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
            setBlurBackground(bit,20);
        }else if (message.getRecode().equals("res")){
            SysRegisterInfoModel sysLoginModel = new SysRegisterInfoModel();
            sysLoginModel.setAvatar(message.getClassify());
            mLoginPresenter.registerInfo(sysLoginModel);
        }else if (message.getRecode().equals("qiehuan")){
            asd(Integer.parseInt(message.getClassify()));
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
        final Bitmap blurBmp2 = BlurUtil.fastblur(this, blurBmp, i);
        final Bitmap blurBmp3 = BlurUtil.fastblur(this, blurBmp2, i);
        BitmapDrawable drawable = new BitmapDrawable(blurBmp3);
        mainskin.setBackgroundDrawable(drawable);
    }
    private void asd(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if (backc){
                finish();
            }else {
                asd(1);
                backc = true;
            }
        }
        return true;
    }
}