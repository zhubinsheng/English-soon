package JvSi.ShanJi.com.English.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * @author binshengzhu
 */
@Entity
public class WordList implements Serializable  , MultiItemEntity {

    @Id
    public long boxId;

    private String library;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String classify ;

    public ToMany<ClassifyBean> classifyBeanList;

    public ToMany<ClassifyBean> getClassifyBeanList() {
        return classifyBeanList;
    }

    public void setClassifyBeanList(ToMany<ClassifyBean> classifyBeanList) {
        this.classifyBeanList = classifyBeanList;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
