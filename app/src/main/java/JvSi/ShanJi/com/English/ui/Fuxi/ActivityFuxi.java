package JvSi.ShanJi.com.English.ui.Fuxi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import JvSi.ShanJi.com.English.Base.BaseActivity;
import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.ClassifyBean;
import JvSi.ShanJi.com.English.bean.WordList;
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
    private List<ClassifyBean> classifyBeanToMany = new ArrayList<>();
    private int i =0;
    private String cureectWord;

    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fuxi);
        englishWordPresenter = new EnglishWordPresenter(this);
        unbinder = ButterKnife.bind(this);

        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);


        Intent intent= getIntent();
        //WordList  wordList = (WordList) intent.getSerializableExtra("youngJoes");
        //classifyBeanToMany =wordList.getClassifyBeanList();
        classifyBeanToMany = (List<ClassifyBean>) intent.getSerializableExtra("youngJoes");

        englishWordPresenter.getClassify(classifyBeanToMany.get(0).getClassify(),10000,1);
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
                    textView8.setTextColor(Color.WHITE);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();

                    //setView(i);
                }else {
                    textView8.setTextColor(Color.RED);
                }
                break;
            case R.id.textView9:
                if (textView9.getText().toString().equals(cureectWord)){
                    i++;
                    textView9.setTextColor(Color.WHITE);
                    //setView(i);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                }else {
                    textView9.setTextColor(Color.RED);
                }
                break;
            case R.id.textView10:
                if (textView10.getText().toString().equals(cureectWord)){
                    i++;
                    textView10.setTextColor(Color.WHITE);
                    //setView(i);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                }else {
                    textView10.setTextColor(Color.RED);
                }
                break;
            case R.id.textView11:
                if (textView11.getText().toString().equals(cureectWord)){
                    i++;
                    textView11.setTextColor(Color.WHITE);
                    //setView(i);
                    Runner1 r1 = new Runner1();
                    Thread t = new Thread(r1);
                    t.start();
                }else {
                    textView11.setTextColor(Color.RED);
                }
                break;


                default: break;
        }
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
                setView(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}