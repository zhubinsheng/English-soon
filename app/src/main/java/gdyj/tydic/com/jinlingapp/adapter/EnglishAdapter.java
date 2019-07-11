package gdyj.tydic.com.jinlingapp.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.EnglishInfo;


public class EnglishAdapter extends BaseQuickAdapter <EnglishInfo, BaseViewHolder> {
    public EnglishAdapter(int layoutResId, @Nullable List<EnglishInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EnglishInfo item) {
        ((TextView) helper.getView(R.id.text1)).setText(item.getMeaning());
        ((TextView) helper.getView(R.id.text2)).setText(item.getWord());
    }
}
