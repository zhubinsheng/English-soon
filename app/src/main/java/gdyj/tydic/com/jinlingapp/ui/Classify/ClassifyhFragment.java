package gdyj.tydic.com.jinlingapp.ui.Classify;

import android.graphics.Canvas;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.mingle.widget.LoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.MyApplication;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.EnglishAdapter;
import gdyj.tydic.com.jinlingapp.bean.ClassifyL;

/**
 * @author binshengzhu
 */
public class ClassifyhFragment extends Fragment implements ClassifyContract.View{
    private Unbinder unbinder;
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private EnglishAdapter englishAdapter;
    private List< ClassifyL.ResultBean> englishInfoList =new ArrayList<>();
    private Box<Note> notesBox;

    @BindView(R.id.loadView)
    LoadingView loadingView;

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
        unbinder = ButterKnife.bind(this,layout);
        return layout;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        classifyPresenter.onDetach();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData(); // retrofit请求范例
        initAdapter();
}

    private void initData() {
        String token = null;
        if (MyApplication.getInstance().getHasjwt()){
            token = MyApplication.getInstance().getJwt();
            classifyPresenter.getClassify(token);
        }else {
            Toasty.warning(getActivity(), "尚未登录，请先登录哦", Toast.LENGTH_SHORT, true).show();
            //return;
        }

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
       /* List<ClassifyL.ResultBean> englishInfoList = new ArrayList<>();
        ClassifyL.ResultBean resultBean = new ClassifyL.ResultBean();
        resultBean.setClassify("ceshi first");
        resultBean.setWord("1");
        resultBean.setMeaning("yes");
        ClassifyL.ResultBean resultBean1 = new ClassifyL.ResultBean();
        resultBean1.setClassify("ceshi two");
        resultBean1.setWord("1");
        resultBean1.setMeaning("yes");
        englishInfoList.add(resultBean);
        englishInfoList.add(resultBean1);*/
        englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
        englishAdapter.openLoadAnimation();

        //开启动画（默认为渐显效果）
        englishAdapter.openLoadAnimation();
        //条目长按事件
        englishAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "长按了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(getActivity(), "onItemSwipeStart", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(getActivity(), "clearView", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(getActivity(), "onItemSwiped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                Toast.makeText(getActivity(), "onItemSwipeMoving", Toast.LENGTH_SHORT).show();
            }
        };





        // 开启滑动删除
        englishAdapter.enableSwipeItem();
        englishAdapter.setOnItemSwipeListener(onItemSwipeListener);



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
        englishInfoList = loginResult.getResult();
        loadingView.setVisibility(View.GONE);
        englishAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.warning(getActivity(), errorTip, Toast.LENGTH_LONG, true).show();
    }


}
