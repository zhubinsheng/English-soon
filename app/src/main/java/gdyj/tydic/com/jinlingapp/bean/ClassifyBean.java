package gdyj.tydic.com.jinlingapp.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.NameInDb;
import io.objectbox.annotation.Unique;

/**
 * @author binshengzhu
 */
@Entity
public class ClassifyBean {
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
        long boxId;

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
    }