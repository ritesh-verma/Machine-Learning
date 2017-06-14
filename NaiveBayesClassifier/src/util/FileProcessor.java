package util;

import java.io.*;

public class FileProcessor {
    private BufferedReader br;
    private static int lineNumber = 0;

    public FileProcessor(String inputFileName) throws FileNotFoundException {
        File inputFile = new File(inputFileName);
        br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
    }

    String readLineFromFile(){
        String line = null;
        try {
            if(br != null)
                line = br.readLine();
            lineNumber++;
        }catch(IOException e) {
            System.out.println("Error while reading from file at line: " + lineNumber);
            e.printStackTrace();
            System.exit(1);
        }
        return line;
    }

    public void closeFile() throws IOException {
       br.close();
    }
}
