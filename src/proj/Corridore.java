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
    
    private String nome;
    private boolean arrivato;
    
    private ArrayList<String> scores;

    // COSTRUTTORE

    public Corridore(String Nome, float min, float max, float vittoria, ArrayList<String> punteggi) {
        nome = Nome;
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
            nextCycle();
        }
    }

    public void nextCycle() {
        dist += r.nextFloat(r_min, r_max + 1);
        if (dist >= vit) {
            arrivato = true;
            s.P();
            scores.add(getNome());
            s.V();
            dist = vit;
        }
        System.out.println(getNome() + ": " + dist);
    }
    
    // GETTER
    
    public String getNome() {
        return nome;
    }
    
    public static String[] getNomi(Corridore[] cs) {
        String[] names = new String[cs.length];
        for (int i = 0; i < cs.length; i++) {
            names[i] = cs[i].getNome();
        }
        return names;
    }
    
    public float getVittoria() {
        return vit;
    }
    
    public float getDist() {
        return dist;
    }
    
    public boolean isArrivato() {
        return arrivato;
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
