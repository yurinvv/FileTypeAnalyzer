/**
 * Strategy pattern implementation : The common Strategy interface for a family of algorithms.
 */
package ru.somesite.fileTypeAnalyzer.service.search_strategy;
// Strategy
public interface SearchMethod {
    // execute()
    int search(String text, String pattern);
}
