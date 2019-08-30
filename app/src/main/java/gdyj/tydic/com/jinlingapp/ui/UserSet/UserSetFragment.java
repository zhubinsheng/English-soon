package gdyj.tydic.com.jinlingapp.ui.UserSet;

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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhy.changeskin.SkinManager;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.Utils.BlurUtil;
import gdyj.tydic.com.jinlingapp.baiduUtils.TTSUtils;
import gdyj.tydic.com.jinlingapp.ui.Login_Regist.LoginActivity;
import gdyj.tydic.com.jinlingapp.ui.skin.SkinActivity;

/**
 * @author zbs
 */
public class UserSetFragment extends Fragment implements PhoneLoginContract.View{
    private static final int REQUEST_SELECT_PICTURE = 31;
    private int RESULT_OK = -1;
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
    @BindView(R.id.beijing)
    LinearLayout beijing;

    @BindView(R.id.profile_image)
    CircleImageView circleImageView;

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
        //StateListAnimator animator = AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.anim_state_list_m01);
        //Drawable mDrawable = LayoutToDrawable(R.drawable.foreground_selector);
        //StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.foreground_selector);
        //login.setStateListAnimator(animator);
        //login.setForeground(getResources().getDrawable(R.drawable.foreground_selector));
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


    @OnClick({R.id.button, R.id.button1, R.id.button2,R.id.profile_image,R.id.textView2,R.id.textView29})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
            case R.id.textView2:

                //speak();
                //  String phone = mEdtPhone.getText().toString();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
               /* SysLoginModel sysLoginModel = new SysLoginModel();
                sysLoginModel.setCaptcha("123");
                sysLoginModel.setUsername("admin19");
                sysLoginModel.setPassword("123456");
                mLoginPresenter.PhoneLogin(sysLoginModel);*/

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
            case R.id.profile_image:

                //Intent intent2 = new Intent();
                //intent2.setType("image/*");
                //intent2.setAction(Intent.ACTION_GET_CONTENT);
                //intent2.addCategory(Intent.CATEGORY_OPENABLE);
                //getActivity().startActivityForResult(Intent.createChooser(intent2, "选择图片"), REQUEST_SELECT_PICTURE);

                Intent intent3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // 以startActivityForResult的方式启动一个activity用来获取返回的结果
                startActivityForResult(intent3, REQUEST_SELECT_PICTURE);


                break;

            case R.id.textView29:

                //Bitmap bm = intent.getParcelableExtra("data");

                //立即修改背景
                /*BitmapDrawable drawable = new BitmapDrawable(bm);
                layout_left.setBackgroundDrawable(drawable);*/

                Intent intent13 = new Intent(getActivity(), SkinActivity.class);
                startActivity(intent13);
                break;
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
        options.setCropGridStrokeWidth(20);
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
        uCrop.start(activity, this,requestCode);
        return cameraScalePath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            circleImageView.setImageURI(resultUri);

            try {
                Bitmap bit = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(resultUri));
                setBlurBackground(bit);

                //BitmapDrawable drawable = new BitmapDrawable(bit);
                //beijing.setBackgroundDrawable(drawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


           /* Bitmap bitmap= BitmapFactory.decodeFile(new File(String.valueOf(resultUri)).getPath());

            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            beijing.setBackgroundDrawable(drawable);*/

            //UploadPic.uploadPic(resultUri);
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

            startUCrop(getActivity(), uri,UCrop.REQUEST_CROP,6,6);
        }
    }


    /**
     * 设置毛玻璃背景
     * 背景图片 Bitmap
     */
    private void setBlurBackground(Bitmap bmp)
    {
        //0-25，表示模糊值
        final Bitmap blurBmp = BlurUtil.fastblur(getActivity(), bmp, 10);
        BitmapDrawable drawable = new BitmapDrawable(blurBmp);
        beijing.setBackgroundDrawable(drawable);
    }




}
