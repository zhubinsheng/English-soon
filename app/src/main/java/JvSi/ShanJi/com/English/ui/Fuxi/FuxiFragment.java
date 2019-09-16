package JvSi.ShanJi.com.English.ui.Fuxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import JvSi.ShanJi.com.English.Base.BaseFragment;
import JvSi.ShanJi.com.English.Base.MyApplication;
import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.WordList;
import JvSi.ShanJi.com.English.ui.Classify.ClassifyMessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class FuxiFragment extends BaseFragment {
    private Unbinder unbinder;
    private Box<WordList> notesBox;
    private Query<WordList> notesQuery;
    private BoxStore boxStore;

    private List<WordList> youngJoes;

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
        youngJoes = builder.build().find();
        EventBus.getDefault().register(this);
         //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(layoutManager);
        fuxiAdapter = new FuxiAdapter(R.layout.english_ceshi, youngJoes);
        //条目点击事件
        fuxiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ActivityFuxi.class);
                String classify  = youngJoes.get(position).getClassify();
                intent.putExtra("youngJoes",classify);
                startActivity(intent);
            }
        });

        rv_list.setAdapter(fuxiAdapter);
    }

    @Override
    public void onResume() {
        QueryBuilder<WordList> builder = notesBox.query();
        youngJoes = builder.build().find();
        fuxiAdapter.notifyDataSetChanged();
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //EventBus.getDefault().unregister(this);
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(ClassifyMessageEvent message) {
        if (message.getRecode().equals("gegnxin")){
            QueryBuilder<WordList> builder = notesBox.query();
            youngJoes = builder.build().find();
            fuxiAdapter = new FuxiAdapter(R.layout.english_ceshi, youngJoes);
            rv_list.setAdapter(fuxiAdapter);
        }
    }
}
