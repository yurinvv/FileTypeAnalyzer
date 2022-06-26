package model;

import io.Arguments;
import io.IPrinter;
import io.Pattern;
import service.IAnalysisService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Model {

    private String result;
    private String docPath;
    private List<Pattern> patterns;

    public void init(String docPath, List<Pattern> patterns) {
        this.docPath = docPath;
        this.patterns = patterns;
    }

    public void analyze(IAnalysisService analysisService) throws ExecutionException, InterruptedException {
        result = analysisService.analyze(docPath, patterns);
    }

    public void done(IPrinter printer) {
        printer.print(result);
    }
}
