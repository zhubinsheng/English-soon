package gdyj.tydic.com.jinlingapp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.NameInDb;
import io.objectbox.annotation.Unique;

/**
 * @author binshengzhu
 */
@Entity
public class ClassifyBean implements MultiItemEntity {
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


        private String word;
        private String meaning;
    @Unique
    @Index
    @NameInDb("classify")
        private String classify;

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
}