package gdyj.tydic.com.jinlingapp.Utils;

import android.net.Uri;
import android.util.Log;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

public class UploadPic {

    static Configuration config = new Configuration.Builder()
            .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
            .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
            .connectTimeout(10)           // 链接超时。默认10秒
            .useHttps(false)               // 是否使用https上传域名
            .responseTimeout(60)          // 服务器响应超时。默认60秒
            //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
            //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
            .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
            .build();
    // 重用uploadManager。一般地，只需要创建一个uploadManager对象
    static UploadManager uploadManager = new UploadManager(config);


    public static void uploadPic(Uri uri){
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
        String key = "123";
        String token = "123";
        uploadManager.put(String.valueOf(uri), key, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if(info.isOK()) {
                            Log.i("qiniu", "Upload Success");
                        } else {
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);



    }
}
