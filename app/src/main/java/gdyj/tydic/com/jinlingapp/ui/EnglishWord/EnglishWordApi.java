package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import gdyj.tydic.com.jinlingapp.bean.EnglishCodeVo;
import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*@Header("X-Access-Token") String token*/

/**
 * @author Administrator
 */
public interface EnglishWordApi {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/word")
    Observable<EnglishCodeVo> GetEnglishWord();

}
