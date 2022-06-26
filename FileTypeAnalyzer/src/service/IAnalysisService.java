package service;

import io.Pattern;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAnalysisService {

    String analyze(String docPath, List<Pattern> patterns) throws ExecutionException, InterruptedException;
}
