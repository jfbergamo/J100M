// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.util.ArrayList;

public class Demo {

    private static ArrayList<String> scores = new ArrayList<String>();
    private static final String[] nomi = {"Riccardo", "Gianpippo", "Pierpaolo", "Yudri", "Owusu"};
    
    private static final int corridori = nomi.length;
    private static Thread[] ts = new Thread[corridori];
    private static Corridore[] cs = new Corridore[corridori];

    private static float min = 0.50f;
    private static float max = 1.30f;
    private static float vittoria = 100.0f;

    public static void main(String[] args) {
        InitAll();
        StartAll();
        WaitAll();

        for (String c : scores) {
            System.out.println((scores.indexOf(c) + 1) + "] " + c);
        }
    }

    private static void InitAll() {
        for (int i = 0; i < corridori; i++) {
            cs[i] = new Corridore(min, max, vittoria, scores);
            ts[i] = new Thread(cs[i], nomi[i]);
        }
    }

    private static void StartAll() {
        for (Thread t : ts) {
            t.start();
        }
    }

    private static void WaitAll() {
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException ex) { System.exit(ex.hashCode()); }
        }
    }
}