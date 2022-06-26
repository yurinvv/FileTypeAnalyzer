import io.*;
import model.Model;
import service.AnalysisService;
import service.IAnalysisService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        Arguments arguments = new Arguments(args);
        Scanner scanner = new Scanner(new File(arguments.getPatternsPath()));
        IReader patternsReader = new PatternsReader(scanner);
        IPrinter printer = new PrintToConsole();
        List<Pattern> patterns = patternsReader.readPatterns();
        Collections.sort(patterns);
        IAnalysisService analysisService = new AnalysisService();

        Model model = new Model();
        model.init(arguments.getFilesPath(), patterns);
        try {
            model.analyze(analysisService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.done(printer);
    }
}
