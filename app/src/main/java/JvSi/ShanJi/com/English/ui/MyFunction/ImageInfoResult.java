package JvSi.ShanJi.com.English.ui.MyFunction;

public class ImageInfoResult {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"tags":[{"value":"美食","confidence":25.0},{"value":"盘子","confidence":23.0},{"value":"餐厅","confidence":20.0},{"value":"碗","confidence":18.0},{"value":"清炖肉汤","confidence":14.0}],"errno":0,"request_id":"e268d7b4-3995-4d8b-8d03-d87dbc8dfa28"}
     * timestamp : 1568952450616
     */

    private boolean success;
    private String message;
    private int code;
    private String result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
