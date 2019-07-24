package gdyj.tydic.com.jinlingapp.vo;

import gdyj.tydic.com.jinlingapp.MyApplication;
import io.objectbox.BoxStore;

/**
 * 数据库操作统一管理类
 */
public class DataManager {
    private static DataManager dataManager;

    public static synchronized DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public BoxStore boxStore;
    //public Box<UserEntity> userEntityBox;

    public void init(MyApplication demoApplication) {
        boxStore = demoApplication.getBoxStore();
        initUserEntityBox();
    }

    private void initUserEntityBox() {
        //对应操作对应表的类
        //userEntityBox = boxStore.boxFor(UserEntity.class);
    }
}