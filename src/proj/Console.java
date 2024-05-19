// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

public class Console extends Main {
    public static void run() {
        StartAll();
        WaitAll();

        System.out.println("\n=============CLASSIFICA=============\n");
        
        for (String c : Main.scores) {
            System.out.println((Main.scores.indexOf(c) + 1) + "] " + c);
        }

        System.out.println("\n====================================\n");
    }
}