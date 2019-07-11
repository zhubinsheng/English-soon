package gdyj.tydic.com.jinlingapp.present;

import gdyj.tydic.com.jinlingapp.bean.BaseInfo;
import gdyj.tydic.com.jinlingapp.bean.LoginResilt;
import gdyj.tydic.com.jinlingapp.bean.LoginResult;
import gdyj.tydic.com.jinlingapp.bean.Result;
import gdyj.tydic.com.jinlingapp.bean.SysUser;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
    Observable<Result<SysUser>> PhoneRegister(@Body RequestBody  requestBody);

}
