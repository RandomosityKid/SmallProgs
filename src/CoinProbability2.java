import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class CoinProbability2 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("*");
            System.out.print("Enter how many consecutives: ");
            int numGoal = in.nextInt();
            System.out.print("Enter the average of: ");
            int numAverage = in.nextInt();

            ForkJoinPool pool = new ForkJoinPool();
            CoinFlipTask task = new CoinFlipTask(numGoal, numAverage, 1000); // Adjust the threshold as needed

            List<Long> triesList = pool.invoke(task);
            pool.shutdown();

            long totalTries = triesList.stream().mapToLong(Long::longValue).sum();
            double averageTries = (double) totalTries / numAverage;

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            String formattedAverageTries = numberFormat.format(averageTries);

            System.out.println("Total Tries: " + numberFormat.format(totalTries));
            System.out.println("Average Tries: " + formattedAverageTries);
            System.out.println("Min Tries: " + numberFormat.format(Collections.min(triesList)));
            System.out.println("Max Tries: " + numberFormat.format(Collections.max(triesList)));
            System.out.println("*");
        }
    }
}

class CoinFlipTask extends RecursiveTask<List<Long>> {
    private final int numGoal;
    private final int numAverage;
    private final int threshold;

    public CoinFlipTask(int numGoal, int numAverage, int threshold) {
        this.numGoal = numGoal;
        this.numAverage = numAverage;
        this.threshold = threshold;
    }

    @Override
    protected List<Long> compute() {
        if (numAverage <= threshold) {
            List<Long> result = new ArrayList<>(numAverage);
            for (int i = 0; i < numAverage; i++) {
                result.add(simulateCoinFlips(numGoal));
            }
            return result;
        } else {
            int mid = numAverage / 2;
            CoinFlipTask leftTask = new CoinFlipTask(numGoal, mid, threshold);
            CoinFlipTask rightTask = new CoinFlipTask(numGoal, numAverage - mid, threshold);

            leftTask.fork();
            List<Long> rightResult = rightTask.compute();
            List<Long> leftResult = leftTask.join();

            leftResult.addAll(rightResult);
            return leftResult;
        }
    }

    private long simulateCoinFlips(int numGoal) {
        long tries = 0;
        int iterations = 0;
        while (iterations != numGoal) {
            boolean flip = (int) (Math.random() * 2) == 0;
            iterations = flip ? iterations + 1 : 0;
            tries++;
        }
        return tries;
    }
}