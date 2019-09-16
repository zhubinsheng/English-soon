package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;
import com.mingle.widget.LoadingView;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.Utils.SharedPreferencesUtils;
import JvSi.ShanJi.com.English.baiduUtils.TTSUtils;
import JvSi.ShanJi.com.English.baiduUtils.java.bin.com.baidu.translate.demo.Main;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.LearningSit;
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

import static JvSi.ShanJi.com.English.Utils.HeXinUtil.insertCalendarEvent;


/**
 * @author Administrator
 */
public class EnglishFragment extends Fragment implements EnglishContract.View {
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
    private static int pageSize = 30;
    private static long delayMillis = 1000;
    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int isShow = 0;

    private int position = 0;


    private int count=0;
    private static final long DOUBLE_TIME = 400;
    private static long lastClickTime = 0;
    private int positionDobuu;
    private List<WordList> youngJoes = new ArrayList<>();
    private WordList wordLists =new WordList();

    @BindView(R.id.jieguo)
    LinearLayout jieguo;

    @BindView(R.id.hengxiang)
    LinearLayout hengxiang;

    @BindView(R.id.shurudanci)
    MaterialEditText shurudanci;

    @BindView(R.id.sousuo)
    TextView sousuo;

    @BindView(R.id.textView32)
    TextView textView32;


    @BindView(R.id.imageView11)
    ImageView imageView11;

    @BindView(R.id.imageView12)
    ImageView imageView12;

    @BindView(R.id.imageView13)
    ImageView imageView13;

    @BindView(R.id.imageView14)
    ImageView imageView14;

    @BindView(R.id.loadView)
    LoadingView loadingView;

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
                    jieguo.setVisibility(View.VISIBLE);
                    textView32.setText(value);
                    //shurudanci.setFloatingLabelText(value);
                case 1: //yyy操作
                    break;
                default:
                    break;
            }
        }
    };
    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_english, container, false);
        englishWordPresenter = new EnglishWordPresenter(this);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this,layout);
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


        RefreshLayout refreshLayout = (RefreshLayout)layout.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                startUpFetch();
                //refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        return layout;
    }

    private void onGetSuccess(List<ClassifyBean> classifyBeanList) {
        englishInfoList = classifyBeanList;

        loadingView.setVisibility(View.GONE);
        Toasty.success(getActivity(),"获取单词成功").show();
        //englishAdapter.notifyDataSetChanged();
        englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
        //条目子控件点击事件
        englishAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                //判断id
                if (view.getId() == R.id.text1) {
                    Log.v("====", String.valueOf(position));
                    speak(englishInfoList.get(position).getWord());
                } else if (view.getId() == R.id.shuangji) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - lastClickTime < DOUBLE_TIME) {
                        Log.v("..--------------------.", String.valueOf(position));
                        if (englishInfoList.get(position).getColorf() != 0) {
                            englishInfoList.get(position).setColorf(0);
                        } else {
                            englishInfoList.get(position).setColorf(1);
                        }
                        //更新单词状态
                        AsyncTask.execute(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                String classify_cuncu  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(getActivity(),"classify",""));
                                QueryBuilder<WordList> builder = notesBox.query();
                                builder.equal(WordList_.classify,classify_cuncu);
                                youngJoes = builder.build().find();
                                List<WordList> list = youngJoes.stream()
                                        .filter((WordList b) -> b.getClassify().equals(classify_cuncu))
                                        .collect(Collectors.toList());
                                if (list.size()!=0){
                                    wordLists= list.get(0);
                                    wordLists.getClassifyBeanList().get(position).setColorf(1);
                                    wordLists.getClassifyBeanList().get(position).setLevel(2);
                                    //wordLists = list.get(0);
                                    //long Id=notesBox.put(wordLists);
                                }
                                Calendar calendar =Calendar.getInstance();
                                //当前年的第几天
                                int day_of_year = calendar.get(Calendar.DAY_OF_YEAR);
                                wordLists.getClassifyBeanList().get(position).setDate(day_of_year);
                                boxStore.boxFor(ClassifyBean.class).put(wordLists.getClassifyBeanList().get(position));
                                count++;
                            }
                        });
                        /*new Thread(){
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                               *//* Log.v("..--------------------.", String.valueOf(wordLists.classifyBeanList.size()));
                                wordLists.classify = englishInfoList.get(0).getClassify();
                                //wordList.classifyBeanList.addAll(englishInfoList);
                                ClassifyBean classifyBean = wordLists.classifyBeanList.get(position);
                                wordLists.classifyBeanList.get(position).setColorf(1);
                                long Id=notesBox.put(wordLists);
                                Log.v("..--------------------.", String.valueOf(Id));*//*



                                String classify_cuncu  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(getActivity(),"classify",""));
                                QueryBuilder<WordList> builder = notesBox.query();
                                builder.equal(WordList_.classify,classify_cuncu);
                                youngJoes = builder.build().find();
                                List<WordList> list = youngJoes.stream()
                                        .filter((WordList b) -> b.getClassify().equals(classify_cuncu))
                                        .collect(Collectors.toList());
                                if (list.size()!=0){
                                    list.get(0).getClassifyBeanList().get(position).setColorf(1);
                                    wordLists = list.get(0);
                                    long Id=notesBox.put(wordLists);
                                }

                                Calendar calendar =Calendar. getInstance();
                                calendar.add( Calendar. DATE, +1); //向前走一天
                                Date date= calendar.getTime();

                                // 获取日
                                int date2 = calendar.get(Calendar.DATE);
                                wordLists.getClassifyBeanList().get(position).setDate(date);

                                //当前月的第几天：即当前日
                                int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);

                                //当前年的第几天
                                int day_of_year = calendar.get(Calendar.DAY_OF_YEAR);


                                boxStore.boxFor(ClassifyBean.class).put(wordLists.getClassifyBeanList().get(position));






                            }
                        }.start();*/
                        englishAdapter.notifyDataSetChanged();
                    }
                    lastClickTime = currentTimeMillis;
                }
            }
        });
        //开启动画（默认为渐显效果）
        //使用缩放动画
        //englishAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT );
        //设置重复执行动画
        englishAdapter.isFirstOnly(false);
        //条目长按事件
        englishAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "长按了第" + englishInfoList.get(position).getWord(), Toast.LENGTH_SHORT).show();
                //notesQuery = notesBox.query().order(ClassifyBean).build();
                //notesBox.put(classifyBean);
                //QueryBuilder<WordList> builder = notesBox.query();
                //builder.equal(WordList_.classify,result.get(0).getClassify());
                //List<WordList> youngJoes = builder.build().find();
                // List<WordList> resultBeans = notesQuery.find();
                /*WordList  wordList = new WordList();
                if (youngJoes.size()==0){
                    wordList.classify = result.get(0).getClassify();
                    wordList.classifyBeanList.add(englishInfoList.get(position));
                    long Id=notesBox.put(wordList);
                }else {
                    youngJoes.get(0).classifyBeanList.add(englishInfoList.get(position));
                    youngJoes.get(0).classify = result.get(0).getClassify();
                    long Id=notesBox.put(youngJoes.get(0));
                }*/
                hengxiang.setVisibility(View.VISIBLE);
                shurudanci.setFloatingLabelText("点击搜索下面会显示搜索的结果哦");
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
//                Log.v("...", String.valueOf(pastVisiblesItems));
//                Log.v("...2", String.valueOf(totalItemCount));
//                Log.v("...3", String.valueOf(visibleItemCount));
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
                       /* if (resultBean.getCurrent() >= resultBean.getPages()) {
                            //数据全部加载完毕
                            englishAdapter.loadMoreEnd();
                        } else {
                            englishWordPresenter.getClassify(classify,pageSize,resultBean.getCurrent()+1);

                           *//* if (isErr) {
                                //成功获取更多数据
                                englishAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                                mCurrentCounter = mQuickAdapter.getData().size();
                                mQuickAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                                mQuickAdapter.loadMoreFail();

                            }*//*
                        }*/
                    }

                }, delayMillis);
            }
        }, mRecyclerView);

        mRecyclerView.setAdapter(englishAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView() {
        Toasty.info(getActivity(),"右滑进进入菜单选择词库/观看教程").show();
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView=(RecyclerView)layout.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MoveToPosition(mLayoutManager,mRecyclerView,20);
        //back = (ImageView) layout.findViewById(R.id.img_back);

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

        String classify_cuncu  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(getActivity(),"classify",""));
        QueryBuilder<WordList> builder = notesBox.query();
        builder.equal(WordList_.classify,classify_cuncu);
        youngJoes = builder.build().find();

        List<WordList> list = youngJoes.stream()
                .filter((WordList b) -> b.getClassify().equals(classify_cuncu))
                .collect(Collectors.toList());

        if (list.size()!=0){
            onGetSuccess((List<ClassifyBean>)list.get(0).getClassifyBeanList());
            wordLists = list.get(0);
        }

    }
    private void initAdapter() {
        //englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
        //englishAdapter.openLoadAnimation();
        //mRecyclerView.setAdapter(englishAdapter);
    }

    @OnClick({R.id.sousuo,R.id.imageView10,R.id.imageView11,R.id.tianjia,R.id.imageView13})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView13:
                Calendar beginCalendar = Calendar.getInstance();
                beginCalendar.add( Calendar. MINUTE, +45); //向前走一天
                long beginTimeMillis = beginCalendar.getTimeInMillis();
                insertCalendarEvent(getActivity(),"该复习单词了","本日需复习单词20个，现在复习记忆效果特别好哦",beginTimeMillis,0);
                Toasty.info(getActivity(),"已提交本次所记单词,并加入复习计划").show();

                //上传到后台今日所学

                if (MyApplication.getInstance().getHasjwt()&&count!=0){
                    LearningSit learningSit = new LearningSit();
                    learningSit.setUserid(MyApplication.getInstance().getUserInfoBean().getId());
                    learningSit.setClassifyId(wordLists.getClassify());
                    learningSit.setCount(String.valueOf(count));
                    englishWordPresenter.addLearningSit(learningSit);
                    count=0;
                }else {
                    Toasty.info(getActivity(),"请先登录并改变所记数量").show();
                }


                break;

            case R.id.tianjia:


                break;

            case R.id.sousuo:
               GOsousuo();

                break;

            case R.id.imageView11:

                if (englishInfoList == null){
                    Toasty.info(getActivity(),"右滑进进入菜单加入词库/观看教程").show();
                    return;
                }
                if (isShow == 0){
                    this.isShow =1;
                    imageView11.setImageResource(R.drawable.jyishi);
                }else if (isShow == 1){
                    this.isShow =2;
                    imageView11.setImageResource(R.drawable.jyingyu);
                }else if (isShow == 2){
                    this.isShow =0;
                    imageView11.setImageResource(R.drawable.jdanci);
                }
                englishAdapter.changetShowDelImage();
                break;
            case R.id.imageView10:


                EventBus.getDefault().post(ClassifyMessageEvent.getInstance("qiehuan","0"));
                break;
                default:
                    break;

        }
    }

    private void GOsousuo() {
        if (
                shurudanci.getText() ==null ||shurudanci.getText().toString().isEmpty()
        ){
            Toasty.warning(getActivity(),"请输入正确文字").show();
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
    public void onLoginSuccess(List<ClassifyBean> result) {
        new Thread(){
            @Override
            public void run() {
                WordList  wordList2 = new WordList();
                wordList2.classify = result.get(0).getClassify();
                wordList2.classifyBeanList.addAll(result);
                long Id=notesBox.put(wordList2);
                SharedPreferencesUtils.setParam(getActivity(),"classify",classify);
                String classify_cuncu  = java.lang.String.valueOf(SharedPreferencesUtils.getParam(getActivity(),"classify",""));
                EventBus.getDefault().post(ClassifyMessageEvent.getInstance("gegnxin","0"));
            }
        }.start();

        onGetSuccess(result);
       /* loadingView.setVisibility(View.GONE);
        Toasty.success(getActivity(),"获取单词成功").show();
        //englishAdapter.notifyDataSetChanged();
        englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);


        //条目子控件点击事件
        englishAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                //判断id
                if (view.getId() == R.id.text2) {
                   speak(englishInfoList.get(position).getWord());
                } else if (view.getId() == R.id.shuangji) {

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
                Toast.makeText(getActivity(), "长按了第" + englishInfoList.get(position).getWord(), Toast.LENGTH_SHORT).show();
                //notesQuery = notesBox.query().order(ClassifyBean).build();
                //notesBox.put(classifyBean);
                QueryBuilder<WordList> builder = notesBox.query();
                builder.equal(WordList_.classify,result.get(0).getClassify());
                List<WordList> youngJoes = builder.build().find();
               // List<WordList> resultBeans = notesQuery.find();
                *//*WordList  wordList = new WordList();
                if (youngJoes.size()==0){
                    wordList.classify = result.get(0).getClassify();
                    wordList.classifyBeanList.add(englishInfoList.get(position));
                    long Id=notesBox.put(wordList);
                }else {
                    youngJoes.get(0).classifyBeanList.add(englishInfoList.get(position));
                    youngJoes.get(0).classify = result.get(0).getClassify();
                    long Id=notesBox.put(youngJoes.get(0));
                }*//*
                hengxiang.setVisibility(View.VISIBLE);
                shurudanci.setFloatingLabelText("点击搜索下面会显示搜索的结果哦");
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
        *//*englishAdapter.setUpFetchEnable(true);
        englishAdapter.setStartUpFetchPosition(0);
        englishAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                Log.v("...setUpFetchEnable", "0");
                //startUpFetch();
            }
        });*//*

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
                       *//* if (resultBean.getCurrent() >= resultBean.getPages()) {
                            //数据全部加载完毕
                            englishAdapter.loadMoreEnd();
                        } else {
                            englishWordPresenter.getClassify(classify,pageSize,resultBean.getCurrent()+1);

                           *//**//* if (isErr) {
                                //成功获取更多数据
                                englishAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                                mCurrentCounter = mQuickAdapter.getData().size();
                                mQuickAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                                mQuickAdapter.loadMoreFail();

                            }*//**//*
                        }*//*
                    }

                }, delayMillis);
            }
        }, mRecyclerView);

        mRecyclerView.setAdapter(englishAdapter);*/
    }

    private void startUpFetch() {
        hengxiang.setVisibility(View.VISIBLE);
        shurudanci.setFloatingLabelText("点击搜索这里会显示搜索的结果哦");
    }

    @Override
    public void onGetMoreSuccess(List<ClassifyBean> result) {
        englishAdapter.addData(result);
        englishAdapter.loadMoreComplete();
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.warning(getActivity(),errorTip).show();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(ClassifyMessageEvent message) {
        if (message.getRecode().equals("classify")){
            loadingView.setVisibility(View.VISIBLE);
            classify = message.getClassify();
            englishWordPresenter.getClassify(classify,pageSize,1);
        }else if (message.getRecode().equals("word")){
            String word = message.getClassify();
            //englishInfoList.get();
        }
    }
    private void speak(String text) {
        TTSUtils.getInstance().speak(text);
    }
}
