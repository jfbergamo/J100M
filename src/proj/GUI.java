// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final int FPS = 40;
	private final long dt = Math.round(1.0/FPS * 1000.0);

	private final int WIDTH = 16;
	private final int HEIGHT = 9;
	private final int FACTOR = 100;
	
	private boolean run;

	public GUI() {
		// OPERAZIONI FINESTRA
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// OPERAZIONE FRAME
		setTitle("Corsa 100 m");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH*FACTOR, HEIGHT*FACTOR);
		
		Tela ctx = new Tela(Main.cs, Main.giri);
		contentPane.add(ctx, BorderLayout.CENTER);

		run = true;

		new Thread(new Runnable() {
			@Override
			public void run() {
				long t = System.currentTimeMillis();
				while (run) {
					if (t + dt < System.currentTimeMillis()) {
						ctx.repaint();						

						t = System.currentTimeMillis();
					}
				}
			}
		}).start();
		// Main.StartAll();
	}
}
