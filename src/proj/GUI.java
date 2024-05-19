package proj;

import java.awt.EventQueue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI extends JFrame implements TimeManager {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final int FPS = 1;
	private final long dt = (long)(1.0 / (double)FPS * 1000.0);
	private long frameTime;

	private boolean run;

	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Canvas ctx = new Canvas(this);
		contentPane.add(ctx, BorderLayout.CENTER);

		frameTime = 0;
		run = true;

		new Thread(new Runnable() {
			@Override
			public void run() {
				long t = System.currentTimeMillis();
				while (run) {
					if (t + dt >= System.currentTimeMillis()) {
						frameTime += dt;

						ctx.repaint();						

						t = System.currentTimeMillis();
					}
				}
			}
		}).start();
	}

	@Override
	public long getFrameTime() {
		return frameTime;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
