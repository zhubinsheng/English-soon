package gdyj.tydic.com.jinlingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import gdyj.tydic.com.jinlingapp.ui.UserSet.PhoneLoginPresenter;

/**
 * @author Administrator
 */
public class tianxieziliaoAcitivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @OnClick({R.id.quedingtijiao})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quedingtijiao:
                Intent intent = new Intent(tianxieziliaoAcitivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

                default:
                    break;
        }
    }
}
