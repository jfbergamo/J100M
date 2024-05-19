package proj;

import java.awt.*;

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
        int r = getWidth()/6;
        g.setColor(Color.WHITE);
        drawCircle(g, getWidth() / 2 - r, getHeight() / 2 - r, r);
        g.setColor(new Color(0xBFAD40));
        fillCircle(g, getWidth() / 2 + (int)(Math.cos(dt.getFrameTime()) * r), getHeight() / 2 + (int)(Math.sin(dt.getFrameTime()) * r), 4);
    }
    
    public void drawCircle(Graphics g, int x, int y, int r) {
        g.drawArc(x, y, r * 2, r * 2, 0, 360);
    }

    public void fillCircle(Graphics g, int x, int y, int r) {
        g.fillArc(x, y, r * 2, r * 2, 0, 360);
    }
}
