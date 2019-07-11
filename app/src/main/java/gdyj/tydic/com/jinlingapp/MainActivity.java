package gdyj.tydic.com.jinlingapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyhFragment;
import gdyj.tydic.com.jinlingapp.ui.EnglishFragment;
import gdyj.tydic.com.jinlingapp.ui.MainFragment;
import gdyj.tydic.com.jinlingapp.ui.UserSetFragment;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    private String TAG = "jinling";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.testFragmentTablayout);
        mViewPager = (ViewPager) findViewById(R.id.testFragmentViewPager);
        // 创建Fragment集合
        List<Fragment> fragments = new ArrayList<>();
        // 将Fragment添加到集合
        fragments.add(new ClassifyhFragment());
        fragments.add(new EnglishFragment());
        fragments.add(new UserSetFragment());
        fragments.add(new MainFragment());
        // 初始化适配器
        TestFragmentAdapter adapter = new TestFragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        mViewPager.setAdapter(adapter);
        // 设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);


      /*  Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        progressBar.setVisibility(View.VISIBLE); // 需要在主线程执行
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);*/




       /* Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                emitter.onNext(getResponse());
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String response) throws Exception {
                return response.length();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                tv_return.setText("字数：" + integer);
            }
        });*/


       /* Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                emitter.onNext(getResponse());
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String response) throws Exception {
                return response.length();
            }
        });

        //创建观察者
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String mResponse) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                String setText = (mResponse);
            }
        };
        //subscribeOn() 指定的是发送事件的线程, observeOn() 指定的是接收事件的线程.
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);*/

    }



    //使用okhttp访问网上提供的接口，由于是同步get请求，需要在子线程进行
    private String getResponse() {
        String url = "http://v.juhe.cn/weather/index?cityname=%E6%9D%AD%E5%B7%9E&dtype=&format=&key=7970495dbf33839562c9d496156e13cc";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;

        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "error";
        }
    }














        /*//创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //调用观察者的回调
                emitter.onNext("我是");
                emitter.onNext("RxJava");
                emitter.onNext("简单示例");
                emitter.onError(new Throwable("出错了"));
                emitter.onComplete();
            }
        });

        //创建观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"onCompleted");
            }

            //onSubscribe()方法是最先调用的
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"subscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,s);
            }
        };

        //注册，将观察者和被观察者关联，将会触发OnSubscribe.call方法
        observable.subscribe(observer);*/




    }


