package JvSi.ShanJi.com.English.Utils;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;

import JvSi.ShanJi.com.English.R;

public class jianchaiUtil {

    /**
     * 启动裁剪
     * @param activity 上下文
     * @param
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @param aspectRatioX 裁剪图片宽高比
     * @param aspectRatioY 裁剪图片宽高比
     * @return
     */
    public static String startUCrop(Activity activity, Uri uri,
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
        uCrop.start(activity,requestCode);
        return cameraScalePath;
    }
}
