import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * SpellChecker using a HashSet as the dictionary.
 * Loads words into memory and checks a document for unknown words.
 */
public class SpellChecker {
    private final HashSet<String> dictionary;

    private SpellChecker(HashSet<String> dictionary) {
        this.dictionary = dictionary;
    }

    /** Build a SpellChecker from a dictionary file */
    public static SpellChecker fromDictionaryFile(Path dictPath) throws IOException {
        HashSet<String> dict = new HashSet<>();
        try (BufferedReader br = Files.newBufferedReader(dictPath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String w = Normalizer.normalizeToken(line.trim());
                if (!w.isEmpty()) {
                    dict.add(w);
                }
            }
        }
        return new SpellChecker(dict);
    }

    /** Check if a normalized word is in the dictionary */
    public boolean containsNormalized(String normalized) {
        return dictionary.contains(normalized);
    }

    /** Scan the document and collect words not in the dictionary */
    public LinkedHashSet<String> findUnknowns(Path docPath) throws IOException {
        LinkedHashSet<String> unknowns = new LinkedHashSet<>();

        try (Reader r = Files.newBufferedReader(docPath, StandardCharsets.UTF_8)) {
            StringBuilder token = new StringBuilder(32);

            while (true) {
                int ch = r.read();
                if (ch == -1) { // end of file
                    flushToken(token, unknowns);
                    break;
                }
                char c = (char) ch;

                if (Normalizer.isWordChar(c)) { // letters + ' or -
                    token.append(c);
                } else {
                    flushToken(token, unknowns);
                }
            }
        }
        return unknowns;
    }

    /** Normalize a token and add it to unknowns if not in dictionary */
    private void flushToken(StringBuilder token, LinkedHashSet<String> unknowns) {
        if (token.length() == 0)
            return;

        String normalized = Normalizer.normalizeToken(token.toString());
        token.setLength(0); // reset for next token

        if (!normalized.isEmpty() && !containsNormalized(normalized)) {
            unknowns.add(normalized);
        }
    }
}
