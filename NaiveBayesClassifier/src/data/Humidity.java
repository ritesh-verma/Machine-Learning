package data;

import java.util.ArrayList;
import java.util.List;

public class Humidity {
    private int HIGH_YES;
    private int HIGH_NO;
    private int NORMAL_YES;
    private int NORMAL_NO;
    private List<String> choices;

    public Humidity() {
        choices = new ArrayList<>();
    }

    public void generateChoices() {
        choices.add("high");
        choices.add("normal");
    }

    public int getHIGH_YES() {
        return HIGH_YES;
    }

    public void setHIGH_YES(int HIGH_YES) {
        this.HIGH_YES = HIGH_YES;
    }

    public int getHIGH_NO() {
        return HIGH_NO;
    }

    public void setHIGH_NO(int HIGH_NO) {
        this.HIGH_NO = HIGH_NO;
    }

    public int getNORMAL_YES() {
        return NORMAL_YES;
    }

    public void setNORMAL_YES(int NORMAL_YES) {
        this.NORMAL_YES = NORMAL_YES;
    }

    public int getNORMAL_NO() {
        return NORMAL_NO;
    }

    public void setNORMAL_NO(int NORMAL_NO) {
        this.NORMAL_NO = NORMAL_NO;
    }

    public boolean compare(String humidityInput) {
        return choices.contains(humidityInput);
    }
}
