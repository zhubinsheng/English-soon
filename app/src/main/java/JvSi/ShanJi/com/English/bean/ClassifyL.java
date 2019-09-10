package JvSi.ShanJi.com.English.bean;

import java.util.List;

/**
 * @author binshengzhu
 */

public class ClassifyL {
    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : [{"word":null,"meaning":null,"classify":"人教版7年级下"},{"word":null,"meaning":null,"classify":"人教版7年级上"},{"word":null,"meaning":null,"classify":"黄教版9年级全"},{"word":null,"meaning":null,"classify":"牛津沪教7年级下"},{"word":null,"meaning":null,"classify":"牛津沪教7年级上"},{"word":null,"meaning":null,"classify":"牛津沪教8年级下"},{"word":null,"meaning":null,"classify":"牛津沪教8年级上"},{"word":null,"meaning":null,"classify":"gre4500"},{"word":null,"meaning":null,"classify":"人教版8年级上"},{"word":null,"meaning":null,"classify":"牛津沪教9年级上"},{"word":null,"meaning":null,"classify":"牛津沪教9年级下"},{"word":null,"meaning":null,"classify":"黄教版8年级上"},{"word":null,"meaning":null,"classify":"gmat2500"},{"word":null,"meaning":null,"classify":"黄教版7年级上"},{"word":null,"meaning":null,"classify":"黄教版8年级下"},{"word":null,"meaning":null,"classify":"黄教版7年级下"}]
     * timestamp : 1562598160210
     */

    private boolean success;
    private String message;
    private int code;
    private long timestamp;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


    public static class ResultBean {
        /**
         * word : null
         * meaning : null
         * classify : 人教版7年级下
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
