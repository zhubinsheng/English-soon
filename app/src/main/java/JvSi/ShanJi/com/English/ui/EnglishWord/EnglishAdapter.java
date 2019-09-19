package JvSi.ShanJi.com.English.ui.EnglishWord;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import JvSi.ShanJi.com.English.R;
import JvSi.ShanJi.com.English.baiduUtils.TTSUtils;
import JvSi.ShanJi.com.English.bean.ClassifyBean;


public class EnglishAdapter extends BaseItemDraggableAdapter <ClassifyBean, BaseViewHolder> {

    private int colorf = 0;
    private int isShow = 0;
    private static final int RED = 0xFFFF0000;
    private static final int GREEN = 0xFF00FF00;
    private static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;

    public EnglishAdapter(int layoutResId, @Nullable List<ClassifyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  ClassifyBean item) {
        ((TextView) helper.getView(R.id.text2)).setText(item.getMeaning());
        ((TextView) helper.getView(R.id.text1)).setText(item.getWord());

        /*((LinearLayout) helper.getView(R.id.shuangji)).setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick(View v) {
                Log.v(".334341czcfsd..", "Doublec");
                //EventBus.getDefault().post(ClassifyMessageEvent.getInstance("word",item.getWord()));
                if (item.getColorf() == 0){
                    ((TextView) helper.getView(R.id.text1)).setTextColor(GREEN);

                }else {
                    ((TextView) helper.getView(R.id.text1)).setTextColor(RED);

                }
            }
        });
        helper.itemView.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick(View v) {
                Log.v(".3343==41fsd..", ((TextView) helper.getView(R.id.text1)).getTextColors().toString());
                //EventBus.getDefault().post(ClassifyMessageEvent.getInstance("word",item.getWord()));
                if (item.getColorf() == 0){
                    ((TextView) helper.getView(R.id.text1)).setTextColor(RED);


                }else {
                    ((TextView) helper.getView(R.id.text1)).setTextColor(GREEN);

                }
                notifyDataSetChanged();
            }
        });*/

        if (item.getColorf() == 0){
            ((TextView) helper.getView(R.id.text1)).setTextColor(GREEN);
        }else if (item.getColorf() == 1){
            ((TextView) helper.getView(R.id.text1)).setTextColor(RED);
        }else if (item.getColorf() == 2){
            ((TextView) helper.getView(R.id.text1)).setTextColor(BLUE);
        }


        /*switch (helper.getLayoutPosition() % 3) {
            case 0:
                ((TextView) helper.getView(R.id.text2)).setTextColor(RED);
                break;
            case 1:
                ((TextView) helper.getView(R.id.text2)).setTextColor(BLUE);
                break;
            case 2:
                ((TextView) helper.getView(R.id.text2)).setTextColor(GREEN);
                break;
            default:
                break;
        }*/
        helper.addOnClickListener(R.id.text1);    //给图标添加 点击事件
        helper.addOnClickListener(R.id.shuangji);    //给图标添加 点击事件
        //单词默认显示绿色
        //((TextView) helper.getView(R.id.text2)).setTextColor(GREEN);
        //
        if (isShow == 0){
            ((TextView) helper.getView(R.id.text1)).setVisibility(View.VISIBLE);
            ((TextView) helper.getView(R.id.text2)).setVisibility(View.VISIBLE);
        }else if (isShow == 1){
            ((TextView) helper.getView(R.id.text1)).setVisibility(View.INVISIBLE);
            ((TextView) helper.getView(R.id.text2)).setVisibility(View.VISIBLE);
        }
        else if (isShow == 2){
            ((TextView) helper.getView(R.id.text2)).setVisibility(View.INVISIBLE);
            ((TextView) helper.getView(R.id.text1)).setVisibility(View.VISIBLE);
        }
    }


    //改变显示删除的imageview，通过定义变量isShow去接收变量isManager
    public void changetShowDelImage() {
        if (isShow == 0){
            this.isShow =1;
        }else if (isShow == 1){
            this.isShow =2;
        }else if (isShow == 2){
            this.isShow =0;
        }
        notifyDataSetChanged();
    }

    private void speak(String text) {
        TTSUtils.getInstance().speak(text);
    }
}
