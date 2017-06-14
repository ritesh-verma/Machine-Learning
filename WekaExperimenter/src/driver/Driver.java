package driver;

import classifier.MyClassifier;
import weka.core.Instances;

import java.io.*;
import java.util.Scanner;

public class Driver {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide input arguments.");
            System.out.println("Usage: <training_set> <test_set>");
            System.exit(1);
        }

        String trainingSet = args[0];
        String testSet = args[1];

        Reader trainingReader = null, testReader = null;
        Instances trainingInstance = null;
        Instances testInstance = null;

        try {
            trainingReader = new BufferedReader(new InputStreamReader(new FileInputStream(trainingSet)));
            testReader = new BufferedReader(new InputStreamReader(new FileInputStream(testSet)));

            // Create new instances of training and test sets
            // Uses trainingReader and testReader
            trainingInstance = new Instances(trainingReader);
            testInstance = new Instances(testReader);

            // set the class index of sets to 0
            trainingInstance.setClassIndex(0);
            testInstance.setClassIndex(0);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nPlease enter your choice:");
        System.out.println("1. J48 \n2. Naive Bayes\n3. Display Result\n0. Exit");

        MyClassifier myClassifier = new MyClassifier(trainingInstance, testInstance);

        int choice = sc.nextInt();

        try {
            myClassifier.doClassify(choice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
