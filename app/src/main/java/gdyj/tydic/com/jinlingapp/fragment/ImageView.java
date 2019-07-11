package gdyj.tydic.com.jinlingapp.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.HomeAdapter;
import gdyj.tydic.com.jinlingapp.bean.HomeItem;

public class ImageView extends FrameLayout {
    private static final Class<?>[] ACTIVITY = {};
    private static final int[] IMG = {R.drawable.headerandfooter_img1, R.drawable.headerandfooter_img1};
    private ArrayList<HomeItem> mDataList;
    private RecyclerView mRecyclerView;
    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }


    private void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_classify_header_two,this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        initData();
        initAdapter();
    }
    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.image_buttun_item_view, mDataList);
        homeAdapter.openLoadAnimation();

       /* homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });
*/
        mRecyclerView.setAdapter(homeAdapter);
    }
    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < IMG.length; i++) {
            HomeItem item = new HomeItem();
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }
}
