package gdyj.tydic.com.jinlingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gdyj.tydic.com.jinlingapp.vo.Classify;

public class ClassifyAdapter extends BaseAdapter implements View.OnClickListener {
 
  private static final String TAG = ClassifyAdapter.class.getSimpleName();
  private List<Classify> data;
  private LayoutInflater inflater;
  private OnClickItemListener listener;//持有接口
 
  public void setListener(OnClickItemListener listener){
      this.listener=listener;
  }
 
  public ClassifyAdapter(Context context, List<Classify>data) {
    inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (data!=null) {
      this.data=data;
    }
    else {
      this.data=new ArrayList<>();
    }
  }
 
  public void updateRes(List<Classify> data){
    if (data!=null) {
      this.data.clear();
      this.data.addAll(data);
      notifyDataSetChanged();
    }
  }
 
  @Override
  public int getCount() {
    int count=0;
    if (data!=null) {
      count=(data.size()-1)/2;
    }
    return count;
  }
 
  @Override
  public Classify getItem(int position) {
    return data.get(position);
  }
 
  @Override
  public long getItemId(int position) {
    return position;
  }
 
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder=null;
    if (convertView==null) {
      convertView=inflater.inflate(R.layout.classify_item,parent,false);
      holder=new ViewHolder();
      holder.itemIamge01= (ImageView) convertView.findViewById(R.id.teach_classify_item_iamge01);
      holder.itemImage02= (ImageView) convertView.findViewById(R.id.teach_classify_item_iamge02);
      holder.itemText01= (TextView) convertView.findViewById(R.id.teach_classify_item_text01);
      holder.itemText02= (TextView) convertView.findViewById(R.id.teach_classify_item_text02);
      holder.topDivider=convertView.findViewById(R.id.teach_classify_item_divider);
      holder.leftItem=convertView.findViewById(R.id.teach_classify_left);
      holder.rightItem=convertView.findViewById(R.id.teach_classify_right);
      convertView.setTag(holder);
    }
    else {
      holder= (ViewHolder) convertView.getTag();
    }
    //根据条件判断是否显示分割线
    if (position%3==0&&position!=0) {
      holder.topDivider.setVisibility(View.VISIBLE);
    }else {
      holder.topDivider.setVisibility(View.GONE);
    }
    //加载数据
    holder.itemText01.setText(data.get(position*2+1).getTitle());
    holder.itemText02.setText(data.get(position*2+2).getTitle());
    //设置监听
    holder.leftItem.setOnClickListener(this);
    holder.rightItem.setOnClickListener(this);
    //设置标记
    holder.leftItem.setTag(position*2+1);
    holder.rightItem.setTag(position*2+2);
    //加载图片
   /* ImageLoader.display(holder.itemIamge01,data.get(position*2+1).getCoverPath());
    ImageLoader.display(holder.itemImage02,data.get(position*2+2).getCoverPath());*/
    return convertView;
  }
 
  @Override
  public void onClick(View v) {
    Integer position = (Integer) v.getTag();
    Log.e(TAG, "onClick: "+position );
    if (listener!=null) {
      listener.onOnclickItem(position);
    }
 
  }
 
  private static class ViewHolder{
    //左边的图片
    ImageView itemIamge01;
    //右边的图片
    ImageView itemImage02;
    //右边
    TextView itemText01;
    TextView itemText02;
 
    //分割线
    View topDivider;
    //左右布局
    View leftItem,rightItem;
  }
 
  public interface OnClickItemListener{
    void onOnclickItem(int position);
  }
}