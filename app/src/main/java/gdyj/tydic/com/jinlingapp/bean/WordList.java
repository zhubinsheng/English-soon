package gdyj.tydic.com.jinlingapp.bean;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * @author binshengzhu
 */
@Entity
public class WordList {

    @Id
    public long boxId;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String classify ;
    public ToMany<ClassifyBean> classifyBeanList;


}
