package ru.somesite.fileTypeAnalyzer.service;

import ru.somesite.fileTypeAnalyzer.io.Pattern;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAnalysisService {

    /**
     * This method must accept a path to analysis files and the list of patterns.
     * This method must execute a parallel checking of files and returns a result.
     */
    String analyze(String docPath, List<Pattern> patterns) throws ExecutionException, InterruptedException;
}
