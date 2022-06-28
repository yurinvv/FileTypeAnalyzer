/**
 * An interface that provides a readPatterns() method
 * that must return a list of the patterns read in some way.
 */

package ru.somesite.fileTypeAnalyzer.io;

import java.util.List;

public interface IPatternsReader {
    List<Pattern> readPatterns();
}
