package driver;

import classifier.NaiveBayes;
import data.Humidity;
import data.Outlook;
import data.Temperature;
import data.Wind;
import util.DataCreator;
import util.FileProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide input file.");
            System.exit(1);
        }

        Outlook outlook = new Outlook();
        Humidity humidity = new Humidity();
        Temperature temperature = new Temperature();
        Wind wind = new Wind();

        DataCreator dataCreator = null;

        try {
            FileProcessor fp = new FileProcessor(args[0]);
            dataCreator = new DataCreator(fp, outlook, humidity, temperature, wind);
            dataCreator.storeData();
            fp.closeFile();
        } catch (IOException e) {
            System.out.println("Error in file IO operation." + e.getMessage());
            e.printStackTrace();
        }

        NaiveBayes naiveBayes = new NaiveBayes(outlook, humidity, temperature, wind, dataCreator);
        naiveBayes.calculateProbability();

        naiveBayes.display();

        String[] choice = {};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nPlease enter the conditions: ");
        System.out.println("Input format: <outlook> <temperature> <humidity> <wind>");

        try {
            String input = br.readLine();
            choice = input.split(" ");
        } catch (IOException e) {
            System.out.println("Error in user input" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (choice.length < 4) {
            System.out.println("Please enter the choices");
            System.exit(1);
        }

        String prediction = naiveBayes.predict(choice[0], choice[1], choice[2], choice[3]);
        System.out.println("Prediction: " + prediction);

        System.out.println("\nFor Outlook = rain, Temperature = cool, Humidity = high and Wind = strong: ");
        prediction = naiveBayes.predict("rain", "cool", "high", "strong");
        System.out.println("Prediction: " + prediction);

    }

}
