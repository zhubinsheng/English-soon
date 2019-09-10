package JvSi.ShanJi.com.English.ui.Classify;

import JvSi.ShanJi.com.English.bean.BaseResult;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.LibraryResult;
import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*@Header("X-Access-Token") String token*/
/**
 * @author Administrator
 */
public interface ClassifyApi {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/classify")
    Observable<BaseResult<ClassifyBean>> GetClassify();


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lib/library/list")
    Observable<LibraryResult> GetLibrary();

}
