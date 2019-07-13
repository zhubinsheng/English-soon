package gdyj.tydic.com.jinlingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;
import java.util.logging.Logger;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.EnglishAdapter;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;
import gdyj.tydic.com.jinlingapp.bean.BaseEntity;
import gdyj.tydic.com.jinlingapp.net.PersonalProtocol;
import gdyj.tydic.com.jinlingapp.net.RetrofitManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnglishFragment extends Fragment {
    private static final String BASE_URL = "http://192.168.43.43:8089/english/";
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private EnglishAdapter englishAdapter;
    private PersonalProtocol personalProtocol;
    private List<EnglishInfo> englishInfoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_classify, container, false);
        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //initData(); // retrofit请求范例
//        initData2();  //搭配使用范例
        //initAdapter();
}

    private void initData2() {
        personalProtocol = RetrofitManager.create(PersonalProtocol.class);
        personalProtocol.getEnglishInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseEntity<EnglishInfo>>() {
                    @Override
                    public void accept(final BaseEntity<EnglishInfo> englishInfoBaseEntity) throws Exception {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                englishInfoList = englishInfoBaseEntity.gettList();
                                //englishAdapter.addData(englishInfoList);
                                englishAdapter.loadMoreComplete();

                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String retMsg = "nonono";
                    }
                });
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //gson转换器
                .build();

        retrofit.create(PersonalProtocol.class)
                .getPersonalListInfo()
                .enqueue(new Callback<BaseEntity<EnglishInfo>>() {
                    @Override
                    public void onResponse(Call<BaseEntity<EnglishInfo>> call, Response<BaseEntity<EnglishInfo>> response) {
                        BaseEntity<EnglishInfo> body = response.body();
                        List<EnglishInfo> list = body.gettList();
                        String retMsg = body.getRetMsg();
                       // Log.e("xyh", "onResponse: " + list.size());
                    }

                    @Override
                    public void onFailure(Call<BaseEntity<EnglishInfo>> call, Throwable t) {

                    }
                });

    }

    private void initView() {
        mRecyclerView=(RecyclerView)layout.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        back = (ImageView) layout.findViewById(R.id.img_back);
        title = (TextView) layout.findViewById(R.id.title);
        setBackBtn();
        setTitle("英 文 四 级");
    }

    private void setTitle(String msg) {
        if (title != null) {
            title.setText(msg);
        }
    }

    private void initAdapter() {
        //englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
        englishAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(englishAdapter);
    }

    protected void setBackBtn() {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // finish();
                }
            });
        } else {
           // Logger.t(TAG).e("back is null ,please check out");
        }

    }

}
