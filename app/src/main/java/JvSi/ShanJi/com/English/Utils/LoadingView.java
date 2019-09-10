package JvSi.ShanJi.com.English.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import JvSi.ShanJi.com.English.R;

/**
 * @author zhang
 * 自定义加载提示框
 */
public class LoadingView extends Dialog {

    private CharSequence tip;

    public LoadingView(Context context) {

        super(context, R.style.LoadingDialog);
    }
    public LoadingView(Context context, CharSequence tip) {

        super(context,R.style.LoadingDialog);
        this.tip=tip;
    }

    public LoadingView(Context context, int themeResId ){

        super(context,themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        TextView tv = findViewById(R.id.tv);
        if(tip!=null) {
            tv.setText(tip);
        }

    }








}
