package rgbSnake;

import javax.swing.JFrame;

public class gameFrame extends JFrame
{
	//game frame default constructor
	gameFrame()
	{
		gamePanel gp  = new gamePanel();
		this.add(gp);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	
	
}
