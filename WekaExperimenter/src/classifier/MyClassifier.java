package classifier;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.util.Random;
import java.util.Scanner;

public class MyClassifier {

    private Instances trainingInstance;
    private Instances testInstance;

    public MyClassifier(Instances trainingInstance, Instances testInstance) {
        this.trainingInstance = trainingInstance;
        this.testInstance = testInstance;

        trainingInstance.setClassIndex(0);
        testInstance.setClassIndex(0);
    }

    private Scanner sc = new Scanner(System.in);

    /**
     * Method to classify J48 or NBC based on user input
     * @param choice User's choice
     * @throws Exception
     */
    public void doClassify(int choice) throws Exception {
        Model j48Model = null, naiveBayesModel = null;
        while (true) {
            switch (choice) {
                case 1:
                    // J48 Classifier
                    System.out.println("J48 Classifier");
                    j48Model = classifyJ48();
                    break;

                case 2:
                    // Naive Bayes Classifier
                    System.out.println("Naive Bayes Classifier");
                    naiveBayesModel = classifyNaiveBayes();
                    break;

                case 3:
                    // Display result
                    displayResult(j48Model, naiveBayesModel);
                    break;

                case 0:
                default:
                    // Incorrect choice
                    sc.close();
                    System.exit(0);
            }
            System.out.println("\nPlease enter your choice");
            System.out.println("1. J48 \n2. Naive Bayes\n3. Display Result\n0. Exit");
            choice = sc.nextInt();
        }
    }

    /**
     * Compare the error of J48 and NBC algorithms
     * @param j48model best J48 model
     * @param naiveBayesModel best Naive Bayes model
     * @throws Exception
     */
    private void displayResult(Model j48model, Model naiveBayesModel) throws Exception {
        testInstance.setClassIndex(0);
        if (j48model.getError() < naiveBayesModel.getError()) {
            //J48 is selected
            System.out.println("J48 produces the best model.");

            Evaluation testEvaluation = new Evaluation(testInstance);
            testEvaluation.evaluateModel(j48model.getBestModel(), testInstance);

            System.out.println(testEvaluation.toSummaryString());
            System.out.println("Error on test data: " + testEvaluation.incorrect() / testEvaluation.numInstances());

        } else {
            // Naive Bayed is selected
            System.out.println("Naive Bayes produces the best model.");

            Evaluation testEvaluation = new Evaluation(testInstance);
            testEvaluation.evaluateModel(naiveBayesModel.getBestModel(), testInstance);
            System.out.println(testEvaluation.toSummaryString());
            System.out.println("Error on test data: " + testEvaluation.incorrect() / testEvaluation.numInstances());
        }

        for (int i = 0; i < testInstance.numInstances(); i++) {
            double prediction = j48model.getBestModel().classifyInstance(testInstance.instance(i));
            System.out.print("Predicted: " + testInstance.classAttribute().value((int) prediction));
            System.out.println(", Actual: " + testInstance.classAttribute().value((int) testInstance.instance(i).classValue()));
        }
    }

    /**
     * Run J48 algorithm on training data
     * @return the best model generated
     * @throws Exception
     */
    public Model classifyJ48() throws Exception {
        final int FOLDS = 10;
        int beginMinNumObj, endMinNumObj;
        int beginNumFolds, endNumFolds;

        System.out.println("Please enter the range for minNumObj (begin and end values):");
        beginMinNumObj = sc.nextInt();
        endMinNumObj = sc.nextInt();

        System.out.println("Please enter the range for numFolds (begin and end values):");
        beginNumFolds = sc.nextInt();
        endNumFolds = sc.nextInt();

        Evaluation trainingEvaluation = null;
        double minTrainingError = Integer.MAX_VALUE;
        Model bestModel = null;

        System.out.println("\nAverage error of 10-fold cross validation for J48:");

        for (int i = beginMinNumObj; i <= endMinNumObj; i++) {
            for (int j = beginNumFolds; j <= endNumFolds; j++) {
                J48 j48 = new J48();
                setTreeValues(j48, i, j);

                j48.buildClassifier(trainingInstance);
                trainingEvaluation = new Evaluation(trainingInstance);
                trainingEvaluation.crossValidateModel(j48, trainingInstance, FOLDS, new Random(1));

                double trainingError = trainingEvaluation.incorrect() / trainingEvaluation.numInstances();

                System.out.println("\nminNumObj: " + i + ", numObj: " + j + ", Error: " + trainingError);

                if (trainingError < minTrainingError) {
                    minTrainingError = trainingError;
                    bestModel = new Model(i, j, trainingError, j48);
                }
            }
        }

        System.out.println("The best selected model is: " + bestModel.toString());
        System.out.println(trainingEvaluation.toSummaryString());

        return bestModel;
    }

    /**
     * Run Naive Bayes on training data
     * @return the best model generated
     * @throws Exception
     */
    public Model classifyNaiveBayes() throws Exception {
        final int numFolds = 10;

        // Create a classifier instance
        Classifier naiveBayes = new NaiveBayes();


        // Build the classifier using test instance
        naiveBayes.buildClassifier(trainingInstance);

        // create evaluation instances for training and test instances
        Evaluation trainingEvaluation = new Evaluation(trainingInstance);
        Evaluation testEvaluation = new Evaluation(testInstance);

        // Perform a cross-validation for a classifier on a set of instances.
        trainingEvaluation.crossValidateModel(naiveBayes, trainingInstance, numFolds, new Random(1));

        System.out.println(trainingEvaluation.toSummaryString());

        double trainingError = trainingEvaluation.incorrect() / trainingEvaluation.numInstances();

        System.out.println("Average error of 10-fold cross validation for Naive Bayes Classifier: " + trainingError);

        return new Model(trainingError);
    }

    /**
     * Sets the initial values of the J48 tree
     * @param treeValues the tree instance
     * @param minNumObj minimum number of objects
     * @param numFolds number of folds
     */
    public void setTreeValues(J48 treeValues, int minNumObj, int numFolds) {
        treeValues.setUnpruned(false);
        treeValues.setReducedErrorPruning(true);
        treeValues.setMinNumObj(minNumObj);
        treeValues.setNumFolds(numFolds);
    }
}
