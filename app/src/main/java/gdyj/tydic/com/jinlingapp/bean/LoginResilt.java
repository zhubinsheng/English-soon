package gdyj.tydic.com.jinlingapp.bean;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

/**
 * @author binshengzhu
 */
@Entity
public class LoginResilt {

    @Id
    long boxId;

    /**
     * success : true
     * message : 登录成功
     * code : 200
     * result : {"userInfo":{"id":"e4fd3d71e117b3f5158add55b95d93d9","username":"10086","realname":null,"password":"9575827b7c4d2d5b","salt":"EE0py6zc","avatar":null,"birthday":null,"sex":null,"email":null,"phone":null,"classs":null,"status":1,"delFlag":"0","createBy":"xingzai","createTime":"2019-08-29 13:50:18","updateBy":null,"updateTime":null,"class_id":null},"upToken":"mG1JR-ivqabWIl6-t-69ZpGz-Cg57hFttKijlyBG:ZlIR2x5RhLLODE1R75V3r9cG1go=:eyJzY29wZSI6ImVuZ2xpc2giLCJkZWFkbGluZSI6MTU2NzIyNDg1Mn0=","learning":[],"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjcyMjMwNTIsInVzZXJuYW1lIjoiMTAwODYifQ.ZnRMLxKK3k37HvYtJDMURZ-6fNu6_R_Fb7zVQac4JIs"}
     * timestamp : 1567221252326
     */

    private boolean success;
    private String message;
    private int code;
    @Transient
    private ResultBean result;
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        /**
         * userInfo : {"id":"e4fd3d71e117b3f5158add55b95d93d9","username":"10086","realname":null,"password":"9575827b7c4d2d5b","salt":"EE0py6zc","avatar":null,"birthday":null,"sex":null,"email":null,"phone":null,"classs":null,"status":1,"delFlag":"0","createBy":"xingzai","createTime":"2019-08-29 13:50:18","updateBy":null,"updateTime":null,"class_id":null}
         * upToken : mG1JR-ivqabWIl6-t-69ZpGz-Cg57hFttKijlyBG:ZlIR2x5RhLLODE1R75V3r9cG1go=:eyJzY29wZSI6ImVuZ2xpc2giLCJkZWFkbGluZSI6MTU2NzIyNDg1Mn0=
         * learning : []
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjcyMjMwNTIsInVzZXJuYW1lIjoiMTAwODYifQ.ZnRMLxKK3k37HvYtJDMURZ-6fNu6_R_Fb7zVQac4JIs
         */

        private UserInfoBean userInfo;
        private String upToken;
        private String token;
        private List<LearningSit> learning;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getUpToken() {
            return upToken;
        }

        public void setUpToken(String upToken) {
            this.upToken = upToken;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<LearningSit> getLearning() {
            return learning;
        }

        public void setLearning(List<LearningSit> learning) {
            this.learning = learning;
        }

        public static class UserInfoBean {
            /**
             * id : e4fd3d71e117b3f5158add55b95d93d9
             * username : 10086
             * realname : null
             * password : 9575827b7c4d2d5b
             * salt : EE0py6zc
             * avatar : null
             * birthday : null
             * sex : null
             * email : null
             * phone : null
             * classs : null
             * status : 1
             * delFlag : 0
             * createBy : xingzai
             * createTime : 2019-08-29 13:50:18
             * updateBy : null
             * updateTime : null
             * class_id : null
             */

            private String id;
            private String username;
            private Object realname;
            private String password;
            private String salt;
            private Object avatar;
            private Object birthday;
            private Object sex;
            private Object email;
            private Object phone;
            private Object classs;
            private int status;
            private String delFlag;
            private String createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object class_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getRealname() {
                return realname;
            }

            public void setRealname(Object realname) {
                this.realname = realname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getClasss() {
                return classs;
            }

            public void setClasss(Object classs) {
                this.classs = classs;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getClass_id() {
                return class_id;
            }

            public void setClass_id(Object class_id) {
                this.class_id = class_id;
            }
        }
    }
}
