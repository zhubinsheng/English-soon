package gdyj.tydic.com.jinlingapp.bean;

public class LoginResilt {
    /**
     * success : true
     * message : 登录成功
     * code : 200
     * result : {"userInfo":{"id":"8c94bea8bd03cc2489bf8309d3740042","username":"string","realname":null,"password":"984262da947acd0c","salt":"U9H7kOhP","avatar":null,"birthday":null,"sex":null,"email":null,"phone":null,"status":1,"delFlag":"0","createBy":"xingzai","createTime":"2019-07-07 09:43:09","updateBy":null,"updateTime":null},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjI1OTU0NDEsInVzZXJuYW1lIjoic3RyaW5nIn0.mXX_5zADzryQ4WjWyY530hcf1eSjNppV5zO89TNqkZQ"}
     * timestamp : 1562593641850
     */

    private boolean success;
    private String message;
    private Integer code;
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
         * userInfo : {"id":"8c94bea8bd03cc2489bf8309d3740042","username":"string","realname":null,"password":"984262da947acd0c","salt":"U9H7kOhP","avatar":null,"birthday":null,"sex":null,"email":null,"phone":null,"status":1,"delFlag":"0","createBy":"xingzai","createTime":"2019-07-07 09:43:09","updateBy":null,"updateTime":null}
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjI1OTU0NDEsInVzZXJuYW1lIjoic3RyaW5nIn0.mXX_5zADzryQ4WjWyY530hcf1eSjNppV5zO89TNqkZQ
         */

        private UserInfoBean userInfo;
        private String token;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserInfoBean {
            /**
             * id : 8c94bea8bd03cc2489bf8309d3740042
             * username : string
             * realname : null
             * password : 984262da947acd0c
             * salt : U9H7kOhP
             * avatar : null
             * birthday : null
             * sex : null
             * email : null
             * phone : null
             * status : 1
             * delFlag : 0
             * createBy : xingzai
             * createTime : 2019-07-07 09:43:09
             * updateBy : null
             * updateTime : null
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
            private int status;
            private String delFlag;
            private String createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;

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
        }
    }
}
