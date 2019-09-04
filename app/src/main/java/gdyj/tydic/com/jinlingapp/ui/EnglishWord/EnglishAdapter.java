package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;


public class EnglishAdapter extends BaseItemDraggableAdapter <ClassifyBean, BaseViewHolder> {

    private boolean isShow;
    private static final int RED = 0xFFFF0000;
    private static final int GREEN = 0xFF00FF00;
    private static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;

    public EnglishAdapter(int layoutResId, @Nullable List<ClassifyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  ClassifyBean item) {
        ((TextView) helper.getView(R.id.text2)).setText(item.getMeaning());
        ((TextView) helper.getView(R.id.text1)).setText(item.getWord());

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
        //单词默认显示绿色
        ((TextView) helper.getView(R.id.text2)).setTextColor(GREEN);
    }


    //改变显示删除的imageview，通过定义变量isShow去接收变量isManager
    public void changetShowDelImage(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }
}
