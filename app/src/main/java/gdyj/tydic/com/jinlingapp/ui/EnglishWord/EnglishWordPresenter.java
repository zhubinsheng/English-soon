package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import android.annotation.SuppressLint;

import gdyj.tydic.com.jinlingapp.MyRetrofitManager;
import gdyj.tydic.com.jinlingapp.bean.EnglishCodeVo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 * @author binshengzhu
 */
public class EnglishWordPresenter implements EnglishContract.Presenter {

    private EnglishContract.View mView;
    private final EnglishWordApi englishWordApi;

    public EnglishWordPresenter(EnglishContract.View view){
        this.mView = view;
        englishWordApi = MyRetrofitManager.create(EnglishWordApi.class);
    }


    public void onDetach(){
        this.mView = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getClassify(String classify,int pageSize,int pageNo) {
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
