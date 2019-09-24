package JvSi.ShanJi.com.English.ui.UserSet;

import JvSi.ShanJi.com.English.bean.BaseInfo;
import JvSi.ShanJi.com.English.bean.BaseResult;
import JvSi.ShanJi.com.English.bean.ClassResult;
import JvSi.ShanJi.com.English.bean.IntClassResult;
import JvSi.ShanJi.com.English.bean.ListClasssStudyResult;
import JvSi.ShanJi.com.English.bean.LoginResilt;
import JvSi.ShanJi.com.English.bean.LoginResult;
import JvSi.ShanJi.com.English.bean.SysUser;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhao
 */
public interface LoginApi {

    @FormUrlEncoded
    @POST("AppLogin")
    Observable<LoginResult> login(@Field("phone") String phone, @Field("password") String passward, @Field("platForm") int platForm, @Field("ident") String ident);

    @FormUrlEncoded
    @POST("AppLgByPhoneStepOne")
    Observable<BaseInfo> getValidCode(@Field("userPhone") String phone);


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/login")
    Observable<LoginResilt> loginByValidCode(@Body RequestBody  requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/register")
    Observable<BaseResult<SysUser>> PhoneRegister(@Body RequestBody  requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/registerInfo")
    Observable<BaseResult<SysUser>> registerInfo(@Body RequestBody  requestBody);


    @GET("demo/sYSCLASS/queryByClasss")
    Observable<ClassResult> queryByClasss(@Query("classs") String classs);

    @GET("demo/sYSCLASS/list")
    Observable<ListClasssStudyResult> queryListClasss(@Query("classs") String classs);


    @FormUrlEncoded
    @POST("demo/sYSCLASS/add")
    Observable<IntClassResult> CLASSadd(@Field("classs") String classs, @Field("userID") String userID);

}
