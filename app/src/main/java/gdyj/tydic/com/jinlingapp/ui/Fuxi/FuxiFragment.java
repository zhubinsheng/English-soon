package gdyj.tydic.com.jinlingapp.ui.Fuxi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.warkiz.widget.IndicatorSeekBar;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gdyj.tydic.com.jinlingapp.Base.BaseFragment;
import gdyj.tydic.com.jinlingapp.Base.MyApplication;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.WordList;
import gdyj.tydic.com.jinlingapp.ui.EnglishWord.EnglishAdapter;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class FuxiFragment extends BaseFragment {
    private Unbinder unbinder;
    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;

    private FuxiAdapter fuxiAdapter;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fuxi_list;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        //EventBus.getDefault().register(this);
        boxStore= MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(WordList.class);
        QueryBuilder<WordList> builder = notesBox.query();
        List<WordList> youngJoes = builder.build().find();
        fuxiAdapter = new FuxiAdapter(R.layout.english_ceshi, youngJoes);
        rv_list.setAdapter(fuxiAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
}
