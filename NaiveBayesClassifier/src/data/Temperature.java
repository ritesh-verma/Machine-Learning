package data;

import java.util.ArrayList;
import java.util.List;

public class Temperature {
    private int HOT_YES;
    private int HOT_NO;
    private int MILD_YES;
    private int MILD_NO;
    private int COOL_YES;
    private int COOL_NO;
    private List<String> choices;

    public Temperature() {
        choices = new ArrayList<>();
    }

    public void generateChoices() {
        choices.add("hot");
        choices.add("mild");
        choices.add("cool");
    }

    public int getHOT_YES() {
        return HOT_YES;
    }

    public void setHOT_YES(int HOT_YES) {
        this.HOT_YES = HOT_YES;
    }

    public int getHOT_NO() {
        return HOT_NO;
    }

    public void setHOT_NO(int HOT_NO) {
        this.HOT_NO = HOT_NO;
    }

    public int getMILD_YES() {
        return MILD_YES;
    }

    public void setMILD_YES(int MILD_YES) {
        this.MILD_YES = MILD_YES;
    }

    public int getMILD_NO() {
        return MILD_NO;
    }

    public void setMILD_NO(int MILD_NO) {
        this.MILD_NO = MILD_NO;
    }

    public int getCOOL_YES() {
        return COOL_YES;
    }

    public void setCOOL_YES(int COOL_YES) {
        this.COOL_YES = COOL_YES;
    }

    public int getCOOL_NO() {
        return COOL_NO;
    }

    public void setCOOL_NO(int COOL_NO) {
        this.COOL_NO = COOL_NO;
    }

    public boolean compare(String tempInput) {
        return choices.contains(tempInput);
    }
}
