package JvSi.ShanJi.com.English.ui.Classify;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import JvSi.ShanJi.com.English.bean.WordList;

public class ClassifyLevel1Item extends AbstractExpandableItem<WordList> implements MultiItemEntity{

    public String title;
    public String subTitle;

    public ClassifyLevel1Item(String s, String s1) {
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