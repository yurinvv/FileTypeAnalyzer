/**
 * The Model is responsible for all data and its related logic
 */

package ru.somesite.fileTypeAnalyzer.model;

import ru.somesite.fileTypeAnalyzer.io.IPrinter;
import ru.somesite.fileTypeAnalyzer.io.Pattern;
import ru.somesite.fileTypeAnalyzer.service.IAnalysisService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Model {

    private String result;
    private String docPath;
    private List<Pattern> patterns;

    // Initialization
    public void init(String docPath, List<Pattern> patterns) {
        this.docPath = docPath;
        this.patterns = patterns;
    }

    // Files analyze
    public void analyze(IAnalysisService analysisService) throws ExecutionException, InterruptedException {
        result = analysisService.analyze(docPath, patterns);
    }

    // Returning analysis results
    public void done(IPrinter printer) {
        printer.print(result);
    }
}
