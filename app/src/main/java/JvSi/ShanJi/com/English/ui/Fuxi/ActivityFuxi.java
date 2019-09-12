package JvSi.ShanJi.com.English.ui.Fuxi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
                    setView(i);
                }else {
                    textView8.setTextColor(Color.RED);
                }
                break;
            case R.id.textView9:
                if (textView9.getText().toString().equals(cureectWord)){
                    i++;
                    setView(i);
                }else {
                    textView9.setTextColor(Color.RED);
                }
                break;
            case R.id.textView10:
                if (textView10.getText().toString().equals(cureectWord)){
                    i++;
                    setView(i);
                }else {
                    textView10.setTextColor(Color.RED);
                }
                break;
            case R.id.textView11:
                if (textView11.getText().toString().equals(cureectWord)){
                    i++;
                    setView(i);
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
        setView(i);
    }

    @Override
    public void onGetMoreSuccess(List<ClassifyBean> result) {

    }

    @Override
    public void onLoginFail(String errorTip) {

    }
}