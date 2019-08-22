package gdyj.tydic.com.jinlingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.Utils.ValideUtil;
import gdyj.tydic.com.jinlingapp.bean.SysLoginModel;
import gdyj.tydic.com.jinlingapp.ui.UserSet.PhoneLoginContract;
import gdyj.tydic.com.jinlingapp.ui.UserSet.PhoneLoginPresenter;

@SuppressLint("Registered")
public class LoginActivity extends BaseActivity implements PhoneLoginContract.View {


    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.mima)
    TextView editText3;
    @BindView(R.id.jizhumima)
    CheckBox checkBox2;
    @BindView(R.id.wangjimima)
    TextView textView10;
    @BindView(R.id.denglu)
    TextView next;

    private PhoneLoginPresenter mLoginPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new PhoneLoginPresenter(this);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.wangjimima,R.id.denglu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.denglu:
                if(/*ValideUtil.isMobilePhone(String.valueOf(phone.getText()))||*/phone.getText().length()!=11){
                    Toasty.warning(this,"请输入正确的手机号码").show();
                    return;
                }
                if(editText3.getText()==null){
                    Toasty.warning(this,"请输入密码").show();
                    return;
                }
                SysLoginModel sysLoginModel = new SysLoginModel();
                sysLoginModel.setCaptcha("123");
                sysLoginModel.setUsername(phone.getText().toString());
                sysLoginModel.setPassword(editText3.getText().toString());
                mLoginPresenter.PhoneLogin(sysLoginModel);

                break;
            case R.id.wangjimima:
                sendCode(this);
                //startActivity(RegisterMiMaActivity.class);
                  break;
            default:
                break;
        }
    }

    @Override
    public void onValidCodeSend() {

    }

    @Override
    public void onLoginSuccess() {
        Toasty.success(this,"登录成功").show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFail(String errorTip) {

    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onRegisterFail(String errorTip) {

    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作

                    /*SysLoginModel sysLoginModel = new SysLoginModel();
                    sysLoginModel.setCaptcha("123");
                    sysLoginModel.setUsername("admin19");
                    sysLoginModel.setPassword("123456");
                    mLoginPresenter.PhoneRegister(sysLoginModel);*/
                    Intent intent = new Intent(LoginActivity.this, tianxieziliaoAcitivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    // TODO 处理错误的结果
                    Toasty.warning(LoginActivity.this,"注册失败").show();
                }
            }
        });
        page.show(context);
    }
}
