package JvSi.ShanJi.com.English.ui.Classify;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mingle.widget.LoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.Library;
import JvSi.ShanJi.com.English.bean.MySection;
import JvSi.ShanJi.com.English.bean.WordList;
import JvSi.ShanJi.com.English.bean.localLibrary;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

/**
 * @author binshengzhu
 */
public class ClassifyhActivty extends AppCompatActivity implements ClassifyContract.View{
    private Unbinder unbinder;
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private ExpandableItemAdapter expandableItemAdapter;
    private Box<WordList> notesBox;
    private List<MySection> mySectionList =new ArrayList<>();
    private ArrayList<MultiItemEntity> list;

    //private Box<ClassifyBean> notesBox;
    private Query<ClassifyBean> notesQuery;
    private BoxStore boxStore;


    @BindView(R.id.loadView)
    LoadingView loadingView;
    //@BindView(R.id.imageView2)
    //ImageView imageView2;

    private ClassifyPresenter classifyPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classify);
        classifyPresenter = new ClassifyPresenter(this);
        unbinder = ButterKnife.bind(this);
        boxStore=MyApplication.getInstance().getBoxStore();
        initView();
        initData(); // retrofit请求范例
        initAdapter();
    }
    @Nullable
   /* @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_classify, container, false);
        //notesBox = boxStore.boxFor(ClassifyBean.class);
        //notesQuery = notesBox.query().order(ClassifyBean.).build();
        return layout;
    }*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        classifyPresenter.onDetach();
    }
    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }*/

    private void initData() {
        classifyPresenter.getLibrary();
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
        mRecyclerView=(RecyclerView)findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        /*list = generateData(classifyBeans);
        MySection mySection = new MySection(true,"测试组1");
        mySection.setBannerInfo(classifyBeans);
        mySectionList.add(mySection);


        //englishAdapter.notifyDataSetChanged();
        expandableItemAdapter = new ExpandableItemAdapter(list);
        mRecyclerView.setAdapter(expandableItemAdapter);*/
        //loadingView.setVisibility(View.GONE);
        //notesBox.put(classifyBean);
        // List<ResultBean> resultBeans = notesQuery.find();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private ArrayList<MultiItemEntity> generateData(Map<String, List<Library>> classifyBeans) {

        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);
        QueryBuilder<WordList> builder = notesBox.query();
        List<WordList> youngJoes = builder.build().find();
        List<WordList> list2 = youngJoes.stream()
                .filter((WordList b) -> b.getClassify().contains("个人词库"))
                .collect(Collectors.toList());

        int lv0Count = classifyBeans.size();
        int personCount = classifyBeans.size();

        ArrayList<MultiItemEntity> res = new ArrayList<>();

        List<Library> libraryls = new ArrayList<>();
        if (list2.size()!=0){
            for (int j = 0; j < list2.size(); j++) {
                Library library = new Library();
                library.setLibrary("个人词库");
                library.setClassify(list2.get(j).getClassify());
                libraryls.add(library);
            }
            classifyBeans.put("个人词库",libraryls);
        }else {
            Library library = new Library();
            library.setLibrary("个人词库");
            library.setClassify("空词库");
            libraryls.add(library);
            classifyBeans.put("个人词库",libraryls);
        }

        //遍历分组
        for (Map.Entry<String, List<Library>> entryUser : classifyBeans.entrySet()) {
            String key = entryUser.getKey();
            List<Library> entryUserList = entryUser.getValue();


            ClassifyLevel0Item lv0 = new ClassifyLevel0Item(key, "subtitle null");
            for (int k = 0; k < entryUserList.size(); k++) {
                lv0.addSubItem(entryUserList.get(k));
            }
            res.add(lv0);

        }
       /* for (int i = 0; i < lv0Count; i++) {
            ClassifyLevel0Item lv0 = new ClassifyLevel0Item(classifyBeans.get, "subtitle of " + i);
                for (int k = 0; k < personCount; k++) {
                    lv0.addSubItem(classifyBeans.get(k));
                }

            res.add(lv0);
        }*/

        return res;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private ArrayList<MultiItemEntity> generateData2() {

        Map<String, List<WordList>> classifyBeans = new HashMap<>();

        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);
        QueryBuilder<WordList> builder = notesBox.query();
        List<WordList> youngJoes = builder.build().find();

        classifyBeans.put("本地词库",youngJoes);

        List<WordList> list2 = youngJoes.stream()
                .filter((WordList b) -> b.getClassify().contains("个人词库"))
                .collect(Collectors.toList());

        ArrayList<MultiItemEntity> res = new ArrayList<>();

        List<WordList> libraryls = new ArrayList<>();
        if (list2.size()!=0){
            for (int j = 0; j < list2.size(); j++) {
                WordList library = new WordList();
                library.setLibrary("个人词库");
                library.setClassify(list2.get(j).getClassify());
                libraryls.add(library);
            }
            classifyBeans.put("个人词库",libraryls);
        }else {
            WordList library = new WordList();
            library.setLibrary("个人词库");
            library.setClassify("空词库");
            libraryls.add(library);
            classifyBeans.put("个人词库",libraryls);
        }

        //遍历分组
        for (Map.Entry<String, List<WordList>> entryUser : classifyBeans.entrySet()) {
            String key = entryUser.getKey();
            List<WordList> entryUserList = entryUser.getValue();

            ClassifyLevel1Item lv0 = new ClassifyLevel1Item(key, "subtitle null");
            for (int k = 0; k < entryUserList.size(); k++) {
                lv0.addSubItem(entryUserList.get(k));
            }
            res.add(lv0);

        }

        return res;
    }

    @Override
    public void onLoginFail(String errorTip) {
        Toasty.warning(this, errorTip, Toast.LENGTH_LONG, true).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onGetLibrarySuccess(Map<String, List<Library>> result) {
        if (result==null){

            list = generateData2();
            ExpandableItemAdapter1 expandableItemAdapter1 = new ExpandableItemAdapter1(this,list);
            mRecyclerView.setAdapter(expandableItemAdapter1);
            Toasty.warning(this, "加载本地学习记录", Toast.LENGTH_LONG, true).show();
            loadingView.setVisibility(View.GONE);
            return;
        }
        loadingView.setVisibility(View.GONE);
        list = generateData(result);

        BoxStore boxStore= MyApplication.getInstance().getBoxStore();
        Box<localLibrary> notesBox2 = boxStore.boxFor(localLibrary.class);
        QueryBuilder<localLibrary> builder = notesBox2.query();
        List<localLibrary> joes = builder.build().find();

        expandableItemAdapter = new ExpandableItemAdapter(this,list,joes);
        mRecyclerView.setAdapter(expandableItemAdapter);
    }
}