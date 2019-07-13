package gdyj.tydic.com.jinlingapp.ui.Classify;

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
import android.widget.Toast;

import java.util.List;

import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.MyApplication;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.EnglishAdapter;
import gdyj.tydic.com.jinlingapp.bean.BaseEntity;
import gdyj.tydic.com.jinlingapp.bean.ClassifyL;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;
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

public class ClassifyhFragment extends Fragment implements ClassifyContract.View{
    private static final String BASE_URL = "http://192.168.43.43:8089/english/";
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private EnglishAdapter englishAdapter;
    private PersonalProtocol personalProtocol;
    private List< ClassifyL.ResultBean> englishInfoList;

    private ClassifyPresenter classifyPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classifyPresenter = new ClassifyPresenter(this);
    }
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
        initData(); // retrofit请求范例
        initAdapter();
}

    private void initData() {
        String token ;
        if (MyApplication.getInstance().getHasjwt()){
            token = MyApplication.getInstance().getJwt();
        }else {
            Toasty.error(getActivity(), "尚未登录，请先登录哦", Toast.LENGTH_SHORT, true).show();
            return;
        }
        classifyPresenter.getClassify(token);
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
        englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
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

    @Override
    public void onValidCodeSend() {

    }

    @Override
    public void onLoginSuccess(ClassifyL loginResult) {

        englishAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.error(getActivity(), errorTip, Toast.LENGTH_LONG, true).show();
    }
}
