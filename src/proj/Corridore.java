// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import jlibs.*;
import java.util.*;

public class Corridore implements Runnable {
    
    // ATTRIBUTI
    
    private static Semaforo s = new Semaforo();
    private static Random r = new Random(System.currentTimeMillis());
    
    private float r_max, r_min; // Random Range
    private float vit; // Distanza per vincere
    private float dist; // Distanza percorsa
    boolean arrivato;

    private ArrayList<String> scores;

    // COSTRUTTORE

    public Corridore(float min, float max, float vittoria, ArrayList<String> punteggi) {
        setRMin(min);
        setRMax(max);
        setVittoria(vittoria);
        dist = 0;
        arrivato = false;
        scores = punteggi;
    }

    // METODI

    @Override
    public void run() {
        while (!arrivato) {
            dist += r.nextFloat(r_min, r_max + 1);
            if (dist >= vit) {
                arrivato = true;
                s.P();
                scores.add(Thread.currentThread().getName());
                s.V();
                dist = vit;
            }
            System.out.println(Thread.currentThread().getName() + ": " + dist);
        }
    }

    // GETTER
    
    public float getVittoria() {
        return vit;
    }

    public float getDist() {
        return dist;
    }

    // SETTER

    private void setRMin(float m) {
        r_min = m;
    }

    private void setRMax(float M) {
        r_max = M;
    }

    public void setVittoria(float vittoria) {
        vit = vittoria;
    }

}
