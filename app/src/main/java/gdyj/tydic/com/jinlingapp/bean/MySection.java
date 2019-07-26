package gdyj.tydic.com.jinlingapp.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * @author binshengzhu
 */
public class MySection extends SectionEntity<ClassifyBean> {
    private List<ClassifyBean> bannerInfo;
    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(ClassifyBean classifyBean) {
        super(classifyBean);
    }

    public List<ClassifyBean> getBannerInfo() {
        return bannerInfo;
    }

    public void setBannerInfo(List<ClassifyBean> bannerInfo) {
        this.bannerInfo = bannerInfo;
    }
}
