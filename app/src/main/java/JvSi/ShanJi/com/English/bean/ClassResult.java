package JvSi.ShanJi.com.English.bean;

import java.util.List;

public class ClassResult {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : [{"classs":"小学生一班","userid":"e4fd3d71e117b3f5158add55b95d93d9","uuid":"359816f0db4ea6132fe922b3b0ba2b47","allcount":null},{"classs":"小学生一班","userid":"e4fd3d71e117b3f5158add55b95d93d9","uuid":"800a0f6f1d15c69771a55e68a115b4df","allcount":null},{"classs":"小学生一班","userid":"e4fd3d71e117b3f5158add55b95d93d9","uuid":"103d14fef90c0a63c1fb7190776719d1","allcount":null},{"classs":"小学生二班","userid":"e4fd3d71e117b3f5158add55b95d93d9","uuid":"ba4c02e5928a97468e04480cdf4f23f6","allcount":null},{"classs":"小学生二班","userid":"55b95d93d9","uuid":"c45085ddf6bb0208dc36cc6687190e4a","allcount":"636"}]
     * timestamp : 1568883714685
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
         * classs : 小学生一班
         * userid : e4fd3d71e117b3f5158add55b95d93d9
         * uuid : 359816f0db4ea6132fe922b3b0ba2b47
         * allcount : null
         */

        private String classs;
        private String userid;
        private String uuid;
        private Object allcount;

        public String getClasss() {
            return classs;
        }

        public void setClasss(String classs) {
            this.classs = classs;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Object getAllcount() {
            return allcount;
        }

        public void setAllcount(Object allcount) {
            this.allcount = allcount;
        }
    }
}
