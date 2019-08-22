package gdyj.tydic.com.jinlingapp.ui.UserSet;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.baiduUtils.TTSUtils;
import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;

/**
 * @author zbs
 */
public class UserSetFragment extends Fragment implements PhoneLoginContract.View{
    private View layout;
    private Unbinder unbinder;
    private PhoneLoginPresenter mLoginPresenter;

    @BindView(R.id.button)
    Button login;
    @BindView(R.id.button1)
    Button login1;
    @BindView(R.id.button2)
    Button login2;
    @BindView(R.id.title)
    TextView title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter = new PhoneLoginPresenter(this);
        SkinManager.getInstance().register(getActivity());
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.user_set, container, false);
        unbinder = ButterKnife.bind(this,layout);
        StateListAnimator animator = AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.anim_state_list_m01);
        //Drawable mDrawable = LayoutToDrawable(R.drawable.foreground_selector);
        //StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.foreground_selector);
        login.setStateListAnimator(animator);
        login.setForeground(getResources().getDrawable(R.drawable.foreground_selector));
        //login.setBackgroundResource(R.drawable.foreground_selector);
        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initAdapter();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mLoginPresenter.onDetach();
        SkinManager.getInstance().unregister(getActivity());
    }

    private void initAdapter() {
    }

    private void initData() {
       /* new Thread(){
            @Override
            public void run() {
                //需要在子线程中处理的逻辑
                TTSUtils.getInstance().init();
                //initialTts(); // 初始化TTS引擎
            }
        }.start();*/
    }

    private void initView() {
        setTitle("个 人 中 心");
    }
    private void setTitle(String msg) {
        if (title != null) {
            title.setText(msg);
        }
    }


    @OnClick({R.id.button, R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                //speak();
                //  String phone = mEdtPhone.getText().toString();


                SysLoginModel sysLoginModel = new SysLoginModel();
                sysLoginModel.setCaptcha("123");
                sysLoginModel.setUsername("admin19");
                sysLoginModel.setPassword("123456");
                mLoginPresenter.PhoneLogin(sysLoginModel);

                break;
            case R.id.button1:
                //sendCode(getActivity());
                /*SysLoginModel sysLoginModel1 = new SysLoginModel();
                sysLoginModel1.setCaptcha("123");
                sysLoginModel1.setUsername("admin19");
                sysLoginModel1.setPassword("123456");
                mLoginPresenter.PhoneRegister(sysLoginModel1);*/

                break;
            case R.id.button2:

                break;

                default:break;
        }
    }

    @Override
    public void onValidCodeSend() {

    }

    @Override
    public void onLoginSuccess() {
        Toasty.success(getContext(),"登录成功").show();

    }

    @Override
    public void onLoginFail(String errorTip) {

    }

    @Override
    public void onRegisterSuccess() {
        Toasty.success(getContext(),"注册成功").show();
    }

    @Override
    public void onRegisterFail(String errorTip) {

    }



    private void speak() {
        String text = "onSynthesizeFinish";
        // 需要合成的文本text的长度不能超过1024个GBK字节。
        /*if (TextUtils.isEmpty(mInput.getText())) {
            text = "百度语音，面向广大开发者永久免费开放语音合成技术。";
            mInput.setText(text);
        }
        // 合成前可以修改参数：
        // Map<String, String> params = getParams();
        // synthesizer.setParams(params);
        int result = synthesizer.speak(text);
        checkResult(result, "speak");*/
        TTSUtils.getInstance().speak(text);
    }
}
