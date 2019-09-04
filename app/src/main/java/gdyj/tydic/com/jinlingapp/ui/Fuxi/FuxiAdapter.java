package gdyj.tydic.com.jinlingapp.ui.Fuxi;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.WordList;


public class FuxiAdapter extends BaseQuickAdapter<WordList, BaseViewHolder> {

    private boolean isShow;
    private static final int RED = 0xFFFF0000;
    private static final int GREEN = 0xFF00FF00;
    private static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;

    public FuxiAdapter(int layoutResId, @Nullable List<WordList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  WordList item) {
        if (!item.getClassify().isEmpty()){
            ((TextView) helper.getView(R.id.text1)).setVisibility(View.GONE);
            helper.setText(R.id.text2, item.getClassify())
                    .setText(R.id.button2,"点击进入复习");
        }
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                ((TextView) helper.getView(R.id.text2)).setTextColor(RED);
                break;
            case 1:
                ((TextView) helper.getView(R.id.text2)).setTextColor(BLUE);
                break;
            case 2:
                ((TextView) helper.getView(R.id.text2)).setTextColor(GREEN);
                break;
            default:
                break;
        }
    }
    //改变显示删除的imageview，通过定义变量isShow去接收变量isManager
    public void changetShowDelImage(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }
}
