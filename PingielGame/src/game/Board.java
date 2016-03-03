package game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;


public class Board extends JPanel{
	private Dimension boardDimension;
	
	public Board(Dimension boardDimension){	
		this.boardDimension = boardDimension;
		add(new JButton("START GAME"));
	}
	

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawBoard(graphics);
    }
    
    private void drawBoard(Graphics graphics){
    	
    	Graphics2D g2d = (Graphics2D)graphics;
    	g2d.draw(new Rectangle.Double(10, 9 * boardDimension.getHeight() / 20, 5, boardDimension.getHeight() / 10));
    	g2d.draw(new Rectangle.Double(boardDimension.getWidth() - 15, 9 * boardDimension.getHeight() / 20, 5, boardDimension.getHeight() / 10));
    }
}

