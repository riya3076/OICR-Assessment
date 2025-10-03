import java.nio.file.Path;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <document.txt> <dictionary.txt>");
            return;
        }

        Path docPath = Path.of(args[0]);
        Path dictPath = Path.of(args[1]);

        try {
            SpellChecker checker = SpellChecker.fromDictionaryFile(dictPath);
            LinkedHashSet<String> unknowns = checker.findUnknowns(docPath);

            for (String w : unknowns) {
                System.out.println(w);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
    }
}
