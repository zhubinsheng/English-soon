package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

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
import com.warkiz.widget.IndicatorSeekBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.Base.MyApplication;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.bean.EnglishCodeVo;
import gdyj.tydic.com.jinlingapp.bean.WordList;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyMessageEvent;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;


/**
 * @author Administrator
 */
public class EnglishFragment extends Fragment implements EnglishContract.View {
    private Unbinder unbinder;
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private EnglishAdapter englishAdapter;
    private List<ClassifyBean> englishInfoList;
    private EnglishWordPresenter englishWordPresenter;
    private String classify;
    private static int pageSize = 15;
    private static long delayMillis = 1000;
    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;

    @BindView(R.id.seekbar)
    IndicatorSeekBar seekBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_english, container, false);
        englishWordPresenter = new EnglishWordPresenter(this);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this,layout);
        seekBar.setEnabled(false);
        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);
        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        //initAdapter();
}
    private void initData() {
        //englishWordPresenter.getClassify(classify);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
        englishWordPresenter.onDetach();
    }
    private void initView() {
        mRecyclerView=(RecyclerView)layout.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //back = (ImageView) layout.findViewById(R.id.img_back);
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
        //englishAdapter.openLoadAnimation();
        //mRecyclerView.setAdapter(englishAdapter);
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
    public void onLoginSuccess(EnglishCodeVo.ResultBean resultBean) {
        englishInfoList = resultBean.getRecords();
        Toasty.success(getActivity(),"获取单词成功").show();
        //englishAdapter.notifyDataSetChanged();
        englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);

        //开启动画（默认为渐显效果）
        //使用缩放动画
        englishAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT );
        //设置重复执行动画
        englishAdapter.isFirstOnly(false);
        //条目长按事件
        englishAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "长按了第" + englishInfoList.get(position).getWord(), Toast.LENGTH_SHORT).show();


               /* notesQuery = notesBox.query()*//*.order(ClassifyBean)*//*.build();
                //notesBox.put(classifyBean);

                QueryBuilder<WordList> builder = notesBox.query();
        *//*builder.equal(User_.firstName,"Joe)
                .greater(User_.yearOfBirth,1970)
                .startsWith(User_.lastName,"0");*//*
                List<WordList> youngJoes = builder.build().find();
                List<WordList> resultBeans = notesQuery.find();*/


                List<ClassifyBean>  classifyBeanList = new ArrayList<>();
                classifyBeanList.add(englishInfoList.get(position));
                long Id=notesBox.put(new WordList(0,classifyBeanList));


                return false;
            }
        });
        // 开启滑动删除
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
        englishAdapter.enableSwipeItem();
        englishAdapter.setOnItemSwipeListener(onItemSwipeListener);

        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        englishAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (resultBean.getCurrent() >= resultBean.getPages()) {
                            //数据全部加载完毕
                            englishAdapter.loadMoreEnd();
                        } else {
                            englishWordPresenter.getClassify(classify,pageSize,resultBean.getCurrent()+1);

                           /* if (isErr) {
                                //成功获取更多数据
                                englishAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                                mCurrentCounter = mQuickAdapter.getData().size();
                                mQuickAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                                mQuickAdapter.loadMoreFail();

                            }*/
                        }
                    }

                }, delayMillis);
            }
        }, mRecyclerView);

        mRecyclerView.setAdapter(englishAdapter);
    }

    @Override
    public void onGetMoreSuccess(EnglishCodeVo.ResultBean resultBean) {
        englishAdapter.addData(resultBean.getRecords());
        englishAdapter.loadMoreComplete();
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.warning(getActivity(),errorTip).show();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(ClassifyMessageEvent message) {
        if (message.getRecode().equals("classify")){
            classify = message.getClassify();
            englishWordPresenter.getClassify(classify,pageSize,1);
        }
    }
}
