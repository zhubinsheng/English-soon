package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import gdyj.tydic.com.jinlingapp.MyRetrofitManager;
import gdyj.tydic.com.jinlingapp.bean.BaseResult;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyApi;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 * @author binshengzhu
 */
public class EnglishWordPresenter implements ClassifyContract.Presenter {

    private ClassifyContract.View mView;
    private final EnglishWordApi englishWordApi;

    public EnglishWordPresenter(ClassifyContract.View view){
        this.mView = view;
        englishWordApi = MyRetrofitManager.create(EnglishWordApi.class);
    }


    public void onDetach(){
        this.mView = null;
    }

    @Override
    public void getClassify() {
        Observable<BaseResult<ClassifyBean>> observable = englishWordApi.GetEnglishWord();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResult<ClassifyBean>>() {
                    @Override
                    public void accept(BaseResult<ClassifyBean> classifyBeanBaseResult) throws Exception {
                        if(mView!=null&&classifyBeanBaseResult.getResult()!=null){
                            mView.onLoginSuccess(classifyBeanBaseResult.getResult());
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
