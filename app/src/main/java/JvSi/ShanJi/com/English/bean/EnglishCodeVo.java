package JvSi.ShanJi.com.English.bean;

import java.util.List;

/**
 * @author Administrator
 */
public class EnglishCodeVo {
    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"records":[{"classify":"gmat2500","meaning":"n. 价值减低,减价,跌落 [计算机] 折旧","word":"depreciation"},{"classify":"gmat2500","meaning":"n. 暴民, 民众, 暴徒 v. 大举包围, 乱挤, 围攻","word":"mob"},{"classify":"gmat2500","meaning":"n. 长春花属的植物 n. 滨螺，玉黍螺","word":"periwinkle"},{"classify":"gmat2500","meaning":"n.侧面，半面； 外形，轮廓； [航]翼型； 人物简介 vt.描\u2026的轮廓； 给\u2026画侧面图； 为（某人）写传略； [机]铣出\u2026的轮廓","word":"profile"},{"classify":"gmat2500","meaning":"adj. 虚弱的, 无力的","word":"feeble"},{"classify":"gmat2500","meaning":"n. 修补匠, 焊锅 n.（爱尔兰语）吉卜赛 vi. 做焊锅匠, 笨拙的修补 vt. 修补，调整，改进","word":"tinker"},{"classify":"gmat2500","meaning":"vt. 铭记, 使弥香气，使不朽","word":"embalm"},{"classify":"gmat2500","meaning":"n. 子弹声，尖啸声, 活力 v. 发出尖啸声，尖刻批评","word":"zing"},{"classify":"gmat2500","meaning":"n. 愚蠢, 荒唐事 (复)follies: 轻松歌舞剧","word":"folly"},{"classify":"gmat2500","meaning":"vt. 使富足,使肥沃，添加元素","word":"enrich"}],"total":2247,"size":10,"current":1,"searchCount":true,"pages":225}
     * timestamp : 1564219778342
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

    public void setResultsetResult(ResultBean result) {
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
         * records : [{"classify":"gmat2500","meaning":"n. 价值减低,减价,跌落 [计算机] 折旧","word":"depreciation"},{"classify":"gmat2500","meaning":"n. 暴民, 民众, 暴徒 v. 大举包围, 乱挤, 围攻","word":"mob"},{"classify":"gmat2500","meaning":"n. 长春花属的植物 n. 滨螺，玉黍螺","word":"periwinkle"},{"classify":"gmat2500","meaning":"n.侧面，半面； 外形，轮廓； [航]翼型； 人物简介 vt.描\u2026的轮廓； 给\u2026画侧面图； 为（某人）写传略； [机]铣出\u2026的轮廓","word":"profile"},{"classify":"gmat2500","meaning":"adj. 虚弱的, 无力的","word":"feeble"},{"classify":"gmat2500","meaning":"n. 修补匠, 焊锅 n.（爱尔兰语）吉卜赛 vi. 做焊锅匠, 笨拙的修补 vt. 修补，调整，改进","word":"tinker"},{"classify":"gmat2500","meaning":"vt. 铭记, 使弥香气，使不朽","word":"embalm"},{"classify":"gmat2500","meaning":"n. 子弹声，尖啸声, 活力 v. 发出尖啸声，尖刻批评","word":"zing"},{"classify":"gmat2500","meaning":"n. 愚蠢, 荒唐事 (复)follies: 轻松歌舞剧","word":"folly"},{"classify":"gmat2500","meaning":"vt. 使富足,使肥沃，添加元素","word":"enrich"}]
         * total : 2247
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 225
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<ClassifyBean> records;

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

        public List<ClassifyBean> getRecords() {
            return records;
        }

        public void setRecords(List<ClassifyBean> records) {
            this.records = records;
        }


        public static class RecordsBean {
            /**
             * classify : gmat2500
             * meaning : n. 价值减低,减价,跌落 [计算机] 折旧
             * word : depreciation
             */

            private String classify;
            private String meaning;
            private String word;

            public String getClassify() {
                return classify;
            }

            public void setClassify(String classify) {
                this.classify = classify;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }
    }
}
