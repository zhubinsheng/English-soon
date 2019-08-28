package gdyj.tydic.com.jinlingapp.ui.Classify;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import gdyj.tydic.com.jinlingapp.R;
import gdyj.tydic.com.jinlingapp.bean.Library;


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
                        holder.setImageResource(R.id.imageView5, R.drawable.binggan);
                        break;
                    case 1:
                        holder.setImageResource(R.id.imageView5, R.drawable.bigenguo);
                        break;
                    case 2:
                        holder.setImageResource(R.id.imageView5, R.drawable.niuyouguo);
                        break;
                    default:
                        break;
                }
                final ClassifyLevel0Item lv0 = (ClassifyLevel0Item) item;
                holder.setText(R.id.text1, lv0.title);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();

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
                final Library library = (Library) item;
                holder.setText(R.id.text1, library.getClassify());

                switch (holder.getLayoutPosition() % 3) {
                    case 0:
                        holder.setImageResource(R.id.imageView5, R.drawable.binggan);
                        break;
                    case 1:
                        holder.setImageResource(R.id.imageView5, R.drawable.bigenguo);
                        break;
                    case 2:
                        holder.setImageResource(R.id.imageView5, R.drawable.niuyouguo);
                        break;
                    default:
                        break;
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        EventBus.getDefault().post(ClassifyMessageEvent.getInstance("classify",library.getClassify()));
                    }
                });
                break;
            default:
                break;
        }
    }
}