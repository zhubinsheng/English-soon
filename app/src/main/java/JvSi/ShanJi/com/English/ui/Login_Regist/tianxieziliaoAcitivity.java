package JvSi.ShanJi.com.English.ui.Login_Regist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import JvSi.ShanJi.com.English.Base.BaseActivity;
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
import es.dmoral.toasty.Toasty;

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

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    ;

    private ClassAdapter classAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianxieziliao);
        unbinder = ButterKnife.bind(this);
        if (!MyApplication.getInstance().getHasjwt()){
            finish();
        }
        mLoginPresenter = new PhoneLoginPresenter(this);
    }

    @OnClick({R.id.quedingtijiao,R.id.banjichaxun})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quedingtijiao:
                SysRegisterInfoModel sysLoginModel = new SysRegisterInfoModel();
                sysLoginModel.setClassId(banji.getText().toString());
                sysLoginModel.setId(MyApplication.getInstance().getUserInfoBean().getId());
                mLoginPresenter.registerInfo(sysLoginModel);


                break;
            case R.id.banjichaxun:
                mLoginPresenter.queryByClasss(banji.getText().toString());
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
        Toasty.success(this,"请勿重复添加！").show();
    }

    @Override
    public void onLoginSuccess() {
        Toasty.success(this,"添加成功！").show();
        mLoginPresenter.queryByClasss(banji.getText().toString());
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.success(this,"该班级为未创建！"+errorTip).show();
    }

    @Override
    public void onRegisterSuccess() {
        Toasty.success(this,"更新成功！").show();
        //Intent intent = new Intent(tianxieziliaoAcitivity.this, MainActivity.class);
        //startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterFail(String errorTip) {

    }

    @Override
    public void onGetValideCode(ClassResult baseInfo) {
        if (baseInfo.getCode()==500){
            Toasty.success(this,"该班级未创建！").show();
            new MaterialDialog.Builder(this)
                    .content("是否建立该班级")
                    .positiveText(R.string.lab_yes)
                    .negativeText(R.string.lab_no)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            mLoginPresenter.CLASSadd(banji.getText().toString(),MyApplication.getInstance().getUserInfoBean().getId());
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            super.onNegative(dialog);
                        }
                    })
                    .show();
            return;
        }
        classAdapter = new ClassAdapter(R.layout.class_item, baseInfo.getResult());
        classAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.button2) {
                    mLoginPresenter.CLASSadd(baseInfo.getResult().get(position).getClasss(),MyApplication.getInstance().getUserInfoBean().getId());
                    banji.setText(baseInfo.getResult().get(position).getClasss());
                }
            }
        });
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(classAdapter);
    }

    @Override
    public void onRegisterInfoSuccess() {

    }
}
