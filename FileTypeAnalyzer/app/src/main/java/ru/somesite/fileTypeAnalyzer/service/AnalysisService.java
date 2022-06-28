/**
 * The implementation of the IAnalysisService interface
 */

package ru.somesite.fileTypeAnalyzer.service;

import ru.somesite.fileTypeAnalyzer.io.Pattern;
import ru.somesite.fileTypeAnalyzer.service.search_strategy.KMPSearch;
import ru.somesite.fileTypeAnalyzer.service.search_strategy.SearchInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AnalysisService implements IAnalysisService {

    // Strategy context
    private SearchInvoker searchInvoker;

    //  An abstraction called ExecutorService encapsulates one
    //  or more threads into a single pool and puts submitted
    //  tasks in an internal queue to execute them
    //  by using the threads
    private ExecutorService executorService;

    // When we submit a Callable to executor service, it cannot
    // return a result directly since the submit method does not
    // wait until the task completes. Instead, an executor returns
    // a special object called Future that wraps the actual result
    // that may not even exist yet. This object represents the result
    // of an asynchronous computation (task).
    private List<Future<String>> futures;

    // Constructor
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


    // Add one task to the executorService
    private void addTask(File file, List<Pattern> patterns) {
        // The submit method of the executorService accepts a Callable task to be executed.
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

    // Recursion method
    // Add all tasks to the executorService
    private void addAllTasks(File root, List<Pattern> patterns) {
        if (root.isFile()) {
            addTask(root, patterns);
        } else {
            if (root.listFiles().length > 0) {
                for (File dir : root.listFiles()) {
                    addAllTasks(dir, patterns);
                }
            }
        }
    }

    // Get a result of the checking
    private String getResultAsString() throws ExecutionException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        List<Integer> checkIds = new ArrayList<>();

        for (int i = 0; i < futures.size(); i++) {
            checkIds.add(i);
        }

        // Until the task completes, the actual result is not present
        // in the future. To check it, there is a method isDone().
        while (checkIds.size() > 0) {
            for (int i = 0; i < checkIds.size(); i++) {
                if (futures.get(checkIds.get(i)).isDone()) {
                    sb.append(futures.get(checkIds.get(i)).get());
                    sb.append("\n");
                    checkIds.remove(i);
                }
            }
        }

        // It waits until all running tasks are completed
        // and prohibits submitting of new tasks
        // By the time this method is called, all tasks
        // will have already been completed.
        executorService.shutdown();
        return sb.toString();
    }
}
