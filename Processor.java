import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Processor {
    private String inputFileName;

    public Processor(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void process() {
        try {
            // Build the buffer with the linear representation of the program
            char[] buffer = buildBuffer();

            // Place a sentinel value 's' at the end of the buffer
            char[] bufferWithSentinel = new char[buffer.length + 1];
            System.arraycopy(buffer, 0, bufferWithSentinel, 0, buffer.length);
            bufferWithSentinel[bufferWithSentinel.length - 1] = 's';

            // Write the buffer to "out2.txt"
            writeToOutputFile(bufferWithSentinel);

            // Display the contents of the new output file on the console
            displayOutputFileContents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the file character by character and build the buffer
    private char[] buildBuffer() throws IOException {
        StringBuilder bufferBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch != '\n') {
                    bufferBuilder.append((char) ch);
                }
            }
        }
        return bufferBuilder.toString().toCharArray();
    }

    // Write the buffer to an output file "out2.txt"
    private void writeToOutputFile(char[] buffer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("out2.txt"))) {
            writer.write(buffer);
        }
    }

    // Display the contents of the new output file on the console
    private void displayOutputFileContents() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("out2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Processor <input_file>");
            System.exit(1);
        }

        String inputFileName = args[0];
        Processor processor = new Processor(inputFileName);
        processor.process();
    }
}
