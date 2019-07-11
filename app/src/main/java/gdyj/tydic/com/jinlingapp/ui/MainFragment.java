package gdyj.tydic.com.jinlingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import gdyj.tydic.com.jinlingapp.ClassifyAdapter;
import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.adapter.HeaderAndFooterAdapter;

/**
 * @author Administrator
 */
public class MainFragment extends Fragment implements ClassifyAdapter.OnClickItemListener {
  public static final String TAG = MainFragment.class.getSimpleName();
  private ListView mListView;
  private ClassifyAdapter adapter;
  private ImageView mHeaderImage;
  private ImageView mBottomImage;
  private View layout;

    private RecyclerView mRecyclerView;
    private HeaderAndFooterAdapter headerAndFooterAdapter;
    private static final int PAGE_SIZE = 20;
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
    /*setupView();*/
  }
  /**
   * Header的添加最好放在setAdapter之前，在Android4.4之前，Header添加必须放在设置Adapter之前
   */
  private void initView() {
      mRecyclerView=(RecyclerView)layout.findViewById(R.id.rv_list);
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      initAdapter();

    View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify_header,null);
    /*View headerViewTwo = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify_header,null);*/
    headerAndFooterAdapter.addHeaderView(headerView);
    /*headerAndFooterAdapter.addHeaderView(headerViewTwo);*/
    mRecyclerView.setAdapter(headerAndFooterAdapter);
    //header

    /*mHeaderImage = ((ImageView) headerView.findViewById(R.id.teach_classify_lv_header));*/
    //可以添加多个HeaderView
    /*mListView.addHeaderView(headerView);
    //bottom
    View bottomView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify_bottom,null);
    mBottomImage = ((ImageView) bottomView.findViewById(R.id.teach_classify_bottom));
    mListView.addFooterView(bottomView);
    adapter = new ClassifyAdapter(getActivity(), null);*/
    /*mRecyclerView.setAdapter(headerAndFooterAdapter);*/
  }

  @Override
  public void onOnclickItem(int position) {

  }
    private void initAdapter() {
        headerAndFooterAdapter = new HeaderAndFooterAdapter(PAGE_SIZE);
        mRecyclerView.setAdapter(headerAndFooterAdapter);
    }
  /**
   * 网络请求
   *//*
  private void setupView() {
    HttpUtil.getStringAsync(HttpConstant.CLASSIFY_URL, new HttpUtil.RequestCallBack() {
      @Override
      public void onFailure() {
        Log.e(TAG, "onFailure: ");
      }

      @Override
      public void onSuccess(String result) {
        Log.e(TAG, "onSuccess: " + result);
        Gson gson = new Gson();
        ClassifyList classifyList = gson.fromJson(result, ClassifyList.class);
        List<Classify> list = classifyList.getList();
        //更新适配器
        adapter.updateRes(list);
        //更新Header
        ImageLoader.display(mHeaderImage,list.get(0).getCoverPath());
      }

      @Override
      public void onFinish() {
        Log.e(TAG, "onFinish: ");
      }
    });
    String URL_BOTTOM="http://adse.ximalaya.com/ting?device=android&name=cata_index_banner&network=wifi&operator=0&version=4.3.98";
    HttpUtil.getStringAsync(URL_BOTTOM, new HttpUtil.RequestCallBack() {
      @Override
      public void onFailure() {
      }

      @Override
      public void onSuccess(String result) {
        Gson gson = new Gson();
        ClassifyBottomList classifyBottomList = gson.fromJson(result, ClassifyBottomList.class);
        ImageLoader.display(mBottomImage, classifyBottomList.getData().get(0).getCover());
      }

      @Override
      public void onFinish() {
      }
    });
  }
  @Override
  public void onOnclickItem(int position) {
    Log.e(TAG, "onOnclickItem:------------- "+position );
  }*/
}