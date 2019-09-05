package gdyj.tydic.com.jinlingapp.ui.Fuxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gdyj.tydic.com.jinlingapp.Base.BaseFragment;
import gdyj.tydic.com.jinlingapp.Base.MyApplication;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.bean.WordList;
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
                List<ClassifyBean> classifyBeanToMany = youngJoes.get(position).getClassifyBeanList();
                intent.putExtra("youngJoes",  (Serializable) classifyBeanToMany);
                startActivity(intent);
            }
        });

        rv_list.setAdapter(fuxiAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
}
