package JvSi.ShanJi.com.English.ui.EnglishWord;

import JvSi.ShanJi.com.English.bean.BaseResult;
import JvSi.ShanJi.com.English.bean.EnglishCodeVo;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
/*@Header("X-Access-Token") String token*/

/**
 * @author Administrator
 */
public interface EnglishWordApi {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/word")
    Observable<EnglishCodeVo> GetEnglishWord(@Query("classify") String classify,@Query("pageSize") int pageSize,@Query("pageNo") int pageNo);


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("demo/learningSit/add")
    Observable<BaseResult> addLearningSit(@Body RequestBody requestBody);
}
