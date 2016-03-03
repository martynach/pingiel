package game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;


public class Board extends JPanel{
	private Dimension boardDimension;
	
	private Racket leftRacket;
	private Racket rightRacket;
	
	public Board(Dimension boardDimension){	
		this.boardDimension = boardDimension;
		leftRacket = new Racket(this, new double[]{0, 0, 5, 30}, new char[]{'w', 's'});
		rightRacket = new Racket(this, new double[]{boardDimension.getWidth()-5, 0, 5, 30}, new char[]{'o', 'l'});
		
		add(new JButton("START GAME"));
		
		new Thread(() -> {
			while(true) {
				this.repaint();
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void ourResize(Dimension d) {
		boardDimension = d;
		rightRacket.rectangle.x = boardDimension.getWidth()-5;
	}
	

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawBoard(graphics);
    }
    
    private void drawBoard(Graphics graphics){    
    	Graphics2D g2d = (Graphics2D)graphics;
    
    	g2d.fill(leftRacket.update());
    	g2d.fill(rightRacket.update());
    	
    }
}

