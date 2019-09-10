package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.stream.Collectors;

import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.baiduUtils.TTSUtils;
import JvSi.ShanJi.com.English.baiduUtils.java.bin.com.baidu.translate.demo.Main;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.EnglishCodeVo;
import JvSi.ShanJi.com.English.bean.TransltResult;
import JvSi.ShanJi.com.English.bean.WordList;
import JvSi.ShanJi.com.English.bean.WordList_;
import JvSi.ShanJi.com.English.ui.Classify.ClassifyMessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;


/**
 * @author Administrator
 */
public class EnglishActivity2 extends AppCompatActivity implements EnglishContract.View {
    private static final String TAG = "inword";
    private Unbinder unbinder;
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private EnglishAdapter englishAdapter;
    private List<ClassifyBean> englishInfoList;
    private EnglishWordPresenter englishWordPresenter;
    private String classify;
    private static int pageSize = 60;
    private static long delayMillis = 1000;
    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @BindView(R.id.hengxiang)
    LinearLayout hengxiang;

    @BindView(R.id.shurudanci)
    MaterialEditText shurudanci;

    @BindView(R.id.sousuo)
    TextView sousuo;

    @SuppressLint("HandlerLeak")
    Handler myHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //xxx操作
                    List<TransltResult.TransResultBean> transResultBeans = ( List<TransltResult.TransResultBean>) msg.obj;
                    //Toasty.warning(getActivity(),transltResult).show();

                    String value = transResultBeans.stream().map(productInfoVO -> String.valueOf(productInfoVO.getDst())).collect(Collectors.joining(","));
                    shurudanci.setFloatingLabelText(value);
                case 1: //yyy操作
                    break;
                default:
                    break;
            }
        }
    };
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_english);
        Intent intent15 =getIntent();
        String data = intent15.getStringExtra("library");
        englishWordPresenter = new EnglishWordPresenter(this);
        englishWordPresenter.getClassify(data,pageSize,1);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this);
        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);

        shurudanci.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        shurudanci.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    //写你要做的事情
                    GOsousuo();
                    return true;
                }
                return false;
            }
        });

        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                startUpFetch();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        initView();
    }

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_english, container, false);
        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        //initAdapter();
}*/
    private void initData() {
        //englishWordPresenter.getClassify(classify);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
        englishWordPresenter.onDetach();
    }
    private void initView() {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView=(RecyclerView)findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //back = (ImageView) layout.findViewById(R.id.img_back);
        //title = (TextView) layout.findViewById(R.id.title);
        //setBackBtn();
        //setTitle("单 词 列 表");



       /* mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(TAG, "--------------------------------------");
                if(mRecyclerView.canScrollVertically(1)){
                    Log.i(TAG, "direction 1: true");
                }else {
                    Log.i(TAG, "direction 1: false");//滑动到底部
                }
                if(mRecyclerView.canScrollVertically(-1)){
                    Log.i(TAG, "direction -1: true");
                }else {
                    Log.i(TAG, "direction -1: false");//滑动到顶部
                }
            }
        });*/

    }
   /* private void setTitle(String msg) {
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

    }*/

    @OnClick({R.id.sousuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sousuo:
               GOsousuo();

                break;
                default:
                    break;

        }
    }

    private void GOsousuo() {
        if (
                shurudanci.getText() ==null ||shurudanci.getText().toString().isEmpty()
        ){
            Toasty.warning(this,"请输入正确文字").show();
        }else {
            new Thread(){
                @Override
                public void run() {
                    //需要在子线程中处理的逻辑
                    String str  =   Main.main(shurudanci.getText().toString());
                    Gson gson = new Gson();
                    TransltResult transltResult = gson.fromJson(str, TransltResult.class);
                    //Toasty.warning(getActivity(),transltResult.getTrans_result().get(0).getDst()).show();

                    //假设现在在子线程了

                    Message msg = myHandler.obtainMessage();
                    msg.what = 0;
                    msg.obj = transltResult.getTrans_result();
                    myHandler.sendMessage(msg);
                }
            }.start();

        }
    }

    @Override
    public void onValidCodeSend() {

    }
    @Override
    public void onLoginSuccess(EnglishCodeVo.ResultBean resultBean) {
        englishInfoList = resultBean.getRecords();
        Toasty.success(this,"获取单词成功").show();
        //englishAdapter.notifyDataSetChanged();
        englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);


        //条目子控件点击事件
        englishAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                //判断id
                if (view.getId() == R.id.text1) {
                    if (englishInfoList.get(position).getWord()==null||englishInfoList.get(position).getWord().isEmpty()){
                        return;
                    }
                   speak(englishInfoList.get(position).getWord());
                } else if (view.getId() == R.id.tv_title) {
                    Log.i("tag", "点击了第" + position + "条条目的 标题");
                }
            }
        });



        //开启动画（默认为渐显效果）
        //使用缩放动画
        englishAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT );
        //设置重复执行动画
        englishAdapter.isFirstOnly(false);
        //条目长按事件
        englishAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //Toast.makeText(getActivity(), "长按了第" + englishInfoList.get(position).getWord(), Toast.LENGTH_SHORT).show();
                //notesQuery = notesBox.query().order(ClassifyBean).build();
                //notesBox.put(classifyBean);
                QueryBuilder<WordList> builder = notesBox.query();
                builder.equal(WordList_.classify,resultBean.getRecords().get(0).getClassify());
                List<WordList> youngJoes = builder.build().find();
               // List<WordList> resultBeans = notesQuery.find();
                WordList  wordList = new WordList();
                if (youngJoes.size()==0){
                    wordList.classify = resultBean.getRecords().get(0).getClassify();
                    wordList.classifyBeanList.add(englishInfoList.get(position));
                    long Id=notesBox.put(wordList);
                }else {
                    youngJoes.get(0).classifyBeanList.add(englishInfoList.get(position));
                    youngJoes.get(0).classify = resultBean.getRecords().get(0).getClassify();
                    long Id=notesBox.put(youngJoes.get(0));
                }
                hengxiang.setVisibility(View.VISIBLE);
                shurudanci.setFloatingLabelText("点击搜索这里会显示搜索的结果哦");
                return false;
            }
        });
        // 开启滑动删除
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(EnglishActivity2.this, "onItemSwipeStart", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(EnglishActivity2.this, "clearView", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(EnglishActivity2.this, "onItemSwiped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                Toast.makeText(EnglishActivity2.this, "onItemSwipeMoving", Toast.LENGTH_SHORT).show();
            }
        };
        /*englishAdapter.setUpFetchEnable(true);
        englishAdapter.setStartUpFetchPosition(0);
        englishAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                Log.v("...setUpFetchEnable", "0");
                //startUpFetch();
            }
        });*/

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                Log.v("...", String.valueOf(pastVisiblesItems));
                Log.v("...2", String.valueOf(totalItemCount));
                Log.v("...3", String.valueOf(visibleItemCount));
                if (dy>70){
                    hengxiang.setVisibility(View.GONE);
                }
            }
        });


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

    private void startUpFetch() {
        hengxiang.setVisibility(View.VISIBLE);
        shurudanci.setFloatingLabelText("点击搜索这里会显示搜索的结果哦");
    }

    @Override
    public void onGetMoreSuccess(EnglishCodeVo.ResultBean resultBean) {
        englishAdapter.addData(resultBean.getRecords());
        englishAdapter.loadMoreComplete();
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.warning(this,errorTip).show();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(ClassifyMessageEvent message) {
        if (message.getRecode().equals("classify")){
            classify = message.getClassify();
            englishWordPresenter.getClassify(classify,pageSize,1);
        }
    }
    private void speak(String text) {
        TTSUtils.getInstance().init();
        TTSUtils.getInstance().speak(text);
    }
}
