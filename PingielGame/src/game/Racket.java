package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class Racket {
	private Rectangle2D.Double rectangle;
	private Board board;
	private long lastFrameTime = System.nanoTime();
	
	public Racket(Board b, double[] pos, char[] keys) {
		board = b;
		rectangle = new Rectangle2D.Double(pos[0], pos[1], pos[2], pos[3]);
		
		InputMap im = b.getInputMap();
		ActionMap am = b.getActionMap();
		im.put(KeyStroke.getKeyStroke(keys[0]), keys[0]);
		im.put(KeyStroke.getKeyStroke(keys[1]), keys[1]);
		
		// TODO: change to time based
		am.put(keys[0], new RacketAction((e) -> {
			if(rectangle.y > 2)
				rectangle.y -= 2;
		}));
		am.put(keys[1], new RacketAction((e) -> {
			if(rectangle.y+rectangle.height+2 <= b.getHeight())
				rectangle.y += 2;
		}));
		//System.out.println(im.keys());
	}
	
	
	public Rectangle2D update() {
		long currentTime = System.nanoTime();
		long timeDiff = lastFrameTime - currentTime;
		
		//rectangle.y++;
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
