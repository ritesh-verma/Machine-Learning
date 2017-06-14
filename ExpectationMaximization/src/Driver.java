import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide 3 arguments.");
            System.out.println("Usage: <input_file_name> <k> <seed>");
            System.exit(1);
        }

        String fileName = args[0];
        int k = Integer.parseInt(args[1]);
        int seed = Integer.parseInt(args[2]);
        Processor processor;

        try {
            FileProcessor fp = new FileProcessor(fileName);
            processor = new Processor(fp, k, seed, fileName);
            processor.init();

            boolean terminate;

            int counter = 0;

            while (true) {
                terminate = processor.process(counter);
                counter++;
                if (terminate)
                    break;
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
