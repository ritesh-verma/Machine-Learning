import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;


public class Processor {

    private FileProcessor fp;
    private int k;
    private int seed;
    private float[] num;
    private float[] finalArray;
    private float[] inputArray;
    private float[][] matrix1;
    private float[][] matrix2;
    private float[][] matrix3;
    private String file;


    private Random random;

    public Processor(FileProcessor fp, int k, int seed, String fileName) {
        this.fp = fp;
        this.k = k;
        this.seed = seed;
        random = new Random(seed);
        num = new float[k];
        this.file = fileName;
    }

    /**
     * Initialize the class to read file and get inputs
     * And generate initial guess values
     */
    public void init() {
        getInput();

        for (int i = 0; i < k; i++) {
            num[i] = randomGenerator(getMin(), getMax());
        }
    }

    /**
     * Method to count number of lines in a file
     * @param filename input file name
     * @return number of lines
     * @throws IOException File not found
     */
    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    /**
     * Read file and create array of values
     */
    public void getInput() {
        String line;
        int counter = 0;
        try {
            inputArray = new float[countLines(file)]; // getInputCount()
        } catch (IOException e) {
            e.printStackTrace();
        }

        while ((line = fp.readLineFromFile()) != null) {
            inputArray[counter++] = Float.parseFloat(line);
        }
    }

    /**
     * Get the minimum from the list of input values
     * @return minimum value
     */
    public float getMin() {
        float min = Float.MAX_VALUE;

        for (int i = 0; i <inputArray.length; i++) {
            if (inputArray[i] < min) {
                min = inputArray[i];
            }
        }

        return min;
    }

    /**
     * Get the maximum from the list of input values
     * @return maximum value
     */
    public float getMax() {
        float max = Float.MIN_VALUE;

        for (int i = 0; i <inputArray.length; i++) {
            if (inputArray[i] > max) {
                max = inputArray[i];
            }
        }

        return max;
    }

    /** Generate random float. */
    public float randomGenerator(float min, float max) {
        float mean = (min + max) / 2;
        float var = (float) 1.0;

        return (float) (mean + random.nextGaussian() * var);
    }

    /**
     * Generate the expectation matrix
     * @param v
     * @param num
     * @return
     */
    public float expectationPart1(float v, float num) {
        return (float) Math.exp((-0.5) * (Math.pow((v - num), 2)));
    }

    /**
     * Processing starts here
     * @param counter
     * @return
     */
    public boolean process(int counter) {

        matrix1 = new float[inputArray.length][k];
        matrix2 = new float[inputArray.length][k];
        matrix3 = new float[inputArray.length][k];

        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < k; j++) {
                matrix1[i][j] = expectationPart1(inputArray[i], num[j]);
            }
        }

        float[] sum1 = new float[inputArray.length];
        float[] sum2 = new float[k];
        float[] sum3 = new float[k];
        finalArray = new float[k];

        // create matrix 1
        for (int i = 0; i < inputArray.length; i++) {
            sum1[i] = (float) 0.0;
            for (int j = 0; j < k; j++) {
                sum1[i] += matrix1[i][j];
            }
        }

        // create matrix 2
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < k; j++) {
                matrix2[i][j] = matrix1[i][j] / sum1[i];
            }
        }

        // create summation ai
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < inputArray.length; j++) {
                sum2[i] += matrix2[j][i];

            }
        }

        // create matrix 3 = xi * ai
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < k; j++) {
                matrix3[i][j] = matrix2[i][j] * inputArray[i];
            }
        }

        // summation of xi * ai
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < inputArray.length; j++) {
                sum3[i] += matrix3[j][i];
            }
        }

        // generate final array
        for (int i = 0; i < k; i++) {
            finalArray[i] = sum3[i] / sum2[i];
        }

        if (counter == 0) {
            System.out.println("Initial guess values: ");
            System.out.println(Arrays.toString(num));
            System.out.println();
        }

        if (counter > 0 && counter < 6) {
            System.out.print("Iteration " + counter + " :\t");
            System.out.println(Arrays.toString(num));
        }


        boolean toTerminate = checkValues();

        copyArray(num, finalArray);

        if (toTerminate) {
            System.out.println("\nConverges here.");
            System.out.print("Iteration " + (counter + 1) + " :\t");
            System.out.println(Arrays.toString(num));
        }

        return toTerminate;

    }

    /**
     * Check if the new generated array is same sa previous array
     * @return true or false
     */
    public boolean checkValues() {
        return Arrays.equals(num, finalArray);
    }

    /**
     * Copies the new array to initial array to start with
     * @param num
     * @param finalArray
     */
    private void copyArray(float[] num, float[] finalArray) {
        for (int i = 0; i < num.length; i++) {
            num[i] = finalArray[i];
        }
    }
}
