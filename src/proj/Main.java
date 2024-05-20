// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.EventQueue;
import java.util.ArrayList;

public class Main {

    protected static ArrayList<String> scores = new ArrayList<String>();
    public static final String[] nomi = {"Nunzio", "Salvatore", "Roberto", "Williams", "Paolo"};

    private static final int corridori = Main.nomi.length;
    private static Thread[] ts = new Thread[corridori];
    protected static Corridore[] cs = new Corridore[corridori];

    protected static int giri = 3;
    public static final float min = 0.50f;
    public static final float max = 1.30f;
    public static final float vittoria = 100.0f * giri;

    private static final boolean graphic = true;


    public static void main(String[] args) {
        InitAll();
        
        if (graphic) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        GUI frame = new GUI();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Console.run();
        }
    }

    private static void InitAll() {
        for (int i = 0; i < corridori; i++) {
            cs[i] = new Corridore(Main.nomi[i], min, max, vittoria, Main.scores);
            ts[i] = new Thread(cs[i]);
        }
    }

    protected static void StartAll() {
        for (Thread t : ts) {
            t.start();
        }
    }

    protected static void WaitAll() {
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException ex) { System.exit(ex.hashCode()); }
        }
    }
}
