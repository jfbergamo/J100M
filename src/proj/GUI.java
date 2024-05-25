// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.io.File;

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
	
	private final float FONT_SIZE = 32.5f;

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
		
		// FONT
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/Burbank Big Condensed Black.otf")).deriveFont(FONT_SIZE);
		} catch (Exception e) {
			System.err.println("ERROR: Impossibile inizializzare il font:\n" + e.getMessage());
			System.exit(e.hashCode());
		}

		JButton btnPlayAgain = new JButton("playAgain");
		btnPlayAgain.setBounds(168, 120, 89, 23);

		Tela ctx = new Tela(Main.cs, font, btnPlayAgain, Main.giri);
		contentPane.add(ctx, BorderLayout.CENTER);

		new Thread(new Runnable() {
			@Override
			public void run() {
				long t = System.currentTimeMillis();
				do {
					if (t + dt < System.currentTimeMillis()) {
						ctx.repaint();
											

						t = System.currentTimeMillis();
					}
				} while (ctx.hasNotFinished());
				ctx.repaint();
			}
		}).start();
	}

	public static String getLongestName(String[] names) {
		if (names.length <= 0) return null;
		String it = names[0];
		for (String name : names) {
			if (name.length() > it.length()) {
				it = name;
			}
		}
		return it;
	}
}
