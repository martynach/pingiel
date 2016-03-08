package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public class Racket {
	Line2D.Double line = new Line2D.Double();
	private Board board;
	private long lastFrameTime = System.nanoTime();
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	int angle = 0;
	private Stroke stroke;
	
	double x;
	double y;
	double width;
	double height;
	
	public Racket(Board b, double[] pos, char[] keys) {
		board = b;
		this.x = pos[0]; this.y = pos[1]; this.width = pos[2]; this.height = pos[3];
		
		stroke = new BasicStroke((float) width);

		RacketKeyHelper keyHooks = new RacketKeyHelper(b);
		
		keyHooks.addListeners(KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChar(keys[0]), 0).getKeyCode(), e -> {
			moveUp = true;
		}, e -> {
			moveUp = false;
		});
		keyHooks.addListeners(KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChar(keys[1]), 0).getKeyCode(), e -> {
			moveDown = true;
		}, e -> {
			moveDown = false;
		});
		keyHooks.addListeners(KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChar(keys[2]), 0).getKeyCode(), e -> {
			moveRight = true;
		}, e -> {
			moveRight = false;
		});
		keyHooks.addListeners(KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChar(keys[3]), 0).getKeyCode(), e -> {
			moveLeft = true;
		}, e -> {
			moveLeft = false;
		});
	}
	
	
	public void update(Graphics2D g2d) {
		long currentTime = System.nanoTime();
		long timeDiff = currentTime - lastFrameTime;
		long pixels = (long) (timeDiff / 10000000);
		//System.out.println(pixels);
		
		if(moveUp)
			y -= (y > pixels ? pixels : y%pixels);
		
		if(moveDown)
			y = (board.getHeight()-y-height > pixels ? y+pixels : board.getHeight()-height);
		
		if(moveLeft)
			angle += pixels;
		
		if(moveRight)
			angle -= pixels;
		
		angle = (angle > 30 ? 30 : (angle < -30 ? -30 : angle));
		
		double xdiff = cos(toRadians(90+angle))*(height/2);
		double ydiff = sin(toRadians(90+angle))*height;
		
		line.x1 = x+(width/2)-xdiff;
		line.y1 = y;
		line.x2 = x+(width/2)+xdiff;
		line.y2 = y + ydiff;
		
		g2d.setStroke(stroke);
		g2d.draw(line);
		lastFrameTime = currentTime;
	}
	
	private interface RacketActionCallback {
		void cb(ActionEvent e);
	}
	
	private class RacketAction extends AbstractAction implements Action {
		
		private RacketActionCallback callback;
		
		public RacketAction(RacketActionCallback cb) {
			callback = cb;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			callback.cb(e);
			//System.out.println(e);
		}
		
	}
	
}
