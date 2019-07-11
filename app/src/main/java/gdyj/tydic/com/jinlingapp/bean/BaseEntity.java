package gdyj.tydic.com.jinlingapp.bean;

import java.util.List;

public class BaseEntity<T> {
    private List <T> retObj;
    private String retCode;
    private String retMsg;

    public List<T> gettList() {
        return retObj;
    }

    public void settList(List<T> tList) {
        this.retObj = tList;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
