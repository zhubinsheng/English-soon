package gdyj.tydic.com.jinlingapp.bean;

import java.util.List;

import io.objectbox.annotation.Id;

/**
 * @author binshengzhu
 */
public class WordList {
    public WordList(long boxId, List<ClassifyBean> classifyBeanList) {
        this.boxId = boxId;
        this.classifyBeanList = classifyBeanList;
    }

    @Id
    long boxId;

    private List<ClassifyBean>  classifyBeanList;

    public List<ClassifyBean> getClassifyBeanList() {
        return classifyBeanList;
    }

    public void setClassifyBeanList(List<ClassifyBean> classifyBeanList) {
        this.classifyBeanList = classifyBeanList;
    }
}
