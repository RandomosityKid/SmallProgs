import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Locale;

public class CoinProbability3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numGoal = 10; // Number of consecutive heads
        int[] numAverages = {100, 1000, 10000, 100000, 1000000}; // Different workloads for testing

        for (int numAverage : numAverages) {
            System.out.println("Testing with " + numAverage + " iterations:");

            // Single-threaded version
            long startTime = System.currentTimeMillis();
            List<Long> singleThreadedResults = runSingleThreaded(numGoal, numAverage);
            long singleThreadedTime = System.currentTimeMillis() - startTime;

            System.out.println("Single-threaded time: " + singleThreadedTime + " ms");
            printStatistics(singleThreadedResults);

            // Parallel version
            startTime = System.currentTimeMillis();
            List<Long> parallelResults = runParallel(numGoal, numAverage);
            long parallelTime = System.currentTimeMillis() - startTime;

            System.out.println("Parallel time: " + parallelTime + " ms");
            printStatistics(parallelResults);

            System.out.println();
        }
    }

    private static List<Long> runSingleThreaded(int numGoal, int numAverage) {
        List<Long> triesList = new ArrayList<>();
        for (int i = 0; i < numAverage; i++) {
            triesList.add(simulateCoinFlips(numGoal));
        }
        return triesList;
    }

    private static List<Long> runParallel(int numGoal, int numAverage) throws InterruptedException, ExecutionException {
        int batchSize = 100000; // Increased batch size for better performance
        int numBatches = (numAverage + batchSize - 1) / batchSize;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<List<Long>>> futures = new ArrayList<>();

        for (int i = 0; i < numBatches; i++) {
            int taskSize = Math.min(batchSize, numAverage - i * batchSize);
            futures.add(executor.submit(new CoinFlipBatchTask(numGoal, taskSize)));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        List<Long> triesList = new ArrayList<>();
        for (Future<List<Long>> future : futures) {
            triesList.addAll(future.get());
        }
        return triesList;
    }

    private static long simulateCoinFlips(int numGoal) {
        long tries = 0;
        int iterations = 0;
        while (iterations != numGoal) {
            boolean flip = (int) (Math.random() * 2) == 0;
            iterations = flip ? iterations + 1 : 0;
            tries++;
        }
        return tries;
    }

    private static void printStatistics(List<Long> triesList) {
        long totalTries = triesList.stream().mapToLong(Long::longValue).sum();
        double averageTries = (double) totalTries / triesList.size();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        System.out.println("Total Tries: " + numberFormat.format(totalTries));
        System.out.println("Average Tries: " + numberFormat.format(averageTries));
        System.out.println("Min Tries: " + numberFormat.format(Collections.min(triesList)));
        System.out.println("Max Tries: " + numberFormat.format(Collections.max(triesList)));
    }
}

class CoinFlipBatchTask implements Callable<List<Long>> {
    private final int numGoal;
    private final int taskSize;

    public CoinFlipBatchTask(int numGoal, int taskSize) {
        this.numGoal = numGoal;
        this.taskSize = taskSize;
    }

    @Override
    public List<Long> call() {
        List<Long> results = new ArrayList<>(taskSize);
        for (int i = 0; i < taskSize; i++) {
            long tries = 0;
            int iterations = 0;
            while (iterations != numGoal) {
                boolean flip = (int) (Math.random() * 2) == 0;
                iterations = flip ? iterations + 1 : 0;
                tries++;
            }
            results.add(tries);
        }
        return results;
    }
}
