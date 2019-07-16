package gdyj.tydic.com.jinlingapp.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyL;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;


public class EnglishAdapter extends BaseItemDraggableAdapter < ClassifyL.ResultBean, BaseViewHolder> {
    public EnglishAdapter(int layoutResId, @Nullable List< ClassifyL.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  ClassifyL.ResultBean item) {
        ((TextView) helper.getView(R.id.text1)).setText(item.getClassify());
        //((TextView) helper.getView(R.id.text2)).setText(item.getWord());
    }
}
