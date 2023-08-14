import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzer {
    private String inputFileName;

    public LexicalAnalyzer(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void analyze() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                analyzeLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void analyzeLine(String line) {
        String[] tokens = line.split("\\s+|(?<=[\\p{Punct}])|(?=[\\p{Punct}])");
        for (String token : tokens) {
            if (!token.isEmpty()) {
                System.out.println("Lexeme: " + token);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java LexicalAnalyzer <input_file>");
            System.exit(1);
        }

        String inputFileName = args[0];
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(inputFileName);
        lexicalAnalyzer.analyze();
    }
}
