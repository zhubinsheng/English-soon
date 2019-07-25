package gdyj.tydic.com.jinlingapp.ui.Classify;

import gdyj.tydic.com.jinlingapp.MyRetrofitManager;
import gdyj.tydic.com.jinlingapp.bean.BaseResult;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 * @author binshengzhu
 */
public class ClassifyPresenter implements ClassifyContract.Presenter {

    private ClassifyContract.View mView;
    private final ClassifyApi classifyApi;

    public ClassifyPresenter(ClassifyContract.View view){
        this.mView = view;
        classifyApi = MyRetrofitManager.create(ClassifyApi.class);
    }


    public void onDetach(){
        this.mView = null;
    }

    @Override
    public void getClassify(String token) {
        Observable<BaseResult<ClassifyBean>> observable = classifyApi.GetClassify(token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResult<ClassifyBean>>() {
                    @Override
                    public void accept(BaseResult<ClassifyBean> classifyBeanBaseResult) throws Exception {
                        if(mView!=null&&classifyBeanBaseResult.gettList()!=null){
                            mView.onLoginSuccess(classifyBeanBaseResult.gettList());
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
