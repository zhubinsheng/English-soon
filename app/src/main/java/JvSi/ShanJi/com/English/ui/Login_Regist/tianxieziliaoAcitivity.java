package JvSi.ShanJi.com.English.ui.Login_Regist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import JvSi.ShanJi.com.English.Base.BaseActivity;
import JvSi.ShanJi.com.English.Base.MainActivity;
import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.ClassResult;
import JvSi.ShanJi.com.English.bean.SysRegisterInfoModel;
import JvSi.ShanJi.com.English.ui.UserSet.PhoneLoginContract;
import JvSi.ShanJi.com.English.ui.UserSet.PhoneLoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Administrator
 */
public class tianxieziliaoAcitivity extends BaseActivity implements PhoneLoginContract.View {

    private PhoneLoginPresenter mLoginPresenter;
    private Unbinder unbinder;

    @BindView(R.id.xingming)
    EditText xingming;

    @BindView(R.id.banji)
    EditText banji;

    @BindView(R.id.banjichaxun)
    TextView banjichaxun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianxieziliao);
        unbinder = ButterKnife.bind(this);
        mLoginPresenter = new PhoneLoginPresenter();
    }

    @OnClick({R.id.quedingtijiao,R.id.banjichaxun})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quedingtijiao:
                SysRegisterInfoModel sysLoginModel = new SysRegisterInfoModel();
                sysLoginModel.setClassId(banji.getText().toString());
                sysLoginModel.setId(MyApplication.getInstance().getUserInfoBean().getId());
                mLoginPresenter.registerInfo(sysLoginModel);

                Intent intent = new Intent(tianxieziliaoAcitivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.banjichaxun:
                mLoginPresenter.getValideCode(banji.getText().toString());
                break;
                default:
                    break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onRegisterFail(String errorTip) {

    }

    @Override
    public void onGetValideCode(ClassResult baseInfo) {

    }
}
