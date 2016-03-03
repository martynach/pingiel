package game;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;

public class Application extends JFrame{


	public Application(){
		init();
	}
	
	private boolean isResizable = true;
	private Dimension windowDimension = new Dimension(300, 200);
	private Dimension minimumWindowDimension = new Dimension(300, 200);
	
	private void init(){
		setSize(windowDimension);
		setResizable(isResizable);
		setMinimumSize(minimumWindowDimension);
		setTitle("PING-PONG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(new Board(new Dimension((int)(windowDimension.getWidth() - 15), (int)(windowDimension.getHeight() - 30))));
	}
	
	public static void main(String []args){
		EventQueue.invokeLater(() -> new Application().setVisible(true));
//		In the EvenQueue are many events, like keyboard events or mouse events or whatever.
//		There is a Thread that continuesly polls data from this queue.
//		Once that Thread reaches the anonymous class (lambda expression)
//		that was instantiated here, it will execute the run() method
//		There is a rule that code that affects the UI must be executed in the event dispatching thread.
//		The implementation like below may cause unpredicted behaviour:
//		Application ex = new Application();
//		ex.setVisible(true);
	}
}
