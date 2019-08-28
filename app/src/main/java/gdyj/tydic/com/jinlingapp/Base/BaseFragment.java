package gdyj.tydic.com.jinlingapp.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gdyj.tydic.com.jinlingapp.Utils.LoadingView;

//import butterknife.ButterKnife;

//import butterknife.ButterKnife;

/**
 * Created by zhao
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    private LoadingView loadingView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this,view);
    }


    protected abstract int getLayoutId();



    //显示加载提示
    public void showLoading(){

        if(loadingView==null){

            loadingView = new LoadingView(getActivity());
        }

        loadingView.show();

    }

    public void showLoading(String tip){

        if(loadingView==null){
            loadingView = new LoadingView(getActivity(),tip);
        }

        loadingView.show();

    }

    //关闭加载提示
    public void hideLoading(){

        if(loadingView!=null){
            loadingView.dismiss();
        }
    }

}
