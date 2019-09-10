package JvSi.ShanJi.com.English.bean;

import java.util.List;
import java.util.Map;

/**
 * @author binshengzhu
 */
public class LibraryResult {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"大学转四专八":[{"classify":"ceshi","library":"大学转四专八","id":"9"}],"GREGMAT词汇":[{"classify":"ceshi","library":"GREGMAT词汇","id":"8"}],"初中英语":[{"classify":"黄教版7年级上","library":"初中英语","id":"1"},{"classify":"黄教版7年级下","library":"初中英语","id":"2"},{"classify":"黄教版8年级上","library":"初中英语","id":"3"},{"classify":"黄教版8年级下","library":"初中英语","id":"4"},{"classify":"黄教版9年级全","library":"初中英语","id":"5"},{"classify":"牛津沪教7年级上","library":"初中英语","id":"6"},{"classify":"牛津沪教7年级下","library":"初中英语","id":"7"}],"四六级词汇":[{"classify":"ceshi","library":"四六级词汇","id":"10"}]}
     * timestamp : 1566981904451
     */

    private boolean success;
    private String message;
    private int code;
    private Map<String, List<Library>> result;
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


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, List<Library>> getResult() {
        return result;
    }

    public void setResult(Map<String, List<Library>> result) {
        this.result = result;
    }

}
