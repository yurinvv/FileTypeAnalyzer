package service.search_strategy;

public interface SearchMethod {

    /**
     * @param text
     * @param pattern
     * @return The number of comparisons. Otherwise -1 if the pattern is not found in the text
     */
    int search(String text, String pattern);
}
