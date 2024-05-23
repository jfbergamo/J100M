// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.*;
import javax.swing.*;

public class Tela extends JPanel {

    // ATTRIBUTI

    int n;
    Color[] colors;
    private int offset;
    private int defaultRadius;
    private Corridore[] corridores;
    private boolean hasSeenClassifica;

    // COSTRUTTORE E METODI

    public Tela(Corridore[] corridori, int giri) {
        n = giri;
        hasSeenClassifica = false;
        corridores = corridori;
        colors = new Color[corridori.length];
        for (int i = 0; i < colors.length; ++i) {
            colors[i] = RandColor();
        }
        defaultRadius = getWidth() / 12;
        offset = 25;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0x181818));
        g.fillRect(0, 0, getWidth(), getHeight());

        double rr = getWidth()/12;
        double offset = 25;

        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;

        // int iCenterX = (int)Math.round(centerX);
        int iCenterY = (int)Math.round(centerY);

        g.setColor(Color.RED);
        g.drawLine((int)Math.round(centerX + rr - offset), iCenterY, (int)Math.round(centerX + rr + offset * corridores.length), iCenterY);

        for (int i = 0; i < corridores.length; ++i) {
            double r = rr + offset * i;
            Corridore c = corridores[i];

            if (!c.isArrivato()){
                c.nextCycle();
            }

            if (Main.scores.size() >= corridores.length && !hasSeenClassifica) {
                hasSeenClassifica = true;
                showClassifica();
            }

            g.setColor(Color.WHITE);
            drawCircle(g, (int)Math.round(centerX), (int)Math.round(centerY), (int)Math.round(r));
            
            // double x = dt.getFrameTime();
            double x = c.getDist()/c.getVittoria() * n*2*Math.PI;
            g.setColor(colors[i]);
            fillCircle(g, (int)Math.round(centerX + Math.cos(x) * r), (int)Math.round(centerY + Math.sin(x) * r), (int)Math.round(rr/14));
        }
    }

    private void showClassifica() {
        String s = "";
        for (String c : Main.scores) {
            s += Integer.toString(Main.scores.indexOf(c) + 1) + "] " + c + "\n";
        }
        try {
            JOptionPane.showMessageDialog(this, s, "CLASSIFICA", 1);
        } catch (Exception e) {}
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
        int b = (int)(Math.random() * 255);
        return new Color(Color.HSBtoRGB(h, s, b));
    }

    // 7R e GETTER

    public int getDefaultRadius() {
        return defaultRadius;
    }

    public int getOffset() {
        return offset;
    }

    public int getCorridoriCount() {
        return corridores.length;
    }

    public int getGiri() {
        return n;
    }

    public Corridore getCorridore(int i) {
        if (0 <= i && i < corridores.length) {
            return corridores[i];
        } else {
            return null;
        }
    }

    public Color getColor(int i) {
        if (0 <= i && i < colors.length) {
            return colors[i];
        } else {
            return null;
        }
    }
}
