package JvSi.ShanJi.com.English.bean;

import java.util.List;

/**
 * @author Administrator
 */
public class EnglishCodeVo {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : [{"word":"depreciation","meaning":"n. 价值减低,减价,跌落 [计算机] 折旧","classify":"GMAT2500"},{"word":"mob","meaning":"n. 暴民, 民众, 暴徒 v. 大举包围, 乱挤, 围攻","classify":"GMAT2500"}]
     * timestamp : 1568252963660
     */

    private boolean success;
    private String message;
    private int code;
    private long timestamp;
    private List<ClassifyBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<ClassifyBean> getResult() {
        return result;
    }

    public void setResult(List<ClassifyBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * word : depreciation
         * meaning : n. 价值减低,减价,跌落 [计算机] 折旧
         * classify : GMAT2500
         */

        private String word;
        private String meaning;
        private String classify;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }
    }
}
