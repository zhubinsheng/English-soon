package gdyj.tydic.com.jinlingapp.bean;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * @author binshengzhu
 */

public class WordList {

    @Id
    public long boxId;



    @Backlink
    public ToMany<ClassifyBean> classifyBeanList;


}
