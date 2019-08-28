package gdyj.tydic.com.jinlingapp.ui.Classify;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import gdyj.tydic.com.jinlingapp.bean.Library;

public class ClassifyLevel0Item extends AbstractExpandableItem<Library> implements MultiItemEntity{

    public String title;
    public String subTitle;

    public ClassifyLevel0Item(String s, String s1) {
        this.title=s;
        this.subTitle=s1;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}