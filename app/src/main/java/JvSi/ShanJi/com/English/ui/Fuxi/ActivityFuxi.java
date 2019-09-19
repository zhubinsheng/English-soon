package JvSi.ShanJi.com.English.ui.Fuxi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import JvSi.ShanJi.com.English.Base.BaseActivity;
import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.WordList;
import JvSi.ShanJi.com.English.bean.WordList_;
import JvSi.ShanJi.com.English.ui.EnglishWord.EnglishContract;
import JvSi.ShanJi.com.English.ui.EnglishWord.EnglishWordPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class ActivityFuxi extends BaseActivity implements EnglishContract.View{

    private static final int MSG_SUCCESS = 1;
    private Unbinder unbinder;
    private EnglishWordPresenter englishWordPresenter;

    @BindView(R.id.textView7)
    TextView textView7;

    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView11)
    TextView textView11;

    private List<ClassifyBean> englishInfoList;
    private String classify;
    private int i =0;
    private String cureectWord;

    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;
    private List<WordList> youngJoes = new ArrayList<>();
    private WordList wordLists =new WordList();
    private List<ClassifyBean> classifyBeanToMany = new ArrayList<>();

    private boolean cannot = true;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {//此方法在ui线程运行
            switch(msg.what) {
                case MSG_SUCCESS:
                    setView(i);
                    break;

               /* case MSG_FAILURE:
                    Toast.makeText(getApplication(), getApplication().getString(R.string.get_pic_failure), Toast.LENGTH_LONG).show();
                    break;*/
               default: break;
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fuxi);
        englishWordPresenter = new EnglishWordPresenter(this);
        unbinder = ButterKnife.bind(this);
        Calendar calendar =Calendar.getInstance();
        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);

        Intent intent= getIntent();
        //WordList  wordList = (WordList) intent.getSerializableExtra("youngJoes");
        //classifyBeanToMany =wordList.getClassifyBeanList();
         classify = intent.getStringExtra("youngJoes");


        QueryBuilder<WordList> builder = notesBox.query();
        builder.equal(WordList_.classify,classify);
        youngJoes = builder.build().find();

        List<WordList> list = youngJoes.stream()
                .filter((WordList b) -> b.getClassify().equals(classify))
                .collect(Collectors.toList());

        if (list.size()!=0){
            classifyBeanToMany =((List<ClassifyBean>)list.get(0).getClassifyBeanList());
            int day_of_year = calendar.get(Calendar.DAY_OF_YEAR);
            classifyBeanToMany = classifyBeanToMany.stream()
                    .filter((ClassifyBean b) -> b.getDate()==day_of_year)
                    .collect(Collectors.toList());

            List<ClassifyBean> classifyBeanToMany2 = classifyBeanToMany.stream()
                    .filter((ClassifyBean b) -> b.getDate()<day_of_year)
                    .collect(Collectors.toList());

            classifyBeanToMany2.stream().forEach(classifyBean ->{
                classifyBean.setLevel(classifyBean.getLevel()-1);
            });



            wordLists = list.get(0);
        }


        englishWordPresenter.getClassify(classify,10000,1);
    }

    private void setView(int i) {

        if (classifyBeanToMany.size()==i){
            Toasty.info(this,"本词库已全部结束测试").show();
            --i;
            return;
        }
        ClassifyBean classifyBean =  classifyBeanToMany.get(i);
        textView7.setText(classifyBean.getMeaning());
        cureectWord = classifyBean.getWord();

        //textView8.setText(classifyBean.getWord());

        Random random = new Random();
        //int ranInt =  random.nextInt(4);
        List<String> wordList = new ArrayList<>();
        wordList.add(classifyBean.getWord());
        for (int j = 0; j < 3; j++) {
            ClassifyBean classifyBean1   =  englishInfoList.get(random.nextInt(englishInfoList.size()+1));
            englishInfoList.remove(classifyBean1);
            wordList.add(classifyBean1.getWord());
        }
        Collections.shuffle(wordList); // 倒序排列
        textView8.setText(wordList.get(0));
        textView9.setText(wordList.get(1));
        textView10.setText(wordList.get(2));
        textView11.setText(wordList.get(3));

        textView8.setTextColor(Color.WHITE);
        textView9.setTextColor(Color.WHITE);
        textView10.setTextColor(Color.WHITE);
        textView11.setTextColor(Color.WHITE);
        cannot = true;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @OnClick({R.id.textView8,R.id.textView9,R.id.textView10,R.id.textView11})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView8:
                if (textView8.getText().toString().equals(cureectWord)){
                    i++;
                    textView8.setTextColor(Color.GREEN);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                    if (cannot){
                        baocun();
                    }
                    //setView(i);
                }else {
                    textView8.setTextColor(Color.RED);
                    if (cannot){
                        cannot = false;
                        baocunCUO();
                    }
                }
                break;
            case R.id.textView9:
                if (textView9.getText().toString().equals(cureectWord)){
                    i++;
                    textView9.setTextColor(Color.GREEN);
                    //setView(i);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                    if (cannot){
                        baocun();
                    }
                }else {
                    textView9.setTextColor(Color.RED);
                    if (cannot){
                        cannot = false;
                        baocunCUO();
                    }
                }
                break;
            case R.id.textView10:
                if (textView10.getText().toString().equals(cureectWord)){
                    i++;
                    textView10.setTextColor(Color.GREEN);
                    //setView(i);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                    if (cannot){
                        baocun();
                    }
                }else {
                    textView10.setTextColor(Color.RED);
                    if (cannot){
                        cannot = false;
                        baocunCUO();
                    }
                }
                break;
            case R.id.textView11:
                if (textView11.getText().toString().equals(cureectWord)){
                    i++;
                    textView11.setTextColor(Color.GREEN);
                    //setView(i);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                    if (cannot){
                        baocun();
                    }
                }else {
                    textView11.setTextColor(Color.RED);
                    if (cannot){
                        cannot = false;
                        baocunCUO();
                    }
                }
                break;


                default: break;
        }
    }

    private void baocun() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Calendar calendar =Calendar.getInstance();

                switch(classifyBeanToMany.get(i-1).getLevel() ){
                    case 1:
                        //calendar.add( Calendar. DATE, +1); //向前走一天
                        break;
                    case 2:
                        calendar.add( Calendar. DATE, +1); //向前走一天
                        classifyBeanToMany.get(i-1).setLevel(3);
                        break;
                    case 3:
                        calendar.add( Calendar. DATE, +2); //向前走一天
                        classifyBeanToMany.get(i-1).setLevel(4);
                        break;
                    case 4:
                        calendar.add( Calendar. DATE, +6); //向前走一天
                        classifyBeanToMany.get(i-1).setLevel(5);
                        break;
                    case 5:
                        calendar.add( Calendar. DATE, +22); //向前走一天
                        classifyBeanToMany.get(i-1).setLevel(6);
                        break;
                    case 6:
                        calendar.add( Calendar. DATE, +66); //向前走一天
                        classifyBeanToMany.get(i-1).setLevel(7);
                        break;
                    case 7:
                        //不在提醒
                        classifyBeanToMany.get(i-1).setLevel(8);
                        break;
                    default:break;
                }

                classifyBeanToMany.get(i-1).setDate(calendar.get(Calendar.DAY_OF_YEAR));
                boxStore.boxFor(ClassifyBean.class).put(classifyBeanToMany.get(i-1));

            }
        });
    }

    private void baocunCUO() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Calendar calendar =Calendar.getInstance();

                switch(classifyBeanToMany.get(i).getLevel() ){
                    case 1:
                        //calendar.add( Calendar. DATE, +1); //向前走一天
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    case 2:
                        calendar.add( Calendar. DATE, +1); //向前走一天
                        classifyBeanToMany.get(i).setLevel(3);
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    case 3:
                        calendar.add( Calendar. DATE, +2); //向前走一天
                        classifyBeanToMany.get(i).setLevel(4);
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    case 4:
                        calendar.add( Calendar. DATE, +6); //向前走一天
                        classifyBeanToMany.get(i).setLevel(5);
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    case 5:
                        calendar.add( Calendar. DATE, +22); //向前走一天
                        classifyBeanToMany.get(i).setLevel(6);
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    case 6:
                        calendar.add( Calendar. DATE, +66); //向前走一天
                        classifyBeanToMany.get(i).setLevel(7);
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    case 7:
                        //不在提醒
                        classifyBeanToMany.get(i).setLevel(8);
                        classifyBeanToMany.get(i).setColorf(2);
                        break;
                    default:break;
                }

                classifyBeanToMany.get(i).setDate(calendar.get(Calendar.DAY_OF_YEAR));
                boxStore.boxFor(ClassifyBean.class).put(classifyBeanToMany.get(i));

            }
        });
    }

    @Override
    public void onValidCodeSend() {

    }

    @Override
    public void onLoginSuccess(List<ClassifyBean> result) {
        englishInfoList = result;

        mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();//更新下一个
    }

    @Override
    public void onGetMoreSuccess(List<ClassifyBean> result) {

    }

    @Override
    public void onLoginFail(String errorTip) {

    }


    class Runner1 implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                //Thread.currentThread().sleep(1000);
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();//更新下一个
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}