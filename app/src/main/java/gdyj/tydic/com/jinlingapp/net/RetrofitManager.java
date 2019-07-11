package gdyj.tydic.com.jinlingapp.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;
import io.reactivex.android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final String BASE_URL = "http://192.168.43.43:8080/jeecg-boot/";
    private static final long CONNECT_TIME_OUT = 60L;
    private static final long READ_TIME_OUT = 10L;
    private static final long WRITE_TIME_OUT = 10L;
    private static volatile OkHttpClient sOkhttpClient;
    private static volatile Retrofit sRetrofit;

    public static <T> T create(Class<T> clazz){
        if(sRetrofit == null){
            synchronized (RetrofitManager.class){
                if(sRetrofit == null){
                    sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return sRetrofit.create(clazz);
    }

    private static OkHttpClient getOkHttpClient() {
        if (sOkhttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (sOkhttpClient == null) {
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

                    sOkhttpClient = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .addInterceptor(httpLoggingInterceptor)
                            .addNetworkInterceptor(new StethoInterceptor())
                            .build();
                }

            }
        }
        return sOkhttpClient;
    }
}
