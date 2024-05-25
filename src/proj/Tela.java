// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.*;
import javax.swing.*;

public class Tela extends JPanel {

    // ATTRIBUTI

    private int n;
    private Font font;
    private int offset;
    private Color[] colors;
    private boolean finished;
    private int defaultRadius;
    private Corridore[] corridores;

    // COSTRUTTORE E METODI

    public Tela(Corridore[] corridori, Font f, int giri) {
        setLayout(null);

        n = giri;
        finished = false;
        corridores = corridori;
        font = f;

        colors = new Color[corridori.length];
        for (int i = 0; i < colors.length; ++i) {
            colors[i] = RandColor();
        }
        
        offset = 25;
    }
    
    @Override
    public void paint(Graphics g) {
        defaultRadius = getWidth() / 12;
        g.setFont(font);

        /* Sfondo */ {
            g.setColor(new Color(0x181818));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;

        // int iCenterX = (int)Math.round(centerX);
        int iCenterY = (int)Math.round(centerY);

        g.setColor(Color.RED);
        g.drawLine((int)Math.round(centerX + defaultRadius - offset), iCenterY, (int)Math.round(centerX + defaultRadius + offset * corridores.length), iCenterY);

        for (int i = 0; i < corridores.length; ++i) {
            double radius = defaultRadius + offset * i;
            Corridore c = corridores[i];

            /* Main loop */ {
                if (!c.isArrivato()){
                    c.nextCycle();
                }
                
            }
            
            /* Disegno pista */ {
                g.setColor(Color.WHITE);
                drawCircle(g, (int)Math.round(centerX), (int)Math.round(centerY), (int)Math.round(radius));
            }
            
            /* Disegno giocatori */ {
                double x = c.getDist()/c.getVittoria() * n*2*Math.PI;
                g.setColor(colors[i]);
                fillCircle(g, (int)Math.round(centerX + Math.cos(x) * radius), (int)Math.round(centerY + Math.sin(x) * radius), (int)Math.round(defaultRadius/14));
            }
            
            /* Punteggi e giri */ {
                disegnaPunteggi(g, i);
                int giro = (int)(c.getDist()/c.getVittoria() * n);
                if (i == 0) {
                    disegnaGiri(g, giro);
                }
            }

            /* Controllo vittoria */ {
                if (Main.scores.size() >= corridores.length && !finished) {
                    finished = true;
                }
            }
        }
    }
    
    private void disegnaPunteggi(Graphics g, int i) {
        FontMetrics metrics = g.getFontMetrics();
        int fontHeight = metrics.getHeight();
        Corridore c = corridores[i];
        
        int x = offset;
        int xOffset = x*2 + metrics.stringWidth(GUI.getLongestName(Corridore.getNomi(corridores)));
        int y = offset + fontHeight * i + fontHeight/2;
        
        g.drawString(c.getNome() + ":", x, y);
        
        String dist = Float.toString(Math.round(c.getDist()*100f)/100f);
        g.drawString(dist + " mt.", xOffset, y);
    }
    
    private void disegnaGiri(Graphics g, int giro) {
        FontMetrics metrics = g.getFontMetrics();
        int y = offset + metrics.getHeight()/2;

        String lap = "LAP: " + Integer.toString(giro) + "/" + Integer.toString(n);
        int x = getWidth() - offset - metrics.stringWidth(lap);
        g.setColor(Color.WHITE);
        g.drawString(lap, x, y);
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

    public boolean hasNotFinished() {
        return !finished;
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
