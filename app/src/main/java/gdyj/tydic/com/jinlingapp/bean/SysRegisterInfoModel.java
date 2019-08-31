package gdyj.tydic.com.jinlingapp.bean;

/**
 * 注册表单
 *
 * @Author scott
 * @since  2019-01-18
 */

public class SysRegisterInfoModel {

    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    private String avatar;

    private int sex;

}