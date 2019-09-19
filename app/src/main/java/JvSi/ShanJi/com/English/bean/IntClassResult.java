package JvSi.ShanJi.com.English.bean;

public class IntClassResult {

    /**
     * success : true
     * message : 添加成功！
     * code : 200
     * result : null
     * timestamp : 1568896447957
     */

    private boolean success;
    private String message;
    private int code;
    private Object result;
    private long timestamp;

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
