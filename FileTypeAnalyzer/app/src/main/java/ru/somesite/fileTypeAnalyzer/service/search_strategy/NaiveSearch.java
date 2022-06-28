/**
 * Strategy pattern implementation : The Concrete Strategy 1.
 */
package ru.somesite.fileTypeAnalyzer.service.search_strategy;
// ConcreteStrategy1
public class NaiveSearch implements SearchMethod {
    // execute()
    @Override
    public int search(String text, String pattern) {
        return naiveSearch(text, pattern);
    }

    /**
     * The naive substring algorithm
     *
     * At each step, this function compares a pattern with a substring of a text trying to find a complete matching
     */
    private int naiveSearch(String text, String pattern) {
        char[] textChar = text.toCharArray();
        char[] patternChar = pattern.toCharArray();
        int k = 0;
        int comparisons = 0;

        for(int i = 0; i < text.length(); i++) {
            comparisons++;

            if (textChar[i] == patternChar[k]) {
                if (k == patternChar.length - 1) {
                    return comparisons;
                }
                k++;
            } else {
                i = i - k;
                k = 0;
            }
        }

        return -1;
    }
}
