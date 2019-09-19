package JvSi.ShanJi.com.English.ui.Login_Regist;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import JvSi.ShanJi.com.English.bean.ClassResult;

public class ClassAdapter extends BaseQuickAdapter<ClassResult, BaseViewHolder> {
    public ClassAdapter(int layoutResId, @Nullable List<ClassResult> data) {
        super(layoutResId, data);
    }

    public ClassAdapter(@Nullable List<ClassResult> data) {
        super(data);
    }

    public ClassAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassResult item) {

    }
}
