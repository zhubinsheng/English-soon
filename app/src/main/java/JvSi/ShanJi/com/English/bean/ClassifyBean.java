package JvSi.ShanJi.com.English.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @author binshengzhu
 */
@Entity
public class ClassifyBean implements MultiItemEntity , Serializable {
    public ClassifyBean(String word, String meaning, String classify) {
        this.word = word;
        this.meaning = meaning;
        this.classify = classify;
    }

    /**
         * word : null
         * meaning : null
         * classify : 人教版7年级下
         */

        @Id
        public long id;
        private String word;
        private String meaning;
        private String classify;
        private int colorf;
        // 0 代表　绿色    1  代表 红色   2 代表 蓝色

        private int level = 1;
        //等级 1-7

        private int date;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

    @Override
    public int getItemType() {
        return 2;
    }

    public int getColorf() {
        return colorf;
    }
    public int getColorf2() {
            if (colorf == 0){
                return 2;
            }else if (colorf == 1){
                return 0;
            }else if (colorf == 2){
                return 1;
            }
        return 3;
            //根据返回值进行排序
    }

    public void setColorf(int colorf) {
        this.colorf = colorf;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}