// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.*;
import proj.utils.*;
import javax.swing.*;

public class Tela extends JPanel {
    
    private TimeManager dt;

    public Tela(TimeManager T) {
        dt = T;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0x181818));
        g.fillRect(0, 0, getWidth(), getHeight());

        double r = getWidth()/6;
        g.setColor(Color.WHITE);
        drawCircle(g, (int)Math.round(getWidth() / 2.0 - r), (int)Math.round(getHeight() / 2.0 - r), (int)Math.round(r));
        g.setColor(new Color(0xBFAD40));
        fillCircle(g, (int)Math.round(getWidth() / 2.0 + Math.cos(dt.getFrameTime()) * r), (int)Math.round(getHeight() / 2 + Math.sin(dt.getFrameTime()) * r), 12);
    }
    
    public void drawCircle(Graphics g, int x, int y, int r) {
        g.drawArc(x, y, r * 2, r * 2, 0, 360);
    }

    public void fillCircle(Graphics g, int x, int y, int r) {
        g.fillArc(x - r, y - r, r * 2, r * 2, 0, 360);
    }
}
