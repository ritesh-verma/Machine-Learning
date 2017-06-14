import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileProcessor {
	private BufferedReader br;
	private static int lineNumber = 0;
	
	/**
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public FileProcessor(String inputFileName) throws FileNotFoundException {
		File inputFile = new File(inputFileName);
		br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
	}
	
	/**
	 * 
	 * @return
	 */
	public String readLineFromFile(){
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

	
}