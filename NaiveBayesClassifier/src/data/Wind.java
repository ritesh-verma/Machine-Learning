package data;

import java.util.ArrayList;
import java.util.List;

public class Wind {
    private int STRONG_YES;
    private int STRONG_NO;
    private int WEAK_YES;
    private int WEAK_NO;
    private List<String> choices;

    public Wind() {
        choices = new ArrayList<>();
    }

    public void generateChoices() {
        choices.add("strong");
        choices.add("weak");
    }

    public int getSTRONG_YES() {
        return STRONG_YES;
    }

    public void setSTRONG_YES(int STRONG_YES) {
        this.STRONG_YES = STRONG_YES;
    }

    public int getSTRONG_NO() {
        return STRONG_NO;
    }

    public void setSTRONG_NO(int STRONG_NO) {
        this.STRONG_NO = STRONG_NO;
    }

    public int getWEAK_YES() {
        return WEAK_YES;
    }

    public void setWEAK_YES(int WEAK_YES) {
        this.WEAK_YES = WEAK_YES;
    }

    public int getWEAK_NO() {
        return WEAK_NO;
    }

    public void setWEAK_NO(int WEAK_NO) {
        this.WEAK_NO = WEAK_NO;
    }

    public boolean compare(String windInput) {
        return choices.contains(windInput);
    }
}
