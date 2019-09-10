package JvSi.ShanJi.com.English.bean;

import java.io.Serializable;

/**
 * Created by zhao
 */
public class BaseInfo implements Serializable {
    public static final String RET_SUCCESS = "success";
    public static final String RET_FAIL = "fail";

    public String state;
    public String Message;

    public boolean isSuccess(){
        return RET_SUCCESS.equals(state);
    }
}
