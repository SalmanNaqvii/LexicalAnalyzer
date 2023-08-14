import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Preprocessor {
    private String inputFileName;

    public Preprocessor(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void preprocess() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            StringBuilder codeBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                codeBuilder.append(line).append("\n");
            }

            String code = codeBuilder.toString();

            // Perform the preprocessing steps
            code = eliminateBlankLines(code);
            code = eliminateComments(code);
            code = eliminateWhitespace(code);
            code = eliminateImportsAndAnnotations(code);

            // Write the updated program to "out1.txt"
            writeToOutputFile(code);

            // Display the contents of the output file on the console
            displayOutputFileContents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Step 1: Eliminate blank lines in the program
    private String eliminateBlankLines(String code) {
        return code.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

    // Step 2: Identify and eliminate all double slash and slash star comments
    private String eliminateComments(String code) {
        return code.replaceAll("//.*|/\\*(.|\\R)*?\\*/", "");
    }

    // Step 3: Eliminate unnecessary tabs and spaces in the program
    private String eliminateWhitespace(String code) {
        return code.replaceAll("\\s+", " ");
    }

    // Step 4: Eliminate import statements and annotations
    private String eliminateImportsAndAnnotations(String code) {
        return code.replaceAll("(?m)^\\s*import\\s+.*;$|@\\w+\\s*", "");
    }

    // Write the updated program to a separate file named "out1.txt"
    private void writeToOutputFile(String processedCode) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("out1.txt"))) {
            writer.write(processedCode);
        }
    }

    // Display the contents of the output file on the console
    private void displayOutputFileContents() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("out1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Preprocessor <input_file>");
            System.exit(1);
        }

        String inputFileName = args[0];
        Preprocessor preprocessor = new Preprocessor(inputFileName);
        preprocessor.preprocess();
    }
}
