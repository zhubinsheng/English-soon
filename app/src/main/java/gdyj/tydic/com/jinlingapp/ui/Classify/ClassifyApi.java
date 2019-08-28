package gdyj.tydic.com.jinlingapp.ui.Classify;

import gdyj.tydic.com.jinlingapp.bean.BaseResult;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.bean.LibraryResult;
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
