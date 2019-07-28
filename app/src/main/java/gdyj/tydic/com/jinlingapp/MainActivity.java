package gdyj.tydic.com.jinlingapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.zhy.changeskin.SkinManager;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.baiduUtils.TTSUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SkinManager.getInstance().register(this);

        mTabLayout = (TabLayout) findViewById(R.id.testFragmentTablayout);
        mViewPager = (MyCustomViewPager) findViewById(R.id.testFragmentViewPager);
        // 创建Fragment集合
        List<Fragment> fragments = new ArrayList<>();
        // 将Fragment添加到集合
        fragments.add(new ClassifyhFragment());
        fragments.add(new EnglishFragment());
        fragments.add(new UserSetFragment());
        fragments.add(new MainFragment());
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
        //SkinManager.getInstance().unregister(this);
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
}


