// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.*;
import proj.utils.*;
import javax.swing.*;

public class Tela extends JPanel {
    
    int n;
    Color[] colors;
    private TimeManager dt;
    private Corridore[] corridores;

    public Tela(TimeManager T, Corridore[] corridori, int giri) {
        n = giri;
        dt = T;
        corridores = corridori;
        colors = new Color[corridori.length];
        for (int i = 0; i < colors.length; ++i) {
            colors[i] = RandColor();
        }
    }

    int start = 0;

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0x181818));
        g.fillRect(0, 0, getWidth(), getHeight());

        double rr = getWidth()/12;
        double offset = 25;

        g.setColor(Color.RED);
        // g.drawLine(rr - offset, Math.round(getHeight()/2.0f), 0, Math.round(getHeight()/2.0f));

        for (int i = 0; i < corridores.length; ++i) {
            double r = rr + offset * i;
            Corridore c = corridores[i];

            if (!c.isArrivato()){
                c.nextCycle();
            }

            g.setColor(Color.WHITE);
            drawCircle(g, (int)Math.round(getWidth() / 2.0), (int)Math.round(getHeight() / 2.0), (int)Math.round(r));
            
            // double x = dt.getFrameTime();
            double x = c.getDist()/c.getVittoria() * n*2*Math.PI;
            g.setColor(colors[i]);
            fillCircle(g, (int)Math.round(getWidth() / 2.0 + Math.cos(x) * r), (int)Math.round(getHeight() / 2 + Math.sin(x) * r), (int)Math.round(rr/14));
        }
    }

    public void drawCircle(Graphics g, int x, int y, int r) {
        g.drawArc(x - r, y - r, r * 2, r * 2, 0, 360);
    }

    public void fillCircle(Graphics g, int x, int y, int r) {
        g.fillArc(x - r, y - r, r * 2, r * 2, 0, 360);
    }

    private Color RandColor() {
        int h = (int)(Math.random() * 255);
        int s = (int)(Math.random() * 255);
        int v = (int)(Math.random() * 255);
        return new Color(Color.HSBtoRGB(h, s, v));
    }
}
