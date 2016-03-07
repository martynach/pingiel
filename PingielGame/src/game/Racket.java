package game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Racket {
	Rectangle2D.Double rectangle;
	private Board board;
	private long lastFrameTime = System.nanoTime();
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	int angle = 0;
	
	public Racket(Board b, double[] pos, char[] keys) {
		board = b;
		rectangle = new Rectangle2D.Double(pos[0], pos[1], pos[2], pos[3]);
		
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
	
	
	public Rectangle2D update(Graphics2D g2d) {
		long currentTime = System.nanoTime();
		long timeDiff = currentTime - lastFrameTime;
		long pixels = (long) (timeDiff / 10000000);
		//System.out.println(pixels);
		
		if(moveUp)
			rectangle.y -= (rectangle.y > pixels ? pixels : rectangle.y%pixels);
		
		if(moveDown)
			rectangle.y = (board.getHeight()-rectangle.y-rectangle.height > pixels ? rectangle.y+pixels : board.getHeight()-rectangle.getHeight());
		
		if(moveLeft)
			angle += pixels;
		
		if(moveRight)
			angle -= pixels;
		
		angle = (angle > 30 ? 30 : (angle < -30 ? -30 : angle));
		
		//rectangle.y++;
		lastFrameTime = currentTime;
		g2d.rotate(Math.toRadians(angle), rectangle.x+(rectangle.width/2), rectangle.y+(rectangle.height/2));
		g2d.fill(rectangle);
		g2d.rotate(Math.toRadians(-angle), rectangle.x+(rectangle.width/2), rectangle.y+(rectangle.height/2));
		return rectangle;
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
