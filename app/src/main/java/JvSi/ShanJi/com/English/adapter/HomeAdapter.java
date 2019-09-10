package JvSi.ShanJi.com.English.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.HomeItem;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setImageResource(R.id.icon, item.getImageResource());
    }
}
