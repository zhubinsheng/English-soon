package gdyj.tydic.com.jinlingapp.ui.Fuxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gdyj.tydic.com.jinlingapp.Base.BaseActivity;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.bean.WordList;
import io.objectbox.relation.ToMany;

public class ActivityFuxi extends BaseActivity {

    private Unbinder unbinder;

    @BindView(R.id.textView7)
    TextView textView7;

    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.textView9)
    TextView textView;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView11)
    TextView textView11;

    private ToMany<ClassifyBean> classifyBeanToMany = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fuxi);
        unbinder = ButterKnife.bind(this);
        Intent intent= getIntent();
        WordList  wordList = (WordList) intent.getSerializableExtra("youngJoes");
        classifyBeanToMany =wordList.getClassifyBeanList();
        setView(0);
    }

    private void setView(int i) {
        ClassifyBean classifyBean =  classifyBeanToMany.get(i);
        textView7.setText(classifyBean.getMeaning());
        textView8.setText(classifyBean.getWord());

        Random random = new Random();
        int ranInt =  random.nextInt(5);
        System.out.println("Method two:" + random.nextInt(5));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}