package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;


public class EnglishAdapter extends BaseItemDraggableAdapter <ClassifyBean, BaseViewHolder> {
    public EnglishAdapter(int layoutResId, @Nullable List<ClassifyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  ClassifyBean item) {
        ((TextView) helper.getView(R.id.text1)).setText(item.getMeaning());
        ((TextView) helper.getView(R.id.text2)).setText(item.getWord());
        //((TextView) helper.getView(R.id.text2)).setText(item.getWord());
    }
}
