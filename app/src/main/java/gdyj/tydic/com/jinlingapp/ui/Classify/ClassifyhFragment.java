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

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import gdyj.tydic.com.jinlingapp.ExpandableItemAdapter;
import gdyj.tydic.com.jinlingapp.MyApplication;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.bean.MySection;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

/**
 * @author binshengzhu
 */
public class ClassifyhFragment extends Fragment implements ClassifyContract.View{
    private Unbinder unbinder;
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private ExpandableItemAdapter expandableItemAdapter;

    private List<MySection> mySectionList =new ArrayList<>();
    private ArrayList<MultiItemEntity> list;

    private Box<ClassifyBean> notesBox;
    private Query<ClassifyBean> notesQuery;
    private BoxStore boxStore;


    //@BindView(R.id.loadView)
    //LoadingView loadingView;
    //@BindView(R.id.imageView2)
    //ImageView imageView2;

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
        boxStore=MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(ClassifyBean.class);
        //notesQuery = notesBox.query().order(ClassifyBean.).build();
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

    @Override
    public void onStart() {
        super.onStart();
        //imageView2.getBackground().setAlpha(100);//0~255透明度值
        Toasty.warning(getActivity(), "onStart", Toast.LENGTH_SHORT, true).show();
        //initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Toasty.warning(getActivity(), "onResume", Toast.LENGTH_LONG, true).show();
        //initData();
    }

    @Override
    public void onPause() {
        Toasty.warning(getActivity(), "onPause", Toast.LENGTH_LONG, true).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        //Toasty.warning(getActivity(), "onStop", Toast.LENGTH_LONG, true).show();
        super.onStop();
    }
    private void initData() {
        classifyPresenter.getClassify();
        /*String token = null;
        if (MyApplication.getInstance().getHasjwt()){
            token = MyApplication.getInstance().getJwt();
            classifyPresenter.getClassify(token);
        }else {
            Toasty.warning(getActivity(), "尚未登录，请先登录哦", Toast.LENGTH_SHORT, true).show();
            //return;
        }*/
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
        /*englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
        englishAdapter.openLoadAnimation();

        */



        mRecyclerView.setAdapter(expandableItemAdapter);
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
//jvoid gdyj.tydic.com.jinlingapp.bean.ClassifyBean.setMeaning(java.lang.String)' on a null object reference
    @Override
    public void onLoginSuccess(List<ClassifyBean> classifyBeans) {
        list = generateData(classifyBeans);


        MySection mySection = new MySection(true,"测试组1");


        mySection.setBannerInfo(classifyBeans);
        mySectionList.add(mySection);
        //englishAdapter.notifyDataSetChanged();
        expandableItemAdapter = new ExpandableItemAdapter(list);
        mRecyclerView.setAdapter(expandableItemAdapter);
        //loadingView.setVisibility(View.GONE);
        //notesBox.put(classifyBean);
        // List<ResultBean> resultBeans = notesQuery.find();
    }

    private ArrayList<MultiItemEntity> generateData(List<ClassifyBean> classifyBeans) {
        int lv0Count = 9;

        int personCount = classifyBeans.size();

        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            ClassifyLevel0Item lv0 = new ClassifyLevel0Item("This is " + i + "th item in Level 0", "subtitle of " + i);


                for (int k = 0; k < personCount; k++) {
                    lv0.addSubItem(classifyBeans.get(k));
                }

            res.add(lv0);
        }

        return res;
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.warning(getActivity(), errorTip, Toast.LENGTH_LONG, true).show();
    }
}
