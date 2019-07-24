package gdyj.tydic.com.jinlingapp.ui;

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

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.EnglishAdapter;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;

public class EnglishFragment extends Fragment {
    private View layout;
    private ImageView back;
    private TextView title;
    private RecyclerView mRecyclerView;
    private EnglishAdapter englishAdapter;
    private List<EnglishInfo> englishInfoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_classify, container, false);
        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        //initAdapter();
}


    private void initView() {
        mRecyclerView=(RecyclerView)layout.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        back = (ImageView) layout.findViewById(R.id.img_back);
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
        //englishAdapter = new EnglishAdapter(R.layout.english_ceshi, englishInfoList);
        englishAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(englishAdapter);
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

}
