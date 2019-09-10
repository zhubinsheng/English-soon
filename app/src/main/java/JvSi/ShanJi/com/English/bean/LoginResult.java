package JvSi.ShanJi.com.English.bean;

/**
 * Created by zhao
 */
public class LoginResult extends BaseInfo  {
    public Data data;

    public static class Data{
        public int Id;
        public boolean IsSetPassword;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "state='" + state + '\'' +
                ", Message='" + Message + '\'' +
                ", data=" + data +
                '}';
    }
}
