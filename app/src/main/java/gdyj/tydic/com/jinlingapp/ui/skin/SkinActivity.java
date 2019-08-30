package gdyj.tydic.com.jinlingapp.ui.skin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.yalantis.ucrop.UCrop;
import com.zhy.changeskin.SkinManager;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.Utils.BlurUtil;
import gdyj.tydic.com.jinlingapp.Utils.SharedPreferencesUtils;
import gdyj.tydic.com.jinlingapp.Utils.jianchaiUtil;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyMessageEvent;

/**
 * @author binshengzhu
 */
public class SkinActivity extends AppCompatActivity {

    private static final int REQUEST_SELECT_PICTURE = 101;

    private static Uri resultUris = null ;

    private Unbinder unbinder;

    private Bitmap  bmp = null ;
    private Bitmap bit = null;

    @BindView(R.id.beijing)
    LinearLayout beijing;

    @BindView(R.id.skinbeijing)
    LinearLayout skinbeijing;

    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);
        unbinder = ButterKnife.bind(this);
        SkinManager.getInstance().register(this);
        String resultUristring  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(this,"resultUris",""));
        if (resultUristring.isEmpty()){
            bmp = BitmapFactory.decodeResource(getResources(),R.drawable.beijing );
        }else {
             resultUris = Uri.parse(resultUristring);
            try {
                bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUris));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setBlurBackground(bit,10);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                switch (seekBar.getProgress()) {
                    case 0:

                        break;




                    default:
                        if (resultUristring.isEmpty()){
                            setBlurBackground(bmp,seekBar.getProgress());
                        }else {
                            try {
                                bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUris));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            setBlurBackground(bit,seekBar.getProgress());
                        }



                        break;
                }

            }
        });




    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }

    @OnClick({R.id.genghuanbeijing,R.id.hongse,R.id.textView27})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.genghuanbeijing:
                Intent intent3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent3, REQUEST_SELECT_PICTURE);
                break;
            case R.id.hongse:
                SkinManager.getInstance().changeSkin("red");
                break;
            case R.id.textView27:
                EventBus.getDefault().post(ClassifyMessageEvent.getInstance("resultUris",resultUris.toString()));
                finish();
                break;
            default:break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            resultUris = resultUri;
            SharedPreferencesUtils.setParam(this,"resultUris",resultUris.toString());
            try {
                Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                setBlurBackground(bit,12);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }else if (requestCode == REQUEST_SELECT_PICTURE){
            // 获取图片的uri
            Uri uri = data.getData();
            Uri sourceUri = Uri.fromFile(new File(String.valueOf(uri)));
            jianchaiUtil.startUCrop(this, uri,UCrop.REQUEST_CROP,1,2);
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
        BitmapDrawable drawable = new BitmapDrawable(blurBmp);
        skinbeijing.setBackgroundDrawable(drawable);
        beijing.setBackgroundDrawable(drawable);
    }
}
