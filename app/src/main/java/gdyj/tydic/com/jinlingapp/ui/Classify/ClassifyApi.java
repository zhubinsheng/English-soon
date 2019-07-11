package gdyj.tydic.com.jinlingapp.ui.Classify;

import gdyj.tydic.com.jinlingapp.bean.ClassifyL;
import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by zhao
 */
public interface ClassifyApi {



    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/classify")
    Observable<ClassifyL> GetClassify(@Header("X-Access-Token") String token);

}
