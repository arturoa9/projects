package rgbSnake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


import javax.swing.JPanel;

public class gamePanel extends JPanel implements ActionListener{
	
	/*
	 * Declare class variables
	 */
	static final int SCREEN_WIDTH = 500; 
	static final int SCREEN_HEIGHT = 500;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
	static final int DELAY = 100;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int fruitsEaten;
	int fruitX;
	int fruitY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	
	//game panel default constructor
	gamePanel()
	{
		random  = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));//set game square size
		this.setBackground(Color.black);//set background color
		this.setFocusable(true);//set focusable for user input
		this.addKeyListener(new myKeyAdapter());//initiate key listener
		startGame();//start game
		
	}
	
	public void startGame() 
	{
		newFruit();//method to create a new fruit
		running = true;//change game status to running
		timer = new Timer(DELAY,this);//initiate a new timer
		timer.start();//start a new timer
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) 
	{

		if(running)//confirm that the game is running
		{
			g.setColor(Color.magenta);//set color for fruit
			g.fillOval(fruitX, fruitY, UNIT_SIZE, UNIT_SIZE);//set random fruit location
			
			for(int i = 0; i < bodyParts; i++)//iterate through the body parts of the snake 
			{
				if( i == 0) 
				{
					g.setColor(Color.green);//color the head of the snake
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else
				{
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));//color the body of the snake
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
		}
		else
			gameOver(g);//game over method
	}

	
	public void move ()//listens to user input to move the snake
	{
		for (int i = bodyParts;i > 0; i--)//iterates through the snake body shifting the coordinates of the array 
		{
			x[i] = x[i-1];
			y[i] = y[i-1];			
		}


		
		switch(direction)//switch with cases to handel user input for the snake movement
		{
		case'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case'R':
			x[0] = x[0] + UNIT_SIZE;
			break;	
		}
	}
	public void newFruit() //initiate a new fruit in the game panel
	{
		fruitX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
		fruitY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;

	}
	
	public void checkFruit() //checks if the head of the snake has eaten a fruit
	{
		if((x[0] == fruitX) && (y[0] == fruitY)) //if the head of the snake eats a fruit add body parts and fruits eaten
		{
			bodyParts++;
			fruitsEaten++;
			newFruit();
		}

		
	}
	
	public void checkCrash()
	{
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) 
		{
			running = false;
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) 
		{
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) 
		{
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) 
		{
			running = false;
		}
		
		if(!running) 
		{
			timer.stop();
		}

	}

	public void gameOver(Graphics g)//creates game over message on the screen 
	{
		g.setColor(Color.red);
		g.setFont(new Font("times new roman",Font.BOLD,65));
		g.drawString("Game Over", (SCREEN_WIDTH/6), (SCREEN_HEIGHT/2));
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(running) 
		{
			move();
			checkFruit();
			checkCrash();
		}
	
		repaint();
	}
	
	public class myKeyAdapter extends KeyAdapter//listens to user input for the direction of the snake
	{
		@Override
		public void keyPressed(KeyEvent e) 
		{
			switch(e.getKeyCode()) 
			{
			case KeyEvent.VK_LEFT:
				if(direction != 'R') 
				{
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') 
				{
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') 
				{
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') 
				{
					direction = 'D';
				}
				break;
			}
			
		}
		
	}
	

}
