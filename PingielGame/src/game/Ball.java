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
	double diameter = 10;
	
	public Ball(Board board){
		this.board = board;
		double x = board.getWidth();
		double y = board.getHeight();
		ellipse = new Ellipse2D.Double(board.getWidth() / 2, board.getHeight() / 2, diameter, diameter);
	}
	
	public void update(Graphics2D g2d){
		long currentTime = System.nanoTime();
		long timeDiff = currentTime - lastFrameTime;
		double pixels = timeDiff / 10000000;
		
		if(direction.justChanged){
			direction.justChanged = false;
		}else{
			if(ellipse.intersects(board.rightRacket.rectangle)){
				changeDirection(board.rightRacket.angle + 90);
			}else if(ellipse.intersects(board.leftRacket.rectangle)){
				changeDirection(board.leftRacket.angle + 90);
			}
			else if(ellipse.y < 0 || ellipse.y + diameter > board.getHeight()){
				changeDirection(0);
			}
//			else if()//todo granica z board
//			else if() //przegrana koniec gry??
		}
				
		ellipse.x += direction.x * pixels;
		ellipse.y += direction.y * pixels;
		
			
		lastFrameTime = currentTime;
		g2d.fill(ellipse);
	}
	
	private class Direction{
		private double horizontalAngle;
		private double x;
		private double y;
		private boolean justChanged = false;
		
		private Direction(){			
			Random random = new Random();
			boolean left = random.nextBoolean();
			horizontalAngle = - 80 + random.nextInt(160);
			if(left){
				horizontalAngle *= (-1);
			}
			x = cos(toRadians(horizontalAngle));
			y = sin(toRadians(horizontalAngle));			
		}
		
	}
	
	Direction direction = new Direction();
	
	private void changeDirection(double angle){		
		direction.horizontalAngle = (2 * angle - direction.horizontalAngle) % 360;
		direction.x = cos(toRadians(direction.horizontalAngle));
		direction.y = sin(toRadians(direction.horizontalAngle));
		direction.justChanged = true;
	}
}
