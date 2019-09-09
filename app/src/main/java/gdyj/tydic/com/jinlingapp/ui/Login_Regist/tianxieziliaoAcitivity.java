package gdyj.tydic.com.jinlingapp.ui.Login_Regist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import gdyj.tydic.com.jinlingapp.Base.BaseActivity;
import gdyj.tydic.com.jinlingapp.Base.MainActivity;
import gdyj.tydic.com.jinlingapp.R;

/**
 * @author Administrator
 */
public class tianxieziliaoAcitivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianxieziliao);

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
