package JvSi.ShanJi.com.English.Base;

import android.support.v7.app.AppCompatActivity;

import JvSi.ShanJi.com.English.Utils.LoadingView;


/**
 * Created by zhao
 */

public class BaseActivity extends AppCompatActivity {


    private LoadingView loadingView;


    /**
     * 加载提示
     */
    public void showLoading(){

        if(loadingView==null){

            loadingView = new LoadingView(this);
        }

        loadingView.show();

    }
    /**
     * 加载提示自定义文本
     */
    public void showLoading(String tip){

        if(loadingView==null){
            loadingView = new LoadingView(this,tip);
        }

        loadingView.show();

    }

    /**
     * 关闭加载提示
     */
    public void hideLoading(){

        if(loadingView!=null){
            loadingView.dismiss();
        }
    }


}
