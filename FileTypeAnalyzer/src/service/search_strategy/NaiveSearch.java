package service.search_strategy;

public class NaiveSearch implements SearchMethod {

    @Override
    public int search(String text, String pattern) {
        return naiveSearch(text, pattern);
    }

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
