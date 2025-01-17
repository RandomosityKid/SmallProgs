import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class coinProbability4{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        while (true) {
			System.out.println("*");
            System.out.print("Enter how many consecutive times results are Head: ");
            int numGoal = in.nextInt();
            System.out.print("Enter the amount of sets done: ");
            int numAverage = in.nextInt();
            
            List<Integer> triesList = new ArrayList<>();
            
            for (int i = 0; i < numAverage; i++) {
                int tries = simulateCoinFlips(numGoal);
                triesList.add(tries);
            }
            
            int totalTries = 0;
            for (int tries : triesList) {
                totalTries += tries;
            }
            
            double averageTries = (double) totalTries / numAverage;
            
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            List<String> formattedTriesList = new ArrayList<>();
//            for (int tries : triesList) {
//                formattedTriesList.add("--" + numberFormat.format(tries) + "--");
//            }
            
            String formattedAverageTries = numberFormat.format(averageTries);
            
            System.out.println("Total Tries: " + numberFormat.format(totalTries));
            System.out.println("Average Tries: " + formattedAverageTries);
//            System.out.println("Tries List: " + formattedTriesList);
            
            int minTries = Collections.min(triesList);
            int maxTries = Collections.max(triesList);
            
            System.out.println("Min Tries: " + numberFormat.format(minTries));
            System.out.println("Max Tries: " + numberFormat.format(maxTries));
			System.out.println("*");
        }
    }
    
    public static int simulateCoinFlips(int numGoal) {
        int tries = 0;
        int iterations = 0;
        while ((iterations != (numGoal)) && (tries < 5000000)) {         
            boolean flip = (int) (Math.random() * 2) == 0;
            iterations = flip ? iterations + 1 : 0;
            tries++;
        }
        return tries;
    }
}
