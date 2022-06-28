/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.somesite.fileTypeAnalyzer;
import ru.somesite.fileTypeAnalyzer.io.*;
import ru.somesite.fileTypeAnalyzer.service.*;
import ru.somesite.fileTypeAnalyzer.model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Attention!
 * Lame English
 */

public class App {

    public String getGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("//---------------------------//\r\n");
        sb.append("//    File type analyzer!    //\r\n");
        sb.append("//---------------------------//\r\n");
        sb.append("\r\n");
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new App().getGreeting());
        // Creating a view object of the passed arguments
        Arguments arguments = new Arguments(args);
        // Passing the path to the file with patterns to the scanner
        Scanner scanner = new Scanner(new File(arguments.getPatternsPath()));
        // Creating of a pattern reader is based on the scanner
        IPatternsReader patternsReader = new PatternsReader(scanner);
        // Creating an Information Printer for output to console
        IPrinter printer = new PrintToConsole();
        // Creating a list of pattern view objects that are read by the pattern reader
        List<Pattern> patterns = patternsReader.readPatterns();
        // Sorting the list to respect pattern priorities
        // In order not to confuse documents. For example, .docx and .zip
        Collections.sort(patterns);
        // Creating of an instance analysis Service
        IAnalysisService analysisService = new AnalysisService();

        // Creating the model, which is responsible for keeping the state of the program
        Model model = new Model();
        // Initialize the model
        model.init(arguments.getFilesPath(), patterns);
        // Try to run a files checking
        try {
            model.analyze(analysisService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Print results
        model.done(printer);
        // Finish!
    }
}