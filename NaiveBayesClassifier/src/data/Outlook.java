package data;

import java.util.ArrayList;
import java.util.List;

public class Outlook {
    private int SUNNY_YES;
    private int SUNNY_NO;
    private int OVERCAST_YES;
    private int OVERCAST_NO;
    private int RAIN_YES;
    private int RAIN_NO;
    private List<String> choices;

    public Outlook() {
        SUNNY_YES = 0;
        SUNNY_NO = 0;
        OVERCAST_YES = 0;
        OVERCAST_NO = 0;
        RAIN_YES = 0;
        RAIN_NO = 0;
        choices = new ArrayList<>();
    }

    public void generateChoices() {
        choices.add("sunny");
        choices.add("overcast");
        choices.add("rain");
    }

    public int getSUNNY_YES() {
        return SUNNY_YES;
    }

    public void setSUNNY_YES(int SUNNY_YES) {
        this.SUNNY_YES = SUNNY_YES;
    }

    public int getSUNNY_NO() {
        return SUNNY_NO;
    }

    public void setSUNNY_NO(int SUNNY_NO) {
        this.SUNNY_NO = SUNNY_NO;
    }

    public int getOVERCAST_YES() {
        return OVERCAST_YES;
    }

    public void setOVERCAST_YES(int OVERCAST_YES) {
        this.OVERCAST_YES = OVERCAST_YES;
    }

    public int getOVERCAST_NO() {
        return OVERCAST_NO;
    }

    public void setOVERCAST_NO(int OVERCAST_NO) {
        this.OVERCAST_NO = OVERCAST_NO;
    }

    public int getRAIN_YES() {
        return RAIN_YES;
    }

    public void setRAIN_YES(int RAIN_YES) {
        this.RAIN_YES = RAIN_YES;
    }

    public int getRAIN_NO() {
        return RAIN_NO;
    }

    public void setRAIN_NO(int RAIN_NO) {
        this.RAIN_NO = RAIN_NO;
    }

    public boolean compare(String outlookInput) {
        return choices.contains(outlookInput);
    }
}
