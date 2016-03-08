package game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Board extends JPanel {
	Racket leftRacket;
	Racket rightRacket;
	private Ball ball;
	private boolean gameStarted = false;
	JButton startButton = new JButton("START GAME");

	public Board() {
		add(startButton);

		startButton.addActionListener((event) -> {
			startButton.setVisible(false);
			gameStarted = true;
			startGame();
		});
	}

	private void startGame() {
		leftRacket = new Racket(this, new double[] { 10, 0, 5, 30 }, new char[] { 'w', 's', 'a', 'd' });
		rightRacket = new Racket(this, new double[] { this.getWidth() - 15, 0, 5, 30 },	new char[] { 'i', 'k', 'j', 'l' });
		ball = new Ball(Board.this);

		new Thread(() -> {
			while (true) {
				this.repaint();
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				rightRacket.x = Board.this.getWidth() - 15;
			}
		});
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (gameStarted) {
			drawBoard(graphics);
		}
	}

	private void drawBoard(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) graphics;

		leftRacket.update(g2d);
		rightRacket.update(g2d);
		ball.update(g2d);
	}
}
