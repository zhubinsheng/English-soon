package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import JvSi.ShanJi.com.English.Base.MyRetrofitManager;
import JvSi.ShanJi.com.English.bean.BaseResult;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.EnglishCodeVo;
import JvSi.ShanJi.com.English.bean.LearningSit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by
 * @author binshengzhu
 */
public class EnglishWordPresenter implements EnglishContract.Presenter {

    private EnglishContract.View mView;
    private final EnglishWordApi englishWordApi;
    public EnglishWordPresenter(EnglishContract.View view){
        this.mView = view;
        englishWordApi = MyRetrofitManager.create(EnglishWordApi.class,null);
    }


    public void onDetach(){
        this.mView = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getClassify(String classify, int pageSize, int pageNo, Context context) {
        Observable<EnglishCodeVo> observable = englishWordApi.GetEnglishWord(classify, pageSize,pageNo);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EnglishCodeVo>() {
                    @Override
                    public void accept(EnglishCodeVo classifyBeanBaseResult) throws Exception {
                        if(mView!=null&&classifyBeanBaseResult.getResult()!=null){
                            /*if (pageNo!=1){
                                mView.onGetMoreSuccess(classifyBeanBaseResult.getResult());
                            }else {*/
                            Log.d("time0",">>>>>>>>>>>>>>>>>>000000      ");
                            List<ClassifyBean> finalResult = classifyBeanBaseResult.getResult();
                                mView.onLoginSuccess(finalResult);
                            Log.d("time0",">>>>>>>>>>>>>>>>>>111      ");


                        }else {
                            mView.onLoginFail("获取单词列表为空，请重试");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                                mView.onLoginFail("网络出问题啦，请稍后再试");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void addLearningSit(LearningSit learningSit) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(learningSit);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),requestBody);
        Observable<BaseResult> observable = englishWordApi.addLearningSit(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResult -> {

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


    @SuppressLint("CheckResult")
    @Override
    public void sousuoWord(String classify,int pageSize,int pageNo) {
        Observable<EnglishCodeVo> observable = englishWordApi.GetEnglishWord(classify, pageSize,pageNo);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EnglishCodeVo>() {
                    @Override
                    public void accept(EnglishCodeVo classifyBeanBaseResult) throws Exception {
                        if(mView!=null&&classifyBeanBaseResult.getResult()!=null){
                            if (pageNo!=1){
                                mView.onGetMoreSuccess(classifyBeanBaseResult.getResult());
                            }else {
                                mView.onLoginSuccess(classifyBeanBaseResult.getResult());
                            }

                        }else {
                            mView.onLoginFail("获取单词列表为空，请重试");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                            mView.onLoginFail("网络出问题啦，请稍后再试");
                        }
                    }
                });
    }
}
