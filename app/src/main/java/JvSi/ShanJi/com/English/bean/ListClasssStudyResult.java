package JvSi.ShanJi.com.English.bean;

import java.util.List;

public class ListClasssStudyResult {
    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"records":[{"classs":"小学生二班","allcount":null,"userid":"e4fd3d71e117b3f5158add55b95d93d9","uuid":"ba4c02e5928a97468e04480cdf4f23f6"},{"classs":"小学生二班","allcount":"636","userid":"55b95d93d9","uuid":"c45085ddf6bb0208dc36cc6687190e4a"},{"classs":"小学生二班","allcount":"5","userid":"cdf87df0d96a65ea755b55db41b156d3","uuid":"e8dd52ada33abd285680cb2231a97036"},{"classs":"小学生二班","allcount":"0","userid":"e8dd52ada33abd285680cb2231a97036","uuid":"ad8de487aa8bd15de883c5ff6ac70c34"}],"total":4,"size":10,"current":1,"searchCount":true,"pages":1}
     * timestamp : 1569307921188
     */

    private boolean success;
    private String message;
    private int code;
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
         * records : [{"classs":"小学生二班","allcount":null,"userid":"e4fd3d71e117b3f5158add55b95d93d9","uuid":"ba4c02e5928a97468e04480cdf4f23f6"},{"classs":"小学生二班","allcount":"636","userid":"55b95d93d9","uuid":"c45085ddf6bb0208dc36cc6687190e4a"},{"classs":"小学生二班","allcount":"5","userid":"cdf87df0d96a65ea755b55db41b156d3","uuid":"e8dd52ada33abd285680cb2231a97036"},{"classs":"小学生二班","allcount":"0","userid":"e8dd52ada33abd285680cb2231a97036","uuid":"ad8de487aa8bd15de883c5ff6ac70c34"}]
         * total : 4
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * classs : 小学生二班
             * allcount : null
             * userid : e4fd3d71e117b3f5158add55b95d93d9
             * uuid : ba4c02e5928a97468e04480cdf4f23f6
             */

            private String classs;
            private Object allcount;
            private String userid;
            private String uuid;

            public String getClasss() {
                return classs;
            }

            public void setClasss(String classs) {
                this.classs = classs;
            }

            public Object getAllcount() {
                return allcount;
            }

            public void setAllcount(Object allcount) {
                this.allcount = allcount;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }
        }
    }
}
