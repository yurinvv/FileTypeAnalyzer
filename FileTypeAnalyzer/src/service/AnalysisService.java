package service;

import io.IReader;
import io.Pattern;
import io.PatternsReader;
import service.search_strategy.KMPSearch;
import service.search_strategy.SearchInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AnalysisService implements IAnalysisService {

    private SearchInvoker searchInvoker;
    private List<Future<String>> futures;
    private ExecutorService executorService;

    public AnalysisService() {
        searchInvoker = new SearchInvoker();
        searchInvoker.setMethod(new KMPSearch());
        futures = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(10);
    }

    @Override
    public String analyze(String docPath, List<Pattern> patterns) throws ExecutionException, InterruptedException {
        File file = new File(docPath);
        addAllTasks(file, patterns);
        return getResultAsString();
    }


    private void addTask(File file, List<Pattern> patterns) {
        Future<String> future = executorService.submit(() -> {
            for (int i = patterns.size() - 1; i >= 0; i--) {
                if (searchInvoker.search(file, patterns.get(i).getIndicator()) > 0) {
                    return String.format("%s: %s", file.getName(), patterns.get(i).getDocType());
                }
            }
            return String.format("%s: %s", file.getName(), "Unknown file type");
        });

        futures.add(future);
    }

    private void addAllTasks(File root, List<Pattern> patterns) {
        if (root.isFile()) {
            addTask(root, patterns);
        } else {
            if (root.listFiles().length > 0) {
                for (File dir : root.listFiles()) {
                    addTask(dir, patterns);
                }
            }
        }
    }

    private String getResultAsString() throws ExecutionException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        List<Integer> checkIds = new ArrayList<>();

        for (int i = 0; i < futures.size(); i++) {
            checkIds.add(i);
        }

        while (checkIds.size() > 0) {
            for (int i = 0; i < checkIds.size(); i++) {
                if (futures.get(checkIds.get(i)).isDone()) {
                    sb.append(futures.get(checkIds.get(i)).get());
                    sb.append("\n");
                    checkIds.remove(i);
                }
            }
        }

        executorService.shutdown();
        return sb.toString();
    }
}
