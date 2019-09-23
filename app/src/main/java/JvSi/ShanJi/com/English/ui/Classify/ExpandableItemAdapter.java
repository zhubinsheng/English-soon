package JvSi.ShanJi.com.English.ui.Classify;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.bean.Library;
import JvSi.ShanJi.com.English.bean.localLibrary;


/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;

    public static final int TYPE_PERSON = 2;

    private Context mcontext;

    private List<localLibrary> youngJoes2;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(Context context,List<MultiItemEntity> data,List<localLibrary> youngJoes2) {
        super(data);
        this.mcontext =context;
        addItemType(TYPE_LEVEL_0, R.layout.classify_ceshi);
        addItemType(TYPE_PERSON, R.layout.classify_ceshi);
        this.youngJoes2 = youngJoes2;

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
                holder.setVisible(R.id.textView6,false);
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
                boolean idlocal = false;
                holder.setText(R.id.text1, library.getClassify());
                holder.setTextColor(R.id.textView6,Color.YELLOW);
                Log.d("time01",">>>>>>>>>>>>>>>>>>222222      ");
                Log.d("time01",">>>>>>>>>>>>>>"+youngJoes2.size());
                if (youngJoes2.size() != 0){
                    for (localLibrary a : youngJoes2) {
                        if (library.getClassify().equals(a.getStringList())){
                            holder.setText(R.id.textView6, "正在学习");
                            holder.setTextColor(R.id.textView6, Color.RED );
                            idlocal = true;
                        }
                    }
                }
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
                boolean finalIdlocal = idlocal;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        if (finalIdlocal){
                            EventBus.getDefault().post(ClassifyMessageEvent.getInstance("classifytrue",library.getClassify()));
                        }else {
                            EventBus.getDefault().post(ClassifyMessageEvent.getInstance("classify",library.getClassify()));
                        }

                        //Intent intent15 = new Intent(mcontext, EnglishActivity.class);
                        //intent15.putExtra("library", library.getClassify());
                        //mcontext.startActivity(intent15);
                        if(Activity.class.isInstance(mcontext))
                        {
                            // 转化为activity，然后finish就行了
                            Activity activity = (Activity)mcontext;
                            activity.finish();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}