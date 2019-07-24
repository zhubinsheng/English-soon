package gdyj.tydic.com.jinlingapp.baiduUtils;
 
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.MyApplication;

/**
 * Created by As on 2017/8/7.
 */
 
public class TTSUtils implements SpeechSynthesizerListener {
 
    private static final String TAG = "TTSUtils";
    private static volatile TTSUtils instance = null;
    private SpeechSynthesizer mSpeechSynthesizer;
 
    private static final String SAMPLE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/baiduTTS/";
    private static final String SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female.dat";
    private static final String TEXT_MODEL_NAME = "bd_etts_text.dat";
 
    private static final String APIKEY = "zToWtAkk1gSPyY2vk9xcVjdS";
    private static final String SECRETKEY = "OiAciNFpBBXY9nHhNwYSx1dMQc92lkGm";
    private static final String APPID = "16806137";
 
    private TTSUtils() {
    }
 
    public static TTSUtils getInstance() {
        if (instance == null) {
            synchronized (TTSUtils.class) {
                if (instance == null) {
                    instance = new TTSUtils();
                }
            }
        }
        return instance;
    }
 
    public void init() {
        Context context = MyApplication.getAppContext();
        File file = new File(SAMPLE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        File textModelFile = new File(SAMPLE_DIR + TEXT_MODEL_NAME);
        if (!textModelFile.exists()) {
            copyAssetsFile2SDCard(context, TEXT_MODEL_NAME, SAMPLE_DIR + TEXT_MODEL_NAME);
        }
        File speechModelFile = new File(SAMPLE_DIR + SPEECH_FEMALE_MODEL_NAME);
        if (!speechModelFile.exists()) {
            copyAssetsFile2SDCard(context, SPEECH_FEMALE_MODEL_NAME, SAMPLE_DIR + SPEECH_FEMALE_MODEL_NAME);
        }
        // 获取语音合成对象实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        // 设置context
        mSpeechSynthesizer.setContext(context);
        // 设置语音合成状态监听器
        mSpeechSynthesizer.setSpeechSynthesizerListener(this);
        mSpeechSynthesizer.setApiKey(APIKEY, SECRETKEY);
        // 设置离线语音合成授权，需要填入从百度语音官网申请的app_id
        mSpeechSynthesizer.setAppId(APPID);
        // 设置语音合成文本模型文件
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, SAMPLE_DIR + TEXT_MODEL_NAME);
        // 设置语音合成声音模型文件
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, SAMPLE_DIR + SPEECH_FEMALE_MODEL_NAME);
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置Mix模式的合成策略
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 初始化tts
        mSpeechSynthesizer.initTts(TtsMode.MIX);
    }
 
    //需要合成的msg长度不能超过1024个GBK字节。
    public void speak(String msg) {
        int result = mSpeechSynthesizer.speak(msg);
        if (result < 0) {
            Log.e(TAG, "error,please look up error code = " + result + " in doc or URL:http://yuyin.baidu.com/docs/tts/122 ");
            Toasty.warning(MyApplication.getAppContext(),"子线程合成语音失败，请联系管理员").show();
        }
    }
 
    public void pause() {
        mSpeechSynthesizer.pause();
    }
 
    public void resume() {
        mSpeechSynthesizer.resume();
    }
 
    public void stop() {
        mSpeechSynthesizer.stop();
    }
 
    public void release() {
        if (null != mSpeechSynthesizer) {
            mSpeechSynthesizer.release();
        }
    }
 
    @Override
    public void onSynthesizeStart(String s) {
        // 监听到合成开始，在此添加相关操作
    }
 
    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {
        // 监听到有合成数据到达，在此添加相关操作
    }
 
    @Override
    public void onSynthesizeFinish(String s) {
        // 监听到合成结束，在此添加相关操作
    }
 
    @Override
    public void onSpeechStart(String s) {
        // 监听到合成并播放开始，在此添加相关操作
    }
 
    @Override
    public void onSpeechProgressChanged(String s, int i) {
        // 监听到播放进度有变化，在此添加相关操作
    }
 
    @Override
    public void onSpeechFinish(String s) {
        // 监听到播放结束，在此添加相关操作
    }
 
    @Override
    public void onError(String s, SpeechError speechError) {
        // 监听到出错，在此添加相关操作
        //System.out.println("========================"+s+speechError.code+speechError.description);
        Toasty.error(MyApplication.getAppContext(), "请将此界面发送给管理员"+speechError.code, Toast.LENGTH_LONG, true).show();
    }
 
    public static void copyAssetsFile2SDCard(Context context, String fileName, String path) {
        try {
            InputStream is = context.getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(new File(path));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取buffer字节
                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            fos.flush();// 刷新缓冲区
            is.close();
            fos.close();
        } catch (IOException e) {
            Log.e(TAG, "copyAssetsFile2SDCard: " + e.toString());
        }
    }
}
