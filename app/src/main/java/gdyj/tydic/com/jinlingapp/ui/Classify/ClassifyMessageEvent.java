package gdyj.tydic.com.jinlingapp.ui.Classify;

public class ClassifyMessageEvent {

    private String recode;
    private String classify;

    public String getRecode() {
        return recode;
    }

    public void setRecode(String recode) {
        this.recode = recode;
    }


    public static ClassifyMessageEvent getInstance(String message , String classify) {
        return new ClassifyMessageEvent(message,classify);
    }


    public ClassifyMessageEvent(String recode, String classify) {
        this.recode = recode;
        this.classify = classify;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}