package gdyj.tydic.com.jinlingapp.ui.EnglishWord;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.bean.EnglishCodeVo;


public class EnglishAdapter extends BaseItemDraggableAdapter <EnglishCodeVo.ResultBean.RecordsBean, BaseViewHolder> {
    public EnglishAdapter(int layoutResId, @Nullable List<EnglishCodeVo.ResultBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  EnglishCodeVo.ResultBean.RecordsBean item) {
        ((TextView) helper.getView(R.id.text1)).setText(item.getMeaning());
        ((TextView) helper.getView(R.id.text2)).setText(item.getWord());
        //((TextView) helper.getView(R.id.text2)).setText(item.getWord());
    }
}
