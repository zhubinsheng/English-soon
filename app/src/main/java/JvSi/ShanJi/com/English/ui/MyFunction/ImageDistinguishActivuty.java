package JvSi.ShanJi.com.English.ui.MyFunction;

import android.content.Context;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.popupwindow.popup.XUIPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import JvSi.ShanJi.com.English.Base.BaseActivity;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.ui.Classify.ClassifyMessageEvent;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author binshengzhu
 */
public class ImageDistinguishActivuty extends BaseActivity {

    private Unbinder unbinder;
    private MyTextureView myTextureView;
    private Handler handler = new Handler( );
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_image_to_info);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        myTextureView = findViewById(R.id.mytextureview);
        findViewById(R.id.paizhai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        long curClickTime = System.currentTimeMillis();
                        Log.d("timebbb","curClickTime1"+curClickTime);
                        myTextureView.take();
                    }
                });
            }
        });
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);

        runnable = new Runnable( ) {
            @Override
            public void run ( ) {
                AsyncTask.execute(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        myTextureView.take();
                    }
                });
                //handler.postDelayed(this,1000);
            }
        };
        // 开始Timer
        handler.postDelayed(runnable,200);

        findViewById(R.id.yulan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTextureView.startPreview();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myTextureView.startPreview();
    }

    @Override
    protected void onStop() {
        myTextureView.stopPreview();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //myTextureView.releaseTextureView();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(ClassifyMessageEvent message) {
        if (message.getRecode().equals("imageInfo")){
            handler.postDelayed(runnable,200);
            /*new MaterialDialog.Builder(this)
                    .content(message.getClassify())
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            super.onNegative(dialog);
                        }
                    }).show();*/
            Gson gson = new Gson();
            AESResult requestBody = gson.fromJson(message.getClassify(),AESResult.class);
            if (requestBody.getErrno()!=0){
                return;
            }
            XUIPopup mNormalPopup = new XUIPopup(this);
            TextView textView = new TextView(this);
            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
                    DensityUtils.dp2px(this, 250),
                    WRAP_CONTENT
            ));
            textView.setLineSpacing(DensityUtils.dp2px(4), 1.0f);
            int padding = DensityUtils.dp2px(20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText(requestBody.getTags().get(0).getValue());
            textView.setTextColor(ContextCompat.getColor(this, R.color.calendar_head_background));
            mNormalPopup.setContentView(textView);
            mNormalPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
            mNormalPopup.setAnimStyle(XUIPopup.ANIM_GROW_FROM_RIGHT);
            mNormalPopup.setPreferredDirection(XUIPopup.DIRECTION_TOP);
            mNormalPopup.show(myTextureView);
        }
    }

}
