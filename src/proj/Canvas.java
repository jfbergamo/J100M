// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.*;
import proj.utils.*;
import javax.swing.*;

public class Canvas extends JPanel {
    
    private TimeManager dt;

    public Canvas(TimeManager T) {
        dt = T;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0x181818));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    public void drawCircle(Graphics g, int x, int y, int r) {
        g.drawArc(x, y, r * 2, r * 2, 0, 360);
    }

    public void fillCircle(Graphics g, int x, int y, int r) {
        g.fillArc(x, y, r * 2, r * 2, 0, 360);
    }
}
