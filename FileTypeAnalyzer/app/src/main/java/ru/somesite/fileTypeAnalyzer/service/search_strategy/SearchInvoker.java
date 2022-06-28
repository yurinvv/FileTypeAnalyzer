/**
 * Strategy pattern implementation : Context.
 */
package ru.somesite.fileTypeAnalyzer.service.search_strategy;

import java.io.*;

public class SearchInvoker {
    // strategy: Strategy
    private SearchMethod searchMethod;

    // SetStrategy(strategy)
    public void setMethod(SearchMethod searchMethod) {
        this.searchMethod = searchMethod;
    }

    // invoke()
    public int search(String text, String pattern) {
        return searchMethod.search(text, pattern);
    }

    // invoke()
    public int search(File file, String pattern) {
        int result = -1;

        //Writing Binary Files Using BufferedInputStream
        //By default, both BufferedInputStream and BufferedOutputStream has an internal buffer of 8192 bytes (8KB),
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[pattern.length()];
            byte[] tail = new byte[pattern.length()]; // This is necessary to avoid splitting the indicator line when reading the buffer.

            while (inputStream.read(buffer) != -1) {
                String text = String.format("%s%s", new String(tail), new String(buffer));
                tail = buffer.clone();
                result = searchMethod.search(text, pattern);
                if (result > 0) { break; }
                // The crutch for Zip files. This id can be found in pdf
                // In a Zip archive, this identifier is always at the beginning of the file.
                if ("PK".equals(pattern)) { break; }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
