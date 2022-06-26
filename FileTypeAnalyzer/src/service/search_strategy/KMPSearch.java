package service.search_strategy;

public class KMPSearch implements SearchMethod {

    @Override
    public int search(String text, String pattern) {
        return KnuthMorrisPratt(text, pattern);
    }

    private int KnuthMorrisPratt(String text, String pattern) {
        int comparisons = 0;
        int[] prefixFun = prefixFunction(pattern);
        char[] patternChar = pattern.toCharArray();
        char[] textChar = text.toCharArray();
        int k = 0;

        for (int i = 0; i < text.length(); i++) {
            comparisons++;
            if (patternChar[k] == textChar[i]) {
                if (k == patternChar.length - 1) {
                    return comparisons;
                }
                k++;

            } else {
                if (k > 0) {
                    int shift = k - prefixFun[k - 1];
                    k = k - shift;
                    i--;
                }
            }
        }
        return -1;
    }

    private int[] prefixFunction(String pattern) {
        int[] p = new int[pattern.length()];

        int k = 0;

        for (int i = 1; i < pattern.length(); i++) {
            char target = pattern.charAt(i);
            while (k > 0 && pattern.charAt(k) != target) {
                k = p[k - 1];
            }

            if (pattern.charAt(k) == target) {
                k++;
            }

            p[i] = k;
        }

        return p;
    }
}
