package classifier;

import data.Humidity;
import data.Outlook;
import data.Temperature;
import data.Wind;
import util.DataCreator;

import java.util.HashMap;
import java.util.Map;

public class NaiveBayes {
    private Outlook outlook;
    private Humidity humidity;
    private Temperature temperature;
    private Wind wind;
    private DataCreator dataCreator;
    private Map<String, Float> probability;

    public NaiveBayes(Outlook outlookIn, Humidity humidityIn, Temperature temperatureIn, Wind windIn, DataCreator dataCreatorIn) {
        outlook = outlookIn;
        humidity = humidityIn;
        temperature = temperatureIn;
        wind = windIn;
        dataCreator = dataCreatorIn;
        probability = new HashMap<>();
    }

    public void calculateProbability() {
        int YES = dataCreator.getYES_COUNT();
        int NO = dataCreator.getNO_COUNT();
        probability.put("YES", calculateProbabilityHelper(YES, (YES + NO)));
        probability.put("NO", calculateProbabilityHelper(NO, (YES + NO)));

        probability.put("SUNNY_YES", calculateProbabilityHelper(outlook.getSUNNY_YES(), YES));
        probability.put("SUNNY_NO", calculateProbabilityHelper(outlook.getSUNNY_NO(), NO));

        probability.put("OVERCAST_YES", calculateProbabilityHelper(outlook.getOVERCAST_YES(), YES));
        probability.put("OVERCAST_NO", calculateProbabilityHelper(outlook.getOVERCAST_NO(), NO));

        probability.put("RAIN_YES", calculateProbabilityHelper(outlook.getRAIN_YES(), YES));
        probability.put("RAIN_NO", calculateProbabilityHelper(outlook.getRAIN_NO(), NO));

        probability.put("HOT_YES", calculateProbabilityHelper(temperature.getHOT_YES(), YES));
        probability.put("HOT_NO", calculateProbabilityHelper(temperature.getHOT_NO(), NO));

        probability.put("MILD_YES", calculateProbabilityHelper(temperature.getMILD_YES(), YES));
        probability.put("MILD_NO", calculateProbabilityHelper(temperature.getMILD_NO(), NO));

        probability.put("COOL_YES", calculateProbabilityHelper(temperature.getCOOL_YES(), YES));
        probability.put("COOL_NO", calculateProbabilityHelper(temperature.getCOOL_NO(), NO));

        probability.put("HIGH_YES", calculateProbabilityHelper(humidity.getHIGH_YES(), YES));
        probability.put("HIGH_NO", calculateProbabilityHelper(humidity.getHIGH_NO(), NO));

        probability.put("NORMAL_YES", calculateProbabilityHelper(humidity.getNORMAL_YES(), YES));
        probability.put("NORMAL_NO", calculateProbabilityHelper(humidity.getNORMAL_NO(), NO));

        probability.put("STRONG_YES", calculateProbabilityHelper(wind.getSTRONG_YES(), YES));
        probability.put("STRONG_NO", calculateProbabilityHelper(wind.getWEAK_NO(), NO));

        probability.put("WEAK_YES", calculateProbabilityHelper(wind.getWEAK_YES(), YES));
        probability.put("WEAK_NO", calculateProbabilityHelper(wind.getWEAK_NO(), NO));

    }

    private float calculateProbabilityHelper(int value, int total) {
        return (float) value / (float) total;
    }

    public String predict(String outlookInput, String tempInput, String humidityInput, String windInput) {
        if (!outlook.compare(outlookInput.toLowerCase()) || !temperature.compare(tempInput.toLowerCase()) ||
                !humidity.compare(humidityInput.toLowerCase()) || !wind.compare(windInput.toLowerCase())) {
            System.out.println("Please enter correct choices.");
            System.exit(1);
        }

        float outlookYes = (float) 0.0, outlookNo = (float) 0.0;
        float tempYes = (float) 0.0, tempNo = (float) 0.0;
        float humidityYes = (float) 0.0, humidityNo = (float) 0.0;
        float windYes = (float) 0.0, windNo = (float) 0.0;
        float playYes, playNo;

        switch (outlookInput.toLowerCase()) {
            case "sunny":
                outlookYes = probability.get("SUNNY_YES");
                outlookNo = probability.get("SUNNY_NO");
                break;
            case "rain":
                outlookYes = probability.get("RAIN_YES");
                outlookNo = probability.get("RAIN_NO");
                break;
            case "overcast":
                outlookYes = probability.get("OVERCAST_YES");
                outlookNo = probability.get("OVERCAST_NO");
                break;
        }

        switch (tempInput.toLowerCase()) {
            case "hot":
                tempYes = probability.get("HOT_YES");
                tempNo = probability.get("HOT_NO");
                break;
            case "mild":
                tempYes = probability.get("MILD_YES");
                tempNo = probability.get("MILD_NO");
                break;
            case "cool":
                tempYes = probability.get("COOL_YES");
                tempNo = probability.get("COOL_NO");
                break;
        }

        switch (humidityInput.toLowerCase()) {
            case "high":
                humidityYes = probability.get("HIGH_YES");
                humidityNo = probability.get("HIGH_NO");
                break;
            case "normal":
                humidityYes = probability.get("NORMAL_YES");
                humidityNo = probability.get("NORMAL_NO");
                break;
        }

        switch (windInput.toLowerCase()) {
            case "strong":
                windYes = probability.get("STRONG_YES");
                windNo = probability.get("STRONG_NO");
                break;
            case "weak":
                windYes = probability.get("WEAK_YES");
                windNo = probability.get("WEAK_NO");
                break;
        }

        playYes = outlookYes * tempYes * humidityYes * windYes;
        playNo = outlookNo * tempNo * humidityNo * windNo;

        return (playYes > playNo) ? "Yes" : "No";
    }

    public void display() {
        System.out.println();
        for (Map.Entry<String, Float> entry : probability.entrySet()){
            System.out.println("Probability for " + entry.getKey() + " : " + entry.getValue());
        }
    }
}
