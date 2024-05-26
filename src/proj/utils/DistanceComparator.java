package proj.utils;

import java.util.Comparator;

import proj.Corridore;

public class DistanceComparator implements Comparator<Corridore> {

    @Override
    public int compare(Corridore a, Corridore b) {
        return Float.compare(a.getDist(), b.getDist());
    }
    
}
