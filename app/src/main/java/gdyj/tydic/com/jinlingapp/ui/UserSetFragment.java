package gdyj.tydic.com.jinlingapp.ui;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.EnglishAdapter;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;
import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;
import gdyj.tydic.com.jinlingapp.net.PersonalProtocol;
import gdyj.tydic.com.jinlingapp.present.PhoneLoginContract;
import gdyj.tydic.com.jinlingapp.present.PhoneLoginPresenter;
import gdyj.tydic.com.jinlingapp.utils.TTSUtils;

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
    @BindView(R.id.title)
    TextView title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter = new PhoneLoginPresenter(this);
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


    @OnClick({R.id.button, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                speak();
                //  String phone = mEdtPhone.getText().toString();
               /* String phone = "123";
                String validNumber = "321";
                SysLoginModel sysLoginModel = new SysLoginModel();
                sysLoginModel.setCaptcha("123");
                sysLoginModel.setUsername("admin19");
                sysLoginModel.setPassword("123456");
                mLoginPresenter.PhoneLogin(sysLoginModel);*/
                break;
            case R.id.button1:
                //sendCode(getActivity());
                SysLoginModel sysLoginModel1 = new SysLoginModel();
                sysLoginModel1.setCaptcha("123");
                sysLoginModel1.setUsername("admin19");
                sysLoginModel1.setPassword("123456");
                mLoginPresenter.PhoneRegister(sysLoginModel1);
                break;
        }
    }

    @Override
    public void onValidCodeSend() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFail(String errorTip) {

    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
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
