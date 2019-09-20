package JvSi.ShanJi.com.English.ui.MyFunction;

import java.util.List;

public class AESResult {

    /**
     * tags : [{"value":"聚光灯","confidence":26},{"value":"杯子","confidence":20},{"value":"放大镜","confidence":17},{"value":"汽车轮","confidence":14},{"value":"投影机","confidence":13}]
     * errno : 0
     * request_id : f5b87a69-55f5-40c1-b7a3-ffc77fbce00c
     */

    private int errno;
    private String request_id;
    private List<TagsBean> tags;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * value : 聚光灯
         * confidence : 26
         */

        private String value;
        private int confidence;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }
    }
}
