package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {

	Ellipse2D.Double ellipse;
	Board board;
	long lastFrameTime = System.nanoTime();
	int latestCollision = 0;
	double diameter = 10;

	public Ball(Board board) {
		this.board = board;
		ellipse = new Ellipse2D.Double(board.getWidth() / 2, board.getHeight() / 2, diameter, diameter);
	}

	public void update(Graphics2D g2d) {
		long currentTime = System.nanoTime();
		long timeDiff = currentTime - lastFrameTime;
		double pixels = timeDiff / 10000000;

		if (board.rightRacket.line.intersects(ellipse.getBounds2D()) && latestCollision != 1) {
			latestCollision = 1;
			changeDirection(board.rightRacket.angle + 90);
		} else if (board.leftRacket.line.intersects(ellipse.getBounds2D()) && latestCollision != 2) {
			latestCollision = 2;
			changeDirection(board.leftRacket.angle + 90);
		} else if (ellipse.y < 0 && latestCollision != 3) {
			latestCollision = 3;
			changeDirection(0);
		} else if (ellipse.y + diameter > board.getHeight() && latestCollision != 4) {
			latestCollision = 4;
			changeDirection(0);
		}
		// else if()//todo granica z board
		// else if() //przegrana koniec gry??

		ellipse.x += direction.x * pixels;
		ellipse.y += direction.y * pixels;

		lastFrameTime = currentTime;
		g2d.fill(ellipse);
	}

	private class Direction {
		private double horizontalAngle;
		private double x;
		private double y;

		private Direction() {
			Random random = new Random();
			boolean left = random.nextBoolean();
			horizontalAngle = -80 + random.nextInt(160);
			if (left) {
				horizontalAngle *= (-1);
			}
			x = cos(toRadians(horizontalAngle));
			y = sin(toRadians(horizontalAngle));
		}

	}

	Direction direction = new Direction();

	private void changeDirection(double angle) {
		direction.horizontalAngle = (2 * angle - direction.horizontalAngle) % 360;
		direction.x = cos(toRadians(direction.horizontalAngle));
		direction.y = sin(toRadians(direction.horizontalAngle));
	}
}
