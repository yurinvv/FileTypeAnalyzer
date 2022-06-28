/**
 * Strategy pattern implementation : The Concrete Strategy 2.
 */
package ru.somesite.fileTypeAnalyzer.service.search_strategy;
// ConcreteStrategy2
public class KMPSearch implements SearchMethod {
    // execute()
    @Override
    public int search(String text, String pattern) {
        return KnuthMorrisPratt(text, pattern);
    }

    /**
     * The Knuth-Morris-Pratt (KMP) algorithm is an efficient
     * approach to the substring searching problem.
     *
     * The KMP algorithm calculates the prefix function for the
     * pattern and then uses it to shift the pattern more optimally
     * to compare a pattern with a substring of text to find a complete
     * matching
     *
     * To check whether a pattern s is a substring of a text t,
     * the algorithm requires O(|s| + |t|) operations in the worst case
     *
     * Refer to theory
     */
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

    /**
     * The prefix function is used here to speed up the process of substring search.
     *
     * For example, the prefix function of the string
     * s = ACCABACCAC is [0,0,0,1,0,1,2,3,4,2].
     *
     * Refer to theory
     */
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
