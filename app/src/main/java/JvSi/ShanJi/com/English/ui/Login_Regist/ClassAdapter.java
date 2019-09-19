package JvSi.ShanJi.com.English.ui.Login_Regist;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.ClassResult;

public class ClassAdapter extends BaseQuickAdapter<ClassResult.ClassResultBean, BaseViewHolder> {
    public ClassAdapter(int layoutResId, @Nullable List<ClassResult.ClassResultBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ClassResult.ClassResultBean item) {
        ((TextView) helper.getView(R.id.text2)).setText(item.getClasss());
        ((TextView) helper.getView(R.id.text1)).setText(item.getAllcount());
        helper.addOnClickListener(R.id.button2);
    }
}
