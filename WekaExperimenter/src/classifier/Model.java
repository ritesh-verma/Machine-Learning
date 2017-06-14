package classifier;

import weka.classifiers.Classifier;

public class Model {
    private int minNumObj;
    private int numFolds;
    private double error;
    private Classifier bestModel = null;

    public Model(double error) {
        this.error = error;
    }

    public Model() {

    }

    public Model(int minNumObj, int numFolds, double error, Classifier bestModel) {
        this.minNumObj = minNumObj;
        this.numFolds = numFolds;
        this.error = error;
        this.bestModel = bestModel;
    }

    public void setMinNumObj(int minNumObj) {
        this.minNumObj = minNumObj;
    }

    public void setNumFolds(int numFolds) {
        this.numFolds = numFolds;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public int getMinNumObj() {

        return minNumObj;
    }

    public int getNumFolds() {
        return numFolds;
    }

    public Classifier getBestModel() {
        return bestModel;
    }

    @Override
    public String toString() {
        return "Model {" +
                "minNumObj = " + minNumObj +
                ", numFolds = " + numFolds +
                ", error = " + error +
                " }";
    }
}
