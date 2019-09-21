package JvSi.ShanJi.com.English.ui.Classify;

import android.annotation.SuppressLint;

import JvSi.ShanJi.com.English.Base.MyRetrofitManager;
import JvSi.ShanJi.com.English.bean.BaseResult;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.LibraryResult;
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
        classifyApi = MyRetrofitManager.create(ClassifyApi.class,null);
    }


    public void onDetach(){
        this.mView = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getClassify() {
        Observable<BaseResult<ClassifyBean>> observable = classifyApi.GetClassify();
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

    @SuppressLint("CheckResult")
    @Override
    public void getLibrary() {
        Observable<LibraryResult> observable = classifyApi.GetLibrary();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LibraryResult>() {
                    @Override
                    public void accept(LibraryResult classifyBeanBaseResult) throws Exception {
                        if(mView!=null&&classifyBeanBaseResult.getResult()!=null){
                           mView.onGetLibrarySuccess(classifyBeanBaseResult.getResult());
                        }else {
                            mView.onLoginFail("获取列表为空，请重试");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null){
                            mView.onLoginFail("网络出问题啦，请稍后再试");
                            mView.onGetLibrarySuccess(null);
                        }
                    }
                });
    }
}
