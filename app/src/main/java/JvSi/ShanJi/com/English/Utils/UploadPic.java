package JvSi.ShanJi.com.English.Utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.ui.Classify.ClassifyMessageEvent;
import JvSi.ShanJi.com.English.ui.MyFunction.AESDecode;

public class UploadPic {

    static Configuration config = new Configuration.Builder()
            .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
            .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
            .connectTimeout(10)           // 链接超时。默认10秒
            .useHttps(false)               // 是否使用https上传域名
            .responseTimeout(60)          // 服务器响应超时。默认60秒
            //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
            //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
            .zone(FixedZone.zone1)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
            .build();
    // 重用uploadManager。一般地，只需要创建一个uploadManager对象
    static UploadManager uploadManager = new UploadManager(config);


    public static void uploadPic(Uri uri) throws URISyntaxException {
        //指定zone的具体区域
//FixedZone.zone0   华东机房
//FixedZone.zone1   华北机房
//FixedZone.zone2   华南机房
//FixedZone.zoneNa0 北美机房

//自动识别上传区域
//AutoZone.autoZone


//Configuration config = new Configuration.Builder()
//.zone(Zone.autoZone)
//.zone(FixedZone.zone0)
//.build();
//UploadManager uploadManager = new UploadManager(config);
        //data = <File对象、或 文件路径、或 字节数组>
        String key = null;
        String token = MyApplication.getInstance().getUploadt();
        //File file = new Path(uri.getPath()).toFile();
        File file2 = new File(new URI(uri.toString()));
        uploadManager.put(uri.getPath(), key, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if(info.isOK()) {
                            try {
                                EventBus.getDefault().post(ClassifyMessageEvent.getInstance("res",res.getString("key")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("qiniu", "Upload Success");
                        } else {
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);



    }

    public static void uploadPic(File file2) throws URISyntaxException {
        //指定zone的具体区域
//FixedZone.zone0   华东机房
//FixedZone.zone1   华北机房
//FixedZone.zone2   华南机房
//FixedZone.zoneNa0 北美机房

//自动识别上传区域
//AutoZone.autoZone


//Configuration config = new Configuration.Builder()
//.zone(Zone.autoZone)
//.zone(FixedZone.zone0)
//.build();
//UploadManager uploadManager = new UploadManager(config);
        //data = <File对象、或 文件路径、或 字节数组>
        String key = null;
        String token = MyApplication.getInstance().getUploadt();
        //File file = new Path(uri.getPath()).toFile();
        long curClickTime3 = System.currentTimeMillis();
        Log.d("timebbb6",">>>>>>>>>>>>>>>>>>curClickTi666666      "+curClickTime3);
        uploadManager.put(file2, key, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if(info.isOK()) {
                            try {
                                //EventBus.getDefault().post(ClassifyMessageEvent.getInstance("res",res.getString("key")));
                                long curClickTime4 = System.currentTimeMillis();
                                Log.d("timebbb6",">>>>>>>>>>>>>>>>>>curClickTi445556666      "+curClickTime4);
                                AESDecode.main("http://px32gts87.bkt.clouddn.com/"+res.getString("key"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.i("qiniu", "Upload Success");
                        } else {
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);



    }

    public static File getFile(Bitmap bitmap) {
        long curClickTime3 = System.currentTimeMillis();
        Log.d("timebbb3",">>>>>>>>>>>>>>>>>>curClickTime1444444444    "+curClickTime3);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long curClickTime4 = System.currentTimeMillis();
        Log.d("timebbb3",">>>>>>>>>>>>>>>>>>curClickTime5555555555    "+curClickTime4);
        return file;
    }
}
