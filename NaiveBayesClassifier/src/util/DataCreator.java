package util;

import data.Humidity;
import data.Outlook;
import data.Temperature;
import data.Wind;

public class DataCreator {
    private FileProcessor fp;
    private Outlook outlook;
    private Humidity humidity;
    private Temperature temperature;
    private Wind wind;
    private int YES_COUNT;
    private int NO_COUNT;

    public int getYES_COUNT() {
        return YES_COUNT;
    }

    private void setYES_COUNT(int YES_COUNT) {
        this.YES_COUNT = YES_COUNT;
    }

    public int getNO_COUNT() {
        return NO_COUNT;
    }

    private void setNO_COUNT(int NO_COUNT) {
        this.NO_COUNT = NO_COUNT;
    }

    public DataCreator(FileProcessor fpIn, Outlook outlookIn, Humidity humidityIn, Temperature temperatureIn, Wind windIn) {
        fp = fpIn;
        outlook = outlookIn;
        humidity = humidityIn;
        temperature = temperatureIn;
        wind = windIn;
        YES_COUNT = 0;
        NO_COUNT = 0;
    }

    public void storeData() {
        String line;
        String[] tokens;

        outlook.generateChoices();
        humidity.generateChoices();
        temperature.generateChoices();
        wind.generateChoices();

        while ((line = fp.readLineFromFile()) != null) {
            tokens = line.split((" "));

            if (tokens[4].equals("Yes")) {
                setYES_COUNT(getYES_COUNT() + 1);
                switch (tokens[0]) {
                    case "Sunny":
                        outlook.setSUNNY_YES(outlook.getSUNNY_YES() + 1);
                        break;
                    case "Overcast":
                        outlook.setOVERCAST_YES(outlook.getOVERCAST_YES() + 1);
                        break;
                    case "Rain":
                        outlook.setRAIN_YES(outlook.getRAIN_YES() + 1);
                        break;
                }
                switch (tokens[1]) {
                    case "Hot":
                        temperature.setHOT_YES(temperature.getHOT_YES() + 1);
                        break;
                    case "Mild":
                        temperature.setMILD_YES(temperature.getMILD_YES() + 1);
                        break;
                    case "Cool":
                        temperature.setCOOL_YES(temperature.getCOOL_YES() + 1);
                        break;
                }
                switch (tokens[2]) {
                    case "High":
                        humidity.setHIGH_YES(humidity.getHIGH_YES() + 1);
                        break;
                    case "Normal":
                        humidity.setNORMAL_YES(humidity.getNORMAL_YES() + 1);
                        break;
                }
                switch (tokens[3]) {
                    case "Strong":
                        wind.setSTRONG_YES(wind.getSTRONG_YES() + 1);
                        break;
                    case "Weak":
                        wind.setWEAK_YES(wind.getWEAK_YES() + 1);
                        break;
                }
            } else {
                setNO_COUNT(getNO_COUNT() + 1);
                switch (tokens[0]) {
                    case "Sunny":
                        outlook.setSUNNY_NO(outlook.getSUNNY_NO() + 1);
                        break;
                    case "Overcast":
                        outlook.setOVERCAST_NO(outlook.getOVERCAST_NO() + 1);
                        break;
                    case "Rain":
                        outlook.setRAIN_NO(outlook.getRAIN_NO() + 1);
                        break;
                }
                switch (tokens[1]) {
                    case "Hot":
                        temperature.setHOT_NO(temperature.getHOT_NO() + 1);
                        break;
                    case "Mild":
                        temperature.setMILD_NO(temperature.getMILD_NO() + 1);
                        break;
                    case "Cool":
                        temperature.setCOOL_NO(temperature.getCOOL_NO() + 1);
                        break;
                }
                switch (tokens[2]) {
                    case "High":
                        humidity.setHIGH_NO(humidity.getHIGH_NO() + 1);
                        break;
                    case "Normal":
                        humidity.setNORMAL_NO(humidity.getNORMAL_NO() + 1);
                        break;
                }
                switch (tokens[3]) {
                    case "Strong":
                        wind.setSTRONG_NO(wind.getSTRONG_NO() + 1);
                        break;
                    case "Weak":
                        wind.setWEAK_NO(wind.getWEAK_NO() + 1);
                        break;
                }
            }
        }
    }
}
