// Bergamasco Jacopo, 4AIA, A.S. 2023-2024
// JLibs - Semaforo
// Scritto da Salvatore Adamo, modificato da me

package jlibs;

public class Semaforo {
    private int cnt;
    
    public Semaforo() {
        cnt = 1;
    }

    public Semaforo(int n) {
        cnt = n;
    }
    
    synchronized public void P(){
        try {
            cnt--;
            if (cnt < 0) {
                wait();
            }
        } catch (InterruptedException ex) {
            return;
        }
    }
    
    synchronized public void V(){
       cnt++;
       notify();
    }
}
