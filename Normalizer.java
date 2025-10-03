public final class Normalizer {
    private Normalizer() {
    }

    /** Check if a character can be part of a word */
    public static boolean isWordChar(char c) {
        return isAsciiLetter(c) || c == '\'' || c == '-';
    }

    /** Convert a raw token into a clean, comparable form */
    public static String normalizeToken(String raw) {
        if (raw == null || raw.isEmpty())
            return "";
        String s = raw.toLowerCase();

        // Remove leading/trailing ' or -
        int i = 0, j = s.length() - 1;
        while (i <= j && (s.charAt(i) == '\'' || s.charAt(i) == '-'))
            i++;
        while (j >= i && (s.charAt(j) == '\'' || s.charAt(j) == '-'))
            j--;
        if (i > j)
            return "";

        s = s.substring(i, j + 1);

        boolean hasLetter = false;
        StringBuilder out = new StringBuilder(s.length());

        for (int k = 0; k < s.length(); k++) {
            char c = s.charAt(k);
            if (isAsciiLetter(c)) {
                hasLetter = true;
                out.append(c);
            } else if (c == '\'' || c == '-') {
                // keep internal ' or -
                out.append(c);
            } else {
                // invalid character found
                return "";
            }
        }
        return hasLetter ? out.toString() : "";
    }

    /** Check if the character is an ASCII letter */
    public static boolean isAsciiLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
