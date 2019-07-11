package gdyj.tydic.com.jinlingapp.adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.Movie;

/**
 * Created by tysheng
 * Date: 2017/5/25 10:47.
 * Email: tyshengsx@gmail.com
 */

public class UpFetchAdapter extends BaseQuickAdapter<Movie, BaseViewHolder> {
    public UpFetchAdapter() {
        super(R.layout.item_movie, null);
    }



    @Override
    protected void convert(BaseViewHolder helper, Movie item) {

    }
}
