package game;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class Application extends JFrame{


	public Application(){
		init();
	}
	
	private boolean isResizable = true;
	private Dimension windowDimension = new Dimension(300, 200);
	private Dimension minimumWindowDimension = new Dimension(300, 200);
	private Board board;
	
	private void init(){
		setSize(windowDimension);
		setResizable(isResizable);
		setMinimumSize(minimumWindowDimension);
		setTitle("PING-PONG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		board = new Board();
		add(board);
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
