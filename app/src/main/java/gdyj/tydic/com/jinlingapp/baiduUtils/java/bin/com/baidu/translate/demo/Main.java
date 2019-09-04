package gdyj.tydic.com.jinlingapp.baiduUtils.java.bin.com.baidu.translate.demo;

import gdyj.tydic.com.jinlingapp.baiduUtils.java.bin.com.baidu.translate.demo.com.baidu.translate.demo.TransApi;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20190904000331854";
    private static final String SECURITY_KEY = "X4XPhNmXlcAdJW4yFoTI";

    public static String main(String query) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        return (api.getTransResult(query, "auto", "en"));
    }

}
