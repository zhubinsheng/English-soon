package gdyj.tydic.com.jinlingapp;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import gdyj.tydic.com.jinlingapp.bean.ClassifyBean;
import gdyj.tydic.com.jinlingapp.ui.Classify.ClassifyLevel0Item;


/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;

    public static final int TYPE_PERSON = 2;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.classify_ceshi);

        addItemType(TYPE_PERSON, R.layout.classify_ceshi);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                switch (holder.getLayoutPosition() % 3) {
                    case 0:
                        holder.setImageResource(R.id.imageView5, R.mipmap.ic_launcher_round);
                        break;
                    case 1:
                        holder.setImageResource(R.id.imageView5, R.mipmap.ic_launcher_round);
                        break;
                    case 2:
                        holder.setImageResource(R.id.imageView5, R.mipmap.ic_launcher_round);
                        break;
                    default:
                        break;
                }
                final ClassifyLevel0Item lv0 = (ClassifyLevel0Item) item;
                holder.setText(R.id.textView6, lv0.title);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 1 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
//                            if (pos % 3 == 0) {
//                                expandAll(pos, false);
//                            } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;

            case TYPE_PERSON:
                final ClassifyBean classifyBean = (ClassifyBean) item;
                holder.setText(R.id.text1, classifyBean.getMeaning());
                holder.setText(R.id.textView6, classifyBean.getWord());
                /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        // 先获取到当前 item 的父 positon，再移除自己
                        int positionAtAll = getParentPositionInAll(pos);
                        remove(pos);
                        if (positionAtAll != -1) {
                            IExpandable multiItemEntity = (IExpandable) getData().get(positionAtAll);
                            if (!hasSubItems(multiItemEntity)) {
                                remove(positionAtAll);
                            }
                        }
                    }
                });*/
                break;
            default:
                break;
        }
    }
}