package JvSi.ShanJi.com.English.ui.UserSet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhy.changeskin.SkinManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.Utils.BlurUtil;
import JvSi.ShanJi.com.English.Utils.UploadPic;
import JvSi.ShanJi.com.English.baiduUtils.TTSUtils;
import JvSi.ShanJi.com.English.bean.ClassResult;
import JvSi.ShanJi.com.English.ui.Classify.ClassifyhActivty;
import JvSi.ShanJi.com.English.ui.Login_Regist.LoginActivity;
import JvSi.ShanJi.com.English.ui.skin.SkinActivity;
import JvSi.ShanJi.com.English.ui.skin.XitongShezhiActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * @author zbs
 */
public class UserSetActivity2 extends AppCompatActivity implements PhoneLoginContract.View{
    private static final int REQUEST_SELECT_PICTURE = 31;
    private int RESULT_OK = -1;
    private View layout;
    private Unbinder unbinder;
    private PhoneLoginPresenter mLoginPresenter;

    @BindView(R.id.ciku)
    LinearLayout ciku;

    @BindView(R.id.gouwu)
    LinearLayout gouwu;

    @BindView(R.id.jiaocheng)
    LinearLayout jiaocheng;

    @BindView(R.id.shezhi)
    LinearLayout shezhi;

    @BindView(R.id.zhuangtai)
    LinearLayout zhuangtai;

    @BindView(R.id.zhuangban)
    LinearLayout zhuangban;



    @BindView(R.id.profile_image)
    CircleImageView circleImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_set);
        mLoginPresenter = new PhoneLoginPresenter(this);
        SkinManager.getInstance().register(this);
        unbinder = ButterKnife.bind(this);
        initData();
        initAdapter();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    /*@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.user_set, container, false);
        unbinder = ButterKnife.bind(this,layout);
        //StateListAnimator animator = AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.anim_state_list_m01);
        //Drawable mDrawable = LayoutToDrawable(R.drawable.foreground_selector);
        //StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.foreground_selector);
        //login.setStateListAnimator(animator);
        //login.setForeground(getResources().getDrawable(R.drawable.foreground_selector));
        //login.setBackgroundResource(R.drawable.foreground_selector);
        return layout;
    }*/

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initView();
        initData();
        initAdapter();
    }*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mLoginPresenter.onDetach();
        SkinManager.getInstance().unregister(this);
    }

    private void initAdapter() {
    }

    private void initData() {
        if (MyApplication.getInstance().getHasjwt()){
            if (MyApplication.getInstance().getUserInfoBean().getRealname()==null||MyApplication.getInstance().getUserInfoBean().getRealname().isEmpty()){
                //textView2.setText("吴彦祖");
            }else {
                //textView2.setText(MyApplication.getInstance().getUserInfoBean().getRealname());
            }
        }else {

        }

        //circleImageView.setImageResource(R.drawable.touxiang);
       /* new Thread(){
            @Override
            public void run() {
                //需要在子线程中处理的逻辑
                TTSUtils.getInstance().init();
                //initialTts(); // 初始化TTS引擎
            }
        }.start();*/
    }

   /* private void initView() {
        setTitle("个 人 中 心");
    }
    private void setTitle(String msg) {
        if (title != null) {
            title.setText(msg);
        }
    }*/


    @OnClick({R.id.profile_image,R.id.ciku,R.id.gouwu,R.id.jiaocheng,R.id.shezhi,R.id.zhuangtai,R.id.zhuangban})
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.textView2:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;*/
            case R.id.profile_image:
                if (MyApplication.getInstance().getHasjwt()){
                    Intent intent3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // 以startActivityForResult的方式启动一个activity用来获取返回的结果
                    startActivityForResult(intent3, REQUEST_SELECT_PICTURE);
                }else {
                    Toasty.normal(this,"请先登录").show();
                    Intent inten5 = new Intent(this, LoginActivity.class);
                    startActivity(inten5);
                }
                //Intent intent2 = new Intent();
                //intent2.setType("image/*");
                //intent2.setAction(Intent.ACTION_GET_CONTENT);
                //intent2.addCategory(Intent.CATEGORY_OPENABLE);
                //getActivity().startActivityForResult(Intent.createChooser(intent2, "选择图片"), REQUEST_SELECT_PICTURE)

                break;

            case R.id.zhuangban:

                //Bitmap bm = intent.getParcelableExtra("data");

                //立即修改背景
                /*BitmapDrawable drawable = new BitmapDrawable(bm);
                layout_left.setBackgroundDrawable(drawable);*/

                Intent intent13 = new Intent(this, SkinActivity.class);
                startActivity(intent13);
                break;

            case R.id.shezhi:
                //系统设置
                Intent intent14 = new Intent(this, XitongShezhiActivity.class);
                startActivity(intent14);

                break;

            case R.id.ciku:
                Intent intent15 = new Intent(this, ClassifyhActivty.class);
                startActivity(intent15);
                break;

            /*case R.id.textView5:
                Intent intent16 = new Intent(this, tianxieziliaoAcitivity.class);
                startActivity(intent16);
                break;*/



                default:break;
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

    @Override
    public void onRegisterSuccess() {
        Toasty.success(this,"注册成功").show();
    }

    @Override
    public void onRegisterFail(String errorTip) {

    }

    @Override
    public void onGetValideCode(ClassResult baseInfo) {

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



    /**
     * 启动裁剪
     * @param activity 上下文
     * @param
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @param aspectRatioX 裁剪图片宽高比
     * @param aspectRatioY 裁剪图片宽高比
     * @return
     */
    public String startUCrop(Activity activity, Uri uri,
                             int requestCode, float aspectRatioX, float aspectRatioY) {
        //Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        String cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(uri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        //options.setCropGridStrokeWidth(20);
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(false);
        //设置是否为圆形裁剪框
        options.setCircleDimmedLayer(true);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(this,requestCode);
        return cameraScalePath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            circleImageView.setImageURI(resultUri);
            new File(String.valueOf(resultUri));
            try {
                Bitmap bit = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(resultUri));
                //setBlurBackground(bit);

                //BitmapDrawable drawable = new BitmapDrawable(bit);
                //beijing.setBackgroundDrawable(drawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


           /* Bitmap bitmap= BitmapFactory.decodeFile(new File(String.valueOf(resultUri)).getPath());

            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            beijing.setBackgroundDrawable(drawable);*/

            try {
                UploadPic.uploadPic(resultUri);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }else if (requestCode == REQUEST_SELECT_PICTURE){
            // 获取图片的uri
            Uri uri = data.getData();
            final Uri resultUri = UCrop.getOutput(data);
            //new File(resultUri);
            //Bitmap bitmap= BitmapFactory.decodeFile(new File(String.valueOf(uri)).getPath());

            //BitmapDrawable drawable = new BitmapDrawable(bitmap);
            //beijing.setBackgroundDrawable(drawable);

            Uri sourceUri = Uri.fromFile(new File(String.valueOf(uri)));

            startUCrop(this, uri,UCrop.REQUEST_CROP,6,6);
        }
    }


    /**
     * 设置毛玻璃背景
     * 背景图片 Bitmap
     */
    private void setBlurBackground(Bitmap bmp)
    {
        //0-25，表示模糊值
        final Bitmap blurBmp = BlurUtil.fastblur(this, bmp, 10);
        BitmapDrawable drawable = new BitmapDrawable(blurBmp);
        //beijing.setBackgroundDrawable(drawable);
    }




}
