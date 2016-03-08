package game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class RacketKeyHelper {
	interface ActionCallback {
		void cb(ActionEvent e);
	}

	class RacketAction extends AbstractAction implements Action {

		private ActionCallback callback;

		public RacketAction(ActionCallback cb) {
			callback = cb;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			callback.cb(e);
		}

	}

	private InputMap im;
	private ActionMap am;

	RacketKeyHelper(JComponent el) {
		im = el.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		am = el.getActionMap();
	}

	void addListeners(int keyCode, ActionCallback down, ActionCallback up) {
		im.put(KeyStroke.getKeyStroke(keyCode, 0, false), "down" + keyCode);
		am.put("down" + keyCode, new RacketAction(down));
		im.put(KeyStroke.getKeyStroke(keyCode, 0, true), "up" + keyCode);
		am.put("up" + keyCode, new RacketAction(up));
	}

}
