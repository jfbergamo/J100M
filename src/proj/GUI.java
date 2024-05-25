// Bergamasco Jacopo, 4AIA, A.S. 2023-2024

package proj;

import java.io.File;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class GUI extends JFrame {

	private static GUI gui;	

	private JPanel contentPane;

	private final int FPS = 60;
	private final long dt = Math.round(1.0/FPS * 1000.0);

	private final int WIDTH = 16;
	private final int HEIGHT = 9;
	private final int FACTOR = 100;
	
	private final float FONT_SIZE = 32.5f;

	public GUI() {
		gui = this; // Tranquillo prof so cosa sto facendo, trusta il processo

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

		Tela ctx = new Tela(Main.cs, font, Main.giri);
		contentPane.add(ctx, BorderLayout.CENTER);
		ctx.setLayout(null);

		JButton btnPlayAgain = new JButton("Gioca ancora");
		btnPlayAgain.setVisible(false);
        ctx.add(btnPlayAgain);
		btnPlayAgain.setBounds(ctx.getWidth() / 2 - 90 / 2, ctx.getHeight() / 2 - 24 / 2, 90, 24);

		btnPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPlayAgain.setVisible(false);
				Main.Reset();
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				long t = System.currentTimeMillis();
				do {
					if (t + dt <= System.currentTimeMillis()) {
						ctx.repaint();

						t = System.currentTimeMillis();
					}
				} while (ctx.hasNotFinished());
				showClassifica();
				btnPlayAgain.setVisible(true);
			}
		}).start();
	}

	private void showClassifica() {
		String s = "";
		for (int i = 0; i < Main.scores.size(); i++) {
			s += Integer.toString(i + 1) + "] " + Main.scores.get(i) + "\n";
		}
		try {
			JOptionPane.showMessageDialog(null, s, "CLASSIFICA", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {}
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

	// 7R E GETTER

	public static GUI getCurrentFrame() {
		return gui;
	}
}
