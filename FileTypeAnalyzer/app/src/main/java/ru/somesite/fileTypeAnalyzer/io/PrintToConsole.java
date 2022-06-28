/**
 * Implementation of IPrinter interface.
 *
 * Print to console.
 */

package ru.somesite.fileTypeAnalyzer.io;

public class PrintToConsole implements IPrinter {
    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
