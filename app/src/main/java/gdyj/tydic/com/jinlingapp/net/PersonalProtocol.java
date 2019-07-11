package gdyj.tydic.com.jinlingapp.net;

import gdyj.tydic.com.jinlingapp.bean.BaseEntity;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;

public interface PersonalProtocol {

    @POST("word/getWord")
    Call<BaseEntity<EnglishInfo>> getPersonalListInfo();



    @POST("word/getWord")
    Observable<BaseEntity<EnglishInfo>> getEnglishInfo();

}